package dcx.lpoint.rvo;


import mosample.bo.lpoint.tran.TranDeserializer;
import mosample.bo.lpoint.util.TranUtils;

/**
 * 온라인몰 취소 승인
 */
public class LP7221RVo implements TranDeserializer {

	private static final long serialVersionUID = 7729552258477030770L;

	private String cardNo;						// 카드번호							AN	16		M	M	요청전문과 동일
	private String password;					// 멤버스포인트사용비밀번호			AN	40		M	M	요청전문과 동일
	private String filler1;						// FILLER 							AN	1				요청전문과 동일
	private String mid;							// 제휴가맹점번호					AN	10		M	M	요청전문과 동일
	private String mno;							// 점포번호							AN	4		M	M	요청전문과 동일
	private String mConfirmNo;					// 제휴사승인번호					AN	19		M	M	요청전문과 동일
	private String dealDate;					// 거래일자							AN	8		M	M	요청전문과 동일
	private String dealTime;					// 거래시간							AN	6		M	M	요청전문과 동일
	private String custNo;						// 고객번호							AN	10				멤버스고객번호
	private String confirmNo;					// 승인번호							N	9				운영사 승인번호
	private String confirmDate;					// 승인일자							AN	8				운영사 승인일자
	private String confirmTime;					// 승인시간							AN	6				운영사 승인시간
	private int usePoint; 						// 금회사용취소포인트				N	13				사용 및 취소 포인트
	private String firstPointYn;				// 첫거래포인트발생여부				AN	1		M		"1" : Y, "0" : N
	private int firstPoint; 					// 첫거래포인트						N	9
	private String birthPointYn; 				// 생일포인트발생여부				AN	1		M		"1" : Y, "0" : N
	private int birthPoint; 					// 생일포인트						N	9
	private String wedingPointYn; 				// 결혼기념일포인트발생여부			AN	1		M		"1" : 발생, "0" : 변동없음
	private int wedingPoint; 					// 결혼기념일포인트					N	9				공백
	private int eventPoint; 					// 이벤트포인트						N	9
	private int mEventPoint; 					// 운영사이벤트포인트				N	9
	private String useablePoint; 				// 가용포인트						N	9
	private String remainPointFlag; 			// 잔여포인트부호					A	1				"+" 또는 "-"
	private int remainPoint; 					// 잔여포인트						N	9
	private String mRemainPointFlag;			// 제휴사잔여포인트부호				A	1				"+" 또는 "-"
	private int mRemainPoint; 					// 제휴사잔여포인트					N	9
	private String message;						// 메시지1							A	64				"응답상세코드(3)+Space(1)+ 메시지내용(60)"
	private String filler;						// FILLER							A	300

