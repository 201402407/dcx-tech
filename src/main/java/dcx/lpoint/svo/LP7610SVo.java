package dcx.lpoint.svo;

import com.google.common.primitives.Bytes;
import mosample.bo.lpoint.exception.TranTypeException;
import mosample.bo.lpoint.properties.DataType;
import mosample.bo.lpoint.tran.TranSerializer;
import mosample.bo.lpoint.util.TranUtils;


public class LP7610SVo implements TranSerializer {

	private static final long serialVersionUID = 7680554029303545972L;

	private String cardNo;						// 카드번호				AN	16		M
	private String custNoCode;					// 고객번호구분코드		A	1			- 1: 멤버스고객번호, 2: 롯데카드고객번호 default 1
	private String custNo;						// 고객번호				AN	11		M	- 멤버스고객번호 10자리, 롯데카드고객번호 11자리
	private String password;					// 비밀번호				AN	40		M	- MD5 암호화 (Triple DES)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
				TranUtils.getBytes(this.cardNo, 		DataType.AN,	16),
				TranUtils.getBytes(this.custNoCode, 	DataType.A,		1),
				TranUtils.getBytes(this.custNo, 		DataType.AN,	11),
				TranUtils.getEncBytes(this.password, 		DataType.AN,	40),
				TranUtils.getBytes(this.filler, 		DataType.A,		40)
		);
	}
}
