package dcx.lpoint.tran;

import java.io.Serializable;

public interface TranDeserializer extends Serializable {

	void deserialize(byte[] bytes) throws Exception;
}
