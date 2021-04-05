/**
 * 통신(소켓, ... ?) 에 사용하는 추상클래스
 * Header, Java Object 값 직렬화
 * 
 */

package dcx.lpoint.tran;

import com.google.common.primitives.Bytes;

public abstract class BaseTran implements Tran {

	protected TranHeader header;
	protected TranSerializer serializer;
	protected TranDeserializer deserializer;

	@Override
	public void setHeader(TranHeader tranHeader) {
		this.header = tranHeader;
	}

	@Override
	public void setInput(TranSerializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public void setResult(TranDeserializer deserializer) {
		this.deserializer = deserializer;
	}

	@Override
	public TranHeader getHeader() {
		return header;
	}


	public TranSerializer getInput() {
		return this.serializer;
	}

	@Override
	public TranDeserializer getResult() {
		return this.deserializer;
	}

	@Override
	public byte[] serialize() throws Exception {
		if (serializer == null) {	// 9900
//			System.out.println("멍멍??");
			return header.serialize();
		} else {
//			System.out.println("멍멍" + serializer.serialize().length);
			return Bytes.concat(header.serialize(), serializer.serialize());
		}
	}
}
