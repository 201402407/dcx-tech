package dcx.lpoint.rvo;


import mosample.bo.lpoint.tran.TranDeserializer;
import mosample.bo.lpoint.util.TranUtils;

public class LP7611RVo implements TranDeserializer {

	private static final long serialVersionUID = 5770755814263876626L;

	private String cardNo;						// 카드번호				AN	16		M
	private String custNoCode;					// 고객번호구분코드		A	1			- 1: 멤버스고객번호, 2: 롯데카드고객번호 default 1
	private String custNo;						// 고객번호				AN	11			- 멤버스고객번호 10자리, 롯데카드고객번호 11자리
	private String responseCode;				// 응답코드				A	1		M	- 0:성공, 1:비밀번호오류,2:비밀번호등록고객아님,3:비밀번호오류횟수초과,4:해당고객없음,5:카드번호오류,6:기타오류
	private String responseMsg;					// 응답 메시지			A	64
	private String confirmDate;					// 승인일자				AN	8			YYYYMMDD
	private String confirmTime;					// 승인시간				AN	6			hhmmss
	private int remainPoint;					// 잔여포인트			N	9
	private int useablePoint;					// 가용포인트			N	9
	private String failCount;					// 비밀번호 오류횟수	A	1			5회 오류시 비밀번호 잠금
	private String filler;						// FILLER				A	40

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCustNoCode() {
		return custNoCode;
	}

	public void setCustNoCode(String custNoCode) {
		this.custNoCode = custNoCode;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public int getRemainPoint() {
		return remainPoint;
	}

	public void setRemainPoint(int remainPoint) {
		this.remainPoint = remainPoint;
	}

	public int getUseablePoint() {
		return useablePoint;
	}

	public void setUseablePoint(int useablePoint) {
		this.useablePoint = useablePoint;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public void deserialize(byte[] bytes) throws Exception {
		byte[] source = new byte[bytes.length - 2 - 73 - 1];
		System.arraycopy(bytes, 73 + 2, source, 0, bytes.length - 2 - 73 - 1 );

		int pos = 0;

		byte[] cardNoBytes = 				new byte[16];
		byte[] custNoCodeBytes = 			new byte[1];
		byte[] custNoBytes = 				new byte[11];
		byte[] responseCodeBytes = 			new byte[1];
		byte[] responseMsgBytes = 			new byte[64];
		byte[] confirmDateBytes = 			new byte[8];
		byte[] confirmTimeBytes = 			new byte[6];
		byte[] remainPointBytes = 			new byte[9];
		byte[] useablePointBytes = 			new byte[9];
		byte[] failCountBytes = 			new byte[1];

		System.arraycopy(source, pos, cardNoBytes,			0, 16);	pos = pos + 16;
		System.arraycopy(source, pos, custNoCodeBytes,		0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, custNoBytes,			0, 11);	pos = pos + 11;
		System.arraycopy(source, pos, responseCodeBytes,	0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, responseMsgBytes,		0, 64);	pos = pos + 64;
		System.arraycopy(source, pos, confirmDateBytes,		0, 8);		pos = pos + 8;
		System.arraycopy(source, pos, confirmTimeBytes,		0, 6);		pos = pos + 6;
		System.arraycopy(source, pos, remainPointBytes,		0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, useablePointBytes,	0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, failCountBytes,		0, 1);

		this.cardNo				= TranUtils.getString(cardNoBytes);
		this.custNoCode			= TranUtils.getString(custNoCodeBytes);
		this.custNo				= TranUtils.getString(custNoBytes);
		this.responseCode		= TranUtils.getString(responseCodeBytes);
		this.responseMsg		= TranUtils.getString(responseMsgBytes);
		this.confirmDate		= TranUtils.getString(confirmDateBytes);
		this.confirmTime		= TranUtils.getString(confirmTimeBytes);
		this.remainPoint		= TranUtils.getInt(remainPointBytes);
		this.useablePoint		= TranUtils.getInt(useablePointBytes);
		this.failCount			= TranUtils.getString(failCountBytes);
		//this.setFiller(new String(fillerBytes, "utf-8"));
	}
}

