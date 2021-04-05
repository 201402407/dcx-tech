package dcx.lpoint.svo;

import dcx.comn.exception.TranTypeException;
import dcx.lpoint.tran.TranSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LP9900SVo implements TranSerializer {

	private static final long serialVersionUID = 642029625401317210L;

	@Override
	public byte[] serialize() throws TranTypeException {
		return null;
	}
}
