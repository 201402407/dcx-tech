package dcx.lpoint.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import mosample.bo.lpoint.domain.Tran7210;
import mosample.bo.lpoint.domain.Tran7220;
import mosample.bo.lpoint.domain.Tran7610;
import mosample.bo.lpoint.domain.condition.LPointCondition;
import mosample.bo.lpoint.domain.receive.LP7001;
import mosample.bo.lpoint.domain.receive.LP7211;
import mosample.bo.lpoint.domain.receive.LP7221;
import mosample.bo.lpoint.domain.receive.LP7611;
import mosample.bo.lpoint.domain.send.LP7000;
import mosample.bo.lpoint.domain.send.LP7210;
import mosample.bo.lpoint.domain.send.LP7220;
import mosample.bo.lpoint.domain.send.LP7610;
import mosample.bo.lpoint.properties.ResponseType;
import mosample.bo.lpoint.properties.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LPointService {
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private TranService tranService;

//	@Scheduled(cron="*/30 * * * * *")
	public void heartbeat() {

		logger.info("L.POINT 9900 Heart Beat!");
		try {
			tranService.send9900();
		} catch (Exception e) {
			logger.debug("heartbeat @@" + e.getMessage());
		}
	}

	public LP7611RVo certification(LPointVo param) {
		try {
			logger.info("=========== certification(L.POINT 본인인증) begin ===========");
			String lp9900 = tranService.send9900();
			if ("SUCCESS".equals(lp9900)) {
			
			
				LP7610SVo lp7610 = new LP7610SVo();
				lp7610.setCardNo(param.getCardNo());
				lp7610.setCustNo(param.getCustNo());
				lp7610.setCustNoCode("1");
				lp7610.setPassword(param.getPassword());
				lp7610.setFiller("");

				Tran7610 tran7610 = tranService.send7610(lp7610);

				logger.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tran7610));


				LP7611RVo lp7611 = (LP7611RVo) tran7610.getResult();
	
				if (!ResponseType.SUCCESS.getCode().equals(tran7610.getHeader().getResponseCode())) {
					logger.error("[본인인증 {} 회 실패] {} - {}", lp7611.getFailCount(), lp7611.getCardNo().substring(0, 4) + "-****-****" + lp7611.getCardNo().substring(13, 16), "LPOINT 인증에 실패 하였습니다. ");
					return lp7611;
				}
				logger.info("=========== certification(L.POINT 본인인증 성공) end   ===========");
				
				return lp7611;
			} else {
				LP7611RVo lp7611 = new LP7611RVo();
				lp7611.setResponseCode("6");
				lp7611.setResponseMsg("본인인증 통신상태 이상");
				return lp7611;
			}
		} catch (Exception e) {
			// LPoint 사용 처리 결과  오류
			logger.error("[{}] {}", "인증실패", e.getMessage());
			return null;
		}
	}

	public LP7001RVo checkLpoint(LPointVo param) throws Exception {
		try {
			logger.info("=========== checkLpoint begin111 ===========");
			String lp9900 = tranService.send9900();
			String cardNo = param.getCardNo();
			String custNo = param.getCustNo();
			logger.info("카드번호: {}, 고객번호: {}.", cardNo, custNo);
			
			if("nullnullnullnull".equals(cardNo)) {
				cardNo = "";
			}
			
			logger.info("카드번호: {}, 고객번호: {}.", cardNo, custNo);
			if ("SUCCESS".equals(lp9900)) {
				LP7000SVo lp7000 = new LP7000SVo();
				lp7000.setCardNo(cardNo);
				lp7000.setCustNo(custNo);
				
				LP7001RVo lp7001 = tranService.send7000(lp7000);

				logger.info("=========== checkLpoint end ===========");
				return lp7001;
			} else {
				logger.error("9900 전문 실패");
				throw new Exception("9900 전문 실패");
			}
		} catch (Exception e) {
			// LPoint 조회 결과 오류
			logger.error("[{}] {}", "checkLpoint 실패", e.getMessage());
			throw e;
		}
	}
	
	public StatusCode usePointUsingCustNo(LPointVo param) throws Exception {
		logger.info("Parameter: cardNo: {}, custNo: {}, password: {}, usePoint: {}", 
				param.getCardNo(), param.getCustNo(), param.getPassword(), param.getUsePoint());
		
		LP7611RVo lp7611 = certification(param);
		if(lp7611 == null) {
			logger.info("7610 전문 실패 (본인 인증)");
			return StatusCode.USE_FAIL;
		}
		logger.info("7610 전문 성공 (본인 인증)");
		
		String cardNo = lp7611.getCardNo();
		logger.info("7610 전문 결과 카드번호: {}.", cardNo);

		param.setCardNo(cardNo);

		return useLPoint(param);
	}

	public StatusCode useLPoint(LPointVo param) throws Exception {
		// 1. LPoint 사용 처리
		try {
			LP7210SVo lp7210 = new LP7210SVo();
			lp7210.setCardNo(param.getCardNo());
			lp7210.setPassword(param.getPassword());
			lp7210.setUsePoint(param.getUsePoint());
			lp7210.setMConfirmNo(tranService.makeConfirmNo());
			Tran7210 tran7210 = tranService.send7210(lp7210);

			// LPoint 사용 처리 결과  오류
			if (!ResponseType.SUCCESS.getCode().equals(tran7210.getHeader().getResponseCode())) {
				logger.error("[{}] {}", StatusCode.USE_FAIL.getMessage(), ResponseType.getResponseType(tran7210.getHeader().getResponseCode()).getMessage());
				return StatusCode.USE_FAIL;
			}
			LP7211RVo result = (LP7211RVo) tran7210.getResult();
			logger.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tran7210));
			logger.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
			param.setTrackingNo(tran7210.getHeader().getTrackingNo());		// 추적번호
			param.setMConfirmNo(result.getMConfirmNo());					// 제휴사 승인번호
			param.setDealDate(result.getDealDate());						// 거래일자
			param.setDealTime(result.getDealTime());						// 거래시간
			param.setConfirmNo(result.getConfirmNo());						// 승인번호
			param.setConfirmDate(result.getConfirmDate());					// 승인일자
			param.setConfirmTime(result.getConfirmTime());					// 승인시간
			param.setUsePoint(result.getUsePoint());						// L.POINT 사용금액

		} catch (Exception e) {
			logger.error("[{}] {}", StatusCode.USE_FAIL.getMessage(), e.getMessage());
			return StatusCode.USE_FAIL;
		}

		// 2. 각 시스템에 L.Point 사용 처리

		return StatusCode.USE_SUCCESS;
	}


	public StatusCode cancelLPoint(LPointVo param) throws Exception {
		// 1. LPoint 취소 처리
		try {
			LP7220SVo lp7220 = new LP7220SVo();
			lp7220.setCardNo(param.getCardNo());
			lp7220.setPassword(param.getPassword());
			lp7220.setMConfirmNo(tranService.makeConfirmNo());
			lp7220.setUsePoint(param.getUsePoint());
			lp7220.setOriginDealYn("1");
			lp7220.setOriginDealtype("2");
			lp7220.setOriginDealConfirmNo(param.getMConfirmNo());
			lp7220.setOriginDealDate(param.getDealDate());
			Tran7220 tran7220 = tranService.send7220(lp7220);

			logger.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tran7220));
			// LPoint 취소 처리 결과  오류
			if (!ResponseType.SUCCESS.getCode().equals(tran7220.getHeader().getResponseCode())) {
				logger.error("[{}] {}", StatusCode.USE_FAIL.getMessage(), ResponseType.getResponseType(tran7220.getHeader().getResponseCode()).getMessage());
				return StatusCode.USE_FAIL;
			}
			LP7221RVo result = (LP7221RVo) tran7220.getResult();
			
			logger.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
			param.setTrackingNo(tran7220.getHeader().getTrackingNo());		// 추적번호
			
			param.setConfirmNo(result.getConfirmNo());						// 취소승인번호
			param.setDealDate(result.getDealDate());						// 거래일자
			param.setDealTime(result.getDealTime());						// 거래시간
			param.setConfirmDate(result.getConfirmDate());					// 승인일자
			param.setConfirmTime(result.getConfirmTime());					// 승인시간
			param.setUsePoint(result.getUsePoint());						// L.POINT 사용금액
			param.setOriginDealConfirmNo(param.getMConfirmNo());			// 원거래 승인번호
			param.setMConfirmNo(result.getMConfirmNo());					// 제휴사 승인번호

		} catch (Exception e) {
			logger.error("[{}] {}", StatusCode.USE_FAIL.getMessage(), e.getMessage());
			return StatusCode.USE_FAIL;
		}

		// 2. 각 시스템에 L.Point 사용 취소 처리

		return StatusCode.CANCEL_SUCCESS;
	}
}


