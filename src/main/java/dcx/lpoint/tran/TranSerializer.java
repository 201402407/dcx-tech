package dcx.lpoint.tran;

import java.io.Serializable;

public interface TranSerializer extends Serializable {
	byte[] serialize() throws Exception;
}
