package dcx.lpoint.tran;

import java.io.Serializable;

public interface Tran extends Serializable {

	void setHeader(TranHeader tranHeader);
	void setInput(TranSerializer serializer);
	void setResult(TranDeserializer deserializer);

	TranHeader getHeader();
	TranDeserializer getResult();
	byte[] serialize() throws Exception;
}
