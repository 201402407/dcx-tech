package dcx.lpoint.svo;

import com.google.common.primitives.Bytes;
import mosample.bo.lpoint.exception.TranTypeException;
import mosample.bo.lpoint.properties.DataType;
import mosample.bo.lpoint.tran.TranSerializer;
import mosample.bo.lpoint.util.TranUtils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 온라인몰 취소 승인
 */
public class LP7220SVo implements TranSerializer {

	private static final long serialVersionUID = -3202576603803092970L;

	private String wcc;							// WCC									AN	1		M	M	"3":Key In
	private String confirmType;					// 승인요청방식							AN	1		M	M	"3":온라인
	private String cardNo;						// 카드번호								AN	16		M		입력 받은 카드번호
	private String password;					// 멤버스포인트사용비밀번호				AN	40		M		MD5로 암호화된 값
	private String filler1 = " ";				// FILLER								AN	1		M	M
	private String mId;							// 제휴가맹점번호						AN	10		M	M
	private String mNo;							// 점포번호								AN	4		M	M
	private String mConfirmNo;					// 제휴사승인번호						AN	19		M	M
	private String dealDate;					// 거래일자								AN	8		M	M
	private String dealTime;					// 거래시간								AN	6		M	M
	private String dealType;					// 거래구분								AN	2		M	M	"20" : 포인트사용, "30" : 포인트조정

	// 거래구분: 20 -> 200:대금결제, 201:상품권구매, 202:사은품구매, 203:포인트기부, 204:카드대금결제, 205:연회비결제, 206:연회비결제, 207:이벤트참여, 208:SMS수수료
	// 거래구분: 30 -> 220:원거래없는 포인트 사용취소
	private String dealCode;					// 거래사유								AN	3		M	M

	private String useType; 					// 사용구분								AN	1		M	M	1 : 정상, 2 : 취소, 3 : 오타, 4 : 반오타(취소의 취소)"
	private String pointFlag;					// 포인트차감사용플래그					AN	1		M	M	1:일반거래, 2:요청포인트 이내 가용포인트 전부사용
	private int usePoint; 						// 금회사용포인트						N	13		M
	private String firstPointYn;				// 첫거래포인트발생여부					AN	1		M		"1" : Y, "0" : N
	private int firstPoint; 					// 첫거래포인트							N	9
	private String birthPointYn; 				// 생일포인트발생여부					AN	1		M		"1" : Y, "0" : N
	private int birthPoint; 					// 생일포인트							N	9
	private String wedingPointYn; 				// 결혼기념일포인트발생여부				AN	1		M		"1" : 발생, "0" : 변동없음
	private int wedingPoint; 					// 결혼기념일포인트						N	9				공백
	private String cashReceipt; 				// 현금영수증자동발행신청				AN	1		M		0:취소, 1:신청(소득공제용), 2:신청(지출증빙용), 9:변동없음
	private String originDealYn; 				// 원거래정보유무						AN	1		M		"1 : 원거래정보있음, 0 : 원거래정보없음"
	private String originDealtype; 				// 원거래정보구분						AN	1				"1 : 운영사 원승인정보, 2 : 제휴사 원승인정보"
	private String originDealConfirmNo;			// 원거래승인번호						AN	19				"원거래정보구분, 1이면 운영사 원승인번호, 2이면 원제휴사승인번호 "
	private String originDealDate; 				// 원거래일자							AN	8				"원거래정보구분, 1이면 운영사 원승인일자, 2이면 제휴사 원거래일자"
	private String filler;						// FILLER								A	200

	public LP7220SVo() {
		this.wcc = "3";
		this.confirmType = "3";
		this.mId = "L380000002";
//		this.mNo = "I500";
		this.mNo = "0000";
		this.dealType = "20";
		this.dealCode = "220";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		this.dealDate = dateFormat.format(now);
		this.dealTime = timeFormat.format(now);
		this.useType = "2";
		this.pointFlag = "1";
		this.birthPointYn = "0";
		this.originDealtype = "0";
		this.firstPointYn = "0";
		this.wedingPointYn = "0";
		this.cashReceipt = "9";
		this.originDealYn = "1";
		this.originDealConfirmNo = "";
		this.originDealDate = "";
	}

	public String getWcc() {
		return wcc;
	}

	public void setWcc(String wcc) {
		this.wcc = wcc;
	}

	public String getConfirmType() {
		return confirmType;
	}

	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
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

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
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

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getPointFlag() {
		return pointFlag;
	}

	public void setPointFlag(String pointFlag) {
		this.pointFlag = pointFlag;
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

	public String getCashReceipt() {
		return cashReceipt;
	}

	public void setCashReceipt(String cashReceipt) {
		this.cashReceipt = cashReceipt;
	}

	public String getOriginDealYn() {
		return originDealYn;
	}

	public void setOriginDealYn(String originDealYn) {
		this.originDealYn = originDealYn;
	}

	public String getOriginDealtype() {
		return originDealtype;
	}

	public void setOriginDealtype(String originDealtype) {
		this.originDealtype = originDealtype;
	}

	public String getOriginDealConfirmNo() {
		return originDealConfirmNo;
	}

	public void setOriginDealConfirmNo(String originDealConfirmNo) {
		this.originDealConfirmNo = originDealConfirmNo;
	}

	public String getOriginDealDate() {
		return originDealDate;
	}

	public void setOriginDealDate(String originDealDate) {
		this.originDealDate = originDealDate;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public byte[] serialize() throws TranTypeException {
		return Bytes.concat(
				TranUtils.getBytes(this.wcc,							DataType.AN,	1),
				TranUtils.getBytes(this.confirmType,					DataType.AN,	1),
				TranUtils.getBytes(this.cardNo,							DataType.AN,	16),
				TranUtils.getEncBytes(this.password,					DataType.AN,	40),
				TranUtils.getBytes(this.filler1,						DataType.AN,	1),
				TranUtils.getBytes(this.mId,							DataType.AN,	10),
				TranUtils.getBytes(this.mNo,							DataType.AN,	4),
				TranUtils.getBytes(this.mConfirmNo,						DataType.AN,	19),
				TranUtils.getBytes(this.dealDate,						DataType.AN,	8),
				TranUtils.getBytes(this.dealTime,						DataType.AN,	6),
				TranUtils.getBytes(this.dealType,						DataType.AN,	2),
				TranUtils.getBytes(this.dealCode,						DataType.AN,	3),
				TranUtils.getBytes(this.useType,						DataType.AN,	1),
				TranUtils.getBytes(this.pointFlag,						DataType.AN,	1),
				TranUtils.getBytes(this.usePoint,						DataType.N,		13),
				TranUtils.getBytes(this.firstPointYn,					DataType.AN,	1),
				TranUtils.getBytes(this.firstPoint,						DataType.N,		9),
				TranUtils.getBytes(this.birthPointYn,					DataType.AN,	1),
				TranUtils.getBytes(this.birthPoint,						DataType.N,		9),
				TranUtils.getBytes(this.wedingPointYn,					DataType.AN,	1),
				TranUtils.getBytes(this.wedingPoint,					DataType.N,		9),
				TranUtils.getBytes(this.cashReceipt,					DataType.AN,	1),
				TranUtils.getBytes(this.originDealYn,					DataType.AN,	1),
				TranUtils.getBytes(this.originDealtype,					DataType.AN,	1),
				TranUtils.getBytes(this.originDealConfirmNo,			DataType.AN,	19),
				TranUtils.getBytes(this.originDealDate,					DataType.AN,	8),
				TranUtils.getBytes(this.filler,							DataType.A,		200)
		);
	}
}
