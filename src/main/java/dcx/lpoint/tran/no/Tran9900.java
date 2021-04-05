package dcx.lpoint.tran.no;

import dcx.lpoint.tran.BaseTran;
import dcx.lpoint.tran.TranHeader;

public class Tran9900 extends BaseTran {

	public Tran9900() {
		TranHeader header = new TranHeader("9900");
		this.setHeader(header);
	}
}
