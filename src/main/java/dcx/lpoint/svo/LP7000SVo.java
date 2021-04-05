// Send Value Object(전송 데이터. 가공 완료 후 전송하기 위한 데이터)
package dcx.lpoint.svo;

import com.google.common.primitives.Bytes;
import mosample.bo.lpoint.exception.TranTypeException;
import mosample.bo.lpoint.properties.DataType;
import mosample.bo.lpoint.tran.TranSerializer;
import mosample.bo.lpoint.util.TranUtils;

public class LP7000SVo implements TranSerializer {

	private static final long serialVersionUID = -3933435878451926209L;

	private String cardNo;						// 카드번호				AN	16		M
	private String custNo;						// 고객번호				AN	10		M
	private String mId = "L380000002";			// 제휴가맹점번호		AN	10		M	- 요청 가맹점번호
	private String filler;						// FILLER				A	200

	public LP7000SVo() {
		this.mId = "L380000002";
	}

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

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Override
	public byte[] serialize() throws TranTypeException {
		return Bytes.concat(TranUtils.getBytes(this.cardNo, 		DataType.AN,	16)
				, TranUtils.getBytes(this.custNo, 		DataType.AN,	10)
				, TranUtils.getBytes(this.mId, 			DataType.AN,	10)
				, TranUtils.getBytes(this.filler, 		DataType.A,		200)
		);
	}
}
