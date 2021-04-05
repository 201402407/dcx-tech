package dcx.lpoint.tran.no;

import dcx.lpoint.tran.BaseTran;
import dcx.lpoint.tran.TranHeader;

public class Tran7610 extends BaseTran {

	public Tran7610() {
		TranHeader header = new TranHeader("7610");
		this.setHeader(header);
	}
}
