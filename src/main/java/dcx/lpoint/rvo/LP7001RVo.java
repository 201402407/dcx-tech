package dcx.lpoint.rvo;


import mosample.bo.lpoint.tran.TranDeserializer;
import mosample.bo.lpoint.util.TranUtils;

public class LP7001RVo implements TranDeserializer {

	private static final long serialVersionUID = 2575053120703824340L;

	private String cardNo;						// 카드번호				AN	16		M
	private String custNo;						// 고객번호				AN	10		M
	private String mId;							// 제휴가맹점번호		AN	10		M
	private int useablePoint;					// 가용포인트			N	9		M	사용가능 포인트
	private String signPoint;					// 잔여포인트부호		A	1		M	"+" 또는 "-"
	private int point;							// 잔여포인트			N	9		M
	private String message;						// 메시지				A	64		M	"응답상세코드(3)+Space(1)+메시지내용(60)
	private String cardCode;					// 제휴카드코드			AN	10			신용카드상품종류코드
	private String cardGoodsCode;				// 멤버스카드상품코드	AN	10
	private String filler;						// FILLER				A	275

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getUseablePoint() {
		return useablePoint;
	}

	public void setUseablePoint(int useablePoint) {
		this.useablePoint = useablePoint;
	}

	public String getSignPoint() {
		return signPoint;
	}

	public void setSignPoint(String signPoint) {
		this.signPoint = signPoint;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardGoodsCode() {
		return cardGoodsCode;
	}

	public void setCardGoodsCode(String cardGoodsCode) {
		this.cardGoodsCode = cardGoodsCode;
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
		byte[] cardNoBytes = 			new byte[16];
		byte[] custNoBytes = 			new byte[10];
		byte[] mIdBytes = 				new byte[10];
		byte[] useablePointBytes = 		new byte[9];
		byte[] signPointBytes = 		new byte[1];
		byte[] pointBytes = 			new byte[9];
		byte[] messageBytes = 			new byte[64];
		byte[] cardCodeBytes = 			new byte[10];
		byte[] cardGoodsCodeBytes = 	new byte[10];

		System.arraycopy(source, pos, cardNoBytes, 0, 16);			pos = pos + 16;
		System.arraycopy(source, pos, custNoBytes, 0, 10);				pos = pos + 10;
		System.arraycopy(source, pos, mIdBytes, 0, 10);			pos = pos + 10;
		System.arraycopy(source, pos, useablePointBytes, 0, 9);			pos = pos + 9;
		System.arraycopy(source, pos, signPointBytes, 0, 1);			pos = pos + 1;
		System.arraycopy(source, pos, pointBytes, 0, 9);			pos = pos + 9;
		System.arraycopy(source, pos, messageBytes, 0, 64);			pos = pos + 64;
		System.arraycopy(source, pos, cardCodeBytes, 0, 10);		pos = pos + 10;
		System.arraycopy(source, pos, cardGoodsCodeBytes, 0, 10);			pos = pos + 10;

		this.cardNo				= TranUtils.getString(cardNoBytes);
		this.custNo				= TranUtils.getString(custNoBytes);
		this.mId				= TranUtils.getString(mIdBytes);
		this.useablePoint		= TranUtils.getInt(useablePointBytes);
		this.signPoint			= TranUtils.getString(signPointBytes);
		this.point				= TranUtils.getInt(pointBytes);
		this.message			= TranUtils.getString(messageBytes);
		this.cardCode			= TranUtils.getString(cardCodeBytes);
		this.cardGoodsCode		= TranUtils.getString(cardGoodsCodeBytes);
		//this.setFiller(new String(fillerBytes, "utf-8"));
	}
}
