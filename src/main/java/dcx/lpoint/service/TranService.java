/**
 * 통신에 사용하는 서비스
 * tranNo에 알맞은 전문 번호를 넣어 헤더를 생성한 후, 전송하는 로직
 * 
 */

package dcx.lpoint.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dcx.lpoint.rvo.LP7001RVo;
import dcx.lpoint.rvo.LP7211RVo;
import dcx.lpoint.rvo.LP7221RVo;
import dcx.lpoint.rvo.LP7611RVo;
import dcx.lpoint.svo.LP7000SVo;
import dcx.lpoint.svo.LP7210SVo;
import dcx.lpoint.svo.LP7220SVo;
import dcx.lpoint.svo.LP7610SVo;
import dcx.lpoint.tran.TranGateway;
import dcx.lpoint.tran.no.Tran7000;
import dcx.lpoint.tran.no.Tran7210;
import dcx.lpoint.tran.no.Tran7220;
import dcx.lpoint.tran.no.Tran7610;
import dcx.lpoint.tran.no.Tran9900;
import lombok.extern.slf4j.Slf4j;

@Service("tranService")
@Slf4j
public class TranService {

	@Autowired
	TranGateway gateway;

	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;

	JdbcTemplate jdbcTemplate;

	public TranService() {

	}

	public String send9900() throws Exception {
		Tran9900 tran = new Tran9900();
		byte[] result = gateway.send(tran.serialize());
		tran.getHeader().deserialize(result);
		return "SUCCESS";
	}

	public LP7001RVo send7000(LP7000SVo param) throws Exception {

		Tran7000 tran = new Tran7000();
		tran.setInput(param);
		
		log.info("Tran7000 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(236);
		log.info("Tran7000 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7000 resultTran = new Tran7000();
		LP7001RVo result = new LP7001RVo();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		//resultTran.setResult(result);
		return result;
	}

	public Tran7610 send7610(LP7610SVo param) throws Exception {

		Tran7610 tran = new Tran7610();
		tran.setInput(param);

		log.info("Tran7610 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(108);
		log.info("Tran7610 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7610 resultTran = new Tran7610();
		LP7611RVo result = new LP7611RVo();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		return resultTran;
	}

	public Tran7210 send7210(LP7210SVo param) throws Exception {

		Tran7210 tran = new Tran7210();
		tran.setInput(param);
		
		log.info("Tran7210 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(386);
		log.info("Tran7210 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7210 resultTran = new Tran7210();
		LP7211RVo result = new LP7211RVo();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		return resultTran;
	}

	public Tran7220 send7220(LP7220SVo param) throws Exception {

		Tran7220 tran = new Tran7220();
		tran.setInput(param);
		
		log.info("Tran7220 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(386);
		log.info("Tran7220 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7220 resultTran = new Tran7220();
		LP7221RVo result = new LP7221RVo();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		return resultTran;
	}

	public byte[] parse(byte[] output) throws Exception {
//		String hexText = new java.math.BigInteger(output).toString(16);
//		System.out.println("parse : [" + hexText + "]");
//
//		byte[] length = new byte[2];
//		byte[] header = new byte[73];
//		byte[] source = new byte[output.length - 2 - 73];
//		System.arraycopy(output, 0, length, 0, 2);
//		System.arraycopy(output, 2, header, 0, 73);
//		System.arraycopy(output, 2 + 73, source, 0, output.length - 2 - 73);

		return output;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return this.jdbcTemplate;
	}

	/**
	 * 제휴사 승인 번호 생성
	 * - 19자리 SEQUENCE CYCLE
	 * @return 승인번호
	 */
	public String makeConfirmNo() {

		return getJdbcTemplate().queryForObject("SELECT LPAD(SEQ_LPOINT.NEXTVAL, 19, '0') FROM DUAL", new Object[] {}, String.class);
	}

	/**
	 * 트랙킹 suffix 번호 생성
	 * - 6자리 SEQUENCE CYCLE
	 * @return suffix No
	 */
	public String makeSuffixNo() {
		return getJdbcTemplate().queryForObject("SELECT LPAD(SEQ_LPOINT_TRACKING.NEXTVAL, 6, '0') FROM DUAL", new Object[] {}, String.class);
	}
}


