package dcx.lpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import dcx.lpoint.service.LPointService;
import lombok.extern.slf4j.Slf4j;
import mosample.bo.lpoint.domain.condition.LPointCondition;
import mosample.bo.lpoint.domain.receive.LP7001;
import mosample.bo.lpoint.domain.receive.LP7611;
import mosample.bo.lpoint.properties.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/lpoint")
@RestController
@Slf4j
public class LPointController {

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	LPointService service;

	@RequestMapping("certification")
	public LP7611RVo certification(LPointVo condition) throws Exception {
		logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(condition));
		LP7611RVo result = service.certification(condition);
		if (result == null) {
			result = new LP7611RVo();
			result.setResponseCode("6");
			result.setResponseMsg("본인인증 오류가 일시 발생하였습니다. 잠시 후 다시 시도해주세요."); // 통신오류
		}
		return result;
	}

	@RequestMapping("checkLpoint")
	public @ResponseBody Map checkLpoint(LPointVo condition) throws Exception {
		logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(condition));
		Map response = new HashMap();
		try {
			LP7001RVo result = service.checkLpoint(condition);
			response = mapper.convertValue(result, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping("useLpointUsingCustNo")
	public StatusCode useLpointUsingCustNo(LPointVo condition) throws Exception {
		logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(condition));
		return service.usePointUsingCustNo(condition);
	}
	
	@RequestMapping("use")
	public StatusCode useLpoint(LPointVo condition) throws Exception {
		logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(condition));
		return service.useLPoint(condition);
	}

	@RequestMapping("cancel")
	public StatusCode cancelLpoint(LPointVo condition) throws Exception {
		logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(condition));
		return service.cancelLPoint(condition);
	}

}