	public LP7221RVo() {

	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getMConfirmNo() {
		return mConfirmNo;
	}

	public void setMConfirmNo(String mConfirmNo) {
		this.mConfirmNo = mConfirmNo;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getConfirmNo() {
		return confirmNo;
	}

	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
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

	public int getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	public String getFirstPointYn() {
		return firstPointYn;
	}

	public void setFirstPointYn(String firstPointYn) {
		this.firstPointYn = firstPointYn;
	}

	public int getFirstPoint() {
		return firstPoint;
	}

	public void setFirstPoint(int firstPoint) {
		this.firstPoint = firstPoint;
	}

	public String getBirthPointYn() {
		return birthPointYn;
	}

	public void setBirthPointYn(String birthPointYn) {
		this.birthPointYn = birthPointYn;
	}

	public int getBirthPoint() {
		return birthPoint;
	}

	public void setBirthPoint(int birthPoint) {
		this.birthPoint = birthPoint;
	}

	public String getWedingPointYn() {
		return wedingPointYn;
	}

	public void setWedingPointYn(String wedingPointYn) {
		this.wedingPointYn = wedingPointYn;
	}

	public int getWedingPoint() {
		return wedingPoint;
	}

	public void setWedingPoint(int wedingPoint) {
		this.wedingPoint = wedingPoint;
	}

	public int getEventPoint() {
		return eventPoint;
	}

	public void setEventPoint(int eventPoint) {
		this.eventPoint = eventPoint;
	}

	public int getmEventPoint() {
		return mEventPoint;
	}

	public void setmEventPoint(int mEventPoint) {
		this.mEventPoint = mEventPoint;
	}

	public String getUseablePoint() {
		return useablePoint;
	}

	public void setUseablePoint(String useablePoint) {
		this.useablePoint = useablePoint;
	}

	public String getRemainPointFlag() {
		return remainPointFlag;
	}

	public void setRemainPointFlag(String remainPointFlag) {
		this.remainPointFlag = remainPointFlag;
	}

	public int getRemainPoint() {
		return remainPoint;
	}

	public void setRemainPoint(int remainPoint) {
		this.remainPoint = remainPoint;
	}

	public String getmRemainPointFlag() {
		return mRemainPointFlag;
	}

	public void setmRemainPointFlag(String mRemainPointFlag) {
		this.mRemainPointFlag = mRemainPointFlag;
	}

	public int getmRemainPoint() {
		return mRemainPoint;
	}

	public void setmRemainPoint(int mRemainPoint) {
		this.mRemainPoint = mRemainPoint;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		System.arraycopy(bytes, 73 + 2, source, 0, bytes.length - 2 - 73 - 1);
		int pos = 0;
		byte[] cardNoBytes = 				new byte[16];
		byte[] passwordBytes = 				new byte[40];
		byte[] filler1Bytes = 				new byte[1];
		byte[] midBytes = 					new byte[10];
		byte[] mnoBytes = 					new byte[4];
		byte[] mConfirmNoBytes = 			new byte[19];
		byte[] dealDateBytes = 				new byte[8];
		byte[] dealTimeBytes = 				new byte[6];
		byte[] custNoBytes = 				new byte[10];
		byte[] confirmNoBytes = 			new byte[9];
		byte[] confirmDateBytes = 			new byte[8];
		byte[] confirmTimeBytes = 			new byte[6];
		byte[] usePointBytes = 				new byte[13];
		byte[] firstPointYnBytes = 			new byte[1];
		byte[] firstPointBytes = 			new byte[9];
		byte[] birthPointYnBytes = 			new byte[1];
		byte[] birthPointBytes = 			new byte[9];
		byte[] wedingPointYnBytes = 		new byte[1];
		byte[] wedingPointBytes = 			new byte[9];
		byte[] eventPointBytes = 			new byte[9];
		byte[] mEventPointBytes = 			new byte[9];
		byte[] useablePointBytes = 			new byte[9];
		byte[] remainPointFlagBytes = 		new byte[1];
		byte[] remainPointBytes = 			new byte[9];
		byte[] mRemainPointFlagBytes = 		new byte[1];
		byte[] mRemainPointBytes = 			new byte[9];
		byte[] messageBytes = 				new byte[64];

		System.arraycopy(source, pos, cardNoBytes,				0, 16);	pos = pos + 16;
		System.arraycopy(source, pos, passwordBytes,			0, 40);	pos = pos + 40;
		System.arraycopy(source, pos, filler1Bytes,				0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, midBytes,					0, 10);	pos = pos + 10;
		System.arraycopy(source, pos, mnoBytes,					0, 4);		pos = pos + 4;
		System.arraycopy(source, pos, mConfirmNoBytes,			0, 19);	pos = pos + 19;
		System.arraycopy(source, pos, dealDateBytes,			0, 8);		pos = pos + 8;
		System.arraycopy(source, pos, dealTimeBytes,			0, 6);		pos = pos + 6;
		System.arraycopy(source, pos, custNoBytes,				0, 10);	pos = pos + 10;
		System.arraycopy(source, pos, confirmNoBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, confirmDateBytes,			0, 8);		pos = pos + 8;
		System.arraycopy(source, pos, confirmTimeBytes,			0, 6);		pos = pos + 6;
		System.arraycopy(source, pos, usePointBytes,			0, 13);	pos = pos + 13;
		System.arraycopy(source, pos, firstPointYnBytes,		0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, firstPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, birthPointYnBytes,		0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, birthPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, wedingPointYnBytes,		0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, wedingPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, eventPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, mEventPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, useablePointBytes,		0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, remainPointFlagBytes,		0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, remainPointBytes,			0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, mRemainPointFlagBytes,	0, 1);		pos = pos + 1;
		System.arraycopy(source, pos, mRemainPointBytes,		0, 9);		pos = pos + 9;
		System.arraycopy(source, pos, messageBytes,				0, 64);	pos = pos + 64;

		this.cardNo				= TranUtils.getString(cardNoBytes);
		this.password			= TranUtils.getString(passwordBytes);
		this.filler1			= TranUtils.getString(filler1Bytes);
		this.mid				= TranUtils.getString(midBytes);
		this.mno				= TranUtils.getString(mnoBytes);
		this.mConfirmNo			= TranUtils.getString(mConfirmNoBytes);
		this.dealDate			= TranUtils.getString(dealDateBytes);
		this.dealTime			= TranUtils.getString(dealTimeBytes);
		this.custNo				= TranUtils.getString(custNoBytes);
		this.confirmNo			= TranUtils.getString(confirmNoBytes);
		this.confirmDate		= TranUtils.getString(confirmDateBytes);
		this.confirmTime		= TranUtils.getString(confirmTimeBytes);
		this.usePoint			= TranUtils.getInt(usePointBytes);
		this.firstPointYn		= TranUtils.getString(firstPointYnBytes);
		this.firstPoint			= TranUtils.getInt(firstPointBytes);
		this.birthPointYn		= TranUtils.getString(birthPointYnBytes);
		this.birthPoint			= TranUtils.getInt(birthPointBytes);
		this.wedingPointYn		= TranUtils.getString(wedingPointYnBytes);
		this.wedingPoint		= TranUtils.getInt(wedingPointBytes);
		this.eventPoint			= TranUtils.getInt(eventPointBytes);
		this.mEventPoint		= TranUtils.getInt(mEventPointBytes);
		this.useablePoint		= TranUtils.getString(useablePointBytes);
		this.remainPointFlag	= TranUtils.getString(remainPointFlagBytes);
		this.remainPoint		= TranUtils.getInt(remainPointBytes);
		this.mRemainPointFlag	= TranUtils.getString(mRemainPointFlagBytes);
		this.mRemainPoint		= TranUtils.getInt(mRemainPointBytes);
		this.message			= TranUtils.getString(messageBytes);
	}
}
