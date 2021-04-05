package dcx.lpoint.service;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dcx.lpoint.tran.TranGateway;

@Service("tranService")
public class TranService {
	private final Logger logger = LoggerFactory.getLogger("LPOINT");

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

	public LP7001 send7000(LP7000 param) throws Exception {

		Tran7000 tran = new Tran7000();
		tran.setInput(param);
		
		logger.info("Tran7000 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(236);
		logger.info("Tran7000 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7000 resultTran = new Tran7000();
		LP7001 result = new LP7001();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		//resultTran.setResult(result);
		return result;
	}

	public Tran7610 send7610(LP7610 param) throws Exception {

		Tran7610 tran = new Tran7610();
		tran.setInput(param);

		logger.info("Tran7610 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(108);
		logger.info("Tran7610 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7610 resultTran = new Tran7610();
		LP7611 result = new LP7611();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		return resultTran;
	}

	public Tran7210 send7210(LP7210 param) throws Exception {

		Tran7210 tran = new Tran7210();
		tran.setInput(param);
		
		logger.info("Tran7210 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(386);
		logger.info("Tran7210 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7210 resultTran = new Tran7210();
		LP7211 result = new LP7211();
		resultTran.getHeader().deserialize(resultPayload);
		result.deserialize(resultPayload);
		resultTran.setResult(result);
		return resultTran;
	}

	public Tran7220 send7220(LP7220 param) throws Exception {

		Tran7220 tran = new Tran7220();
		tran.setInput(param);
		
		logger.info("Tran7220 getTrackingNo 111 = " +tran.getHeader().getTrackingNo());
		tran.getHeader().setTrackingNoSuffix(makeSuffixNo());
		tran.getHeader().setBodyLengh(386);
		logger.info("Tran7220 getTrackingNo 222 = " +tran.getHeader().getTrackingNo());

		byte[] resultPayload = gateway.send(tran.serialize());
		Tran7220 resultTran = new Tran7220();
		LP7221 result = new LP7221();
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


