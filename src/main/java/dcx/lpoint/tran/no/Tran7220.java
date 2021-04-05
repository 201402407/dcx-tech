package dcx.lpoint.tran.no;

import dcx.lpoint.tran.BaseTran;
import dcx.lpoint.tran.TranHeader;

public class Tran7220 extends BaseTran {

	public Tran7220() {
		TranHeader header = new TranHeader("7220");
		this.setHeader(header);
	}
}
