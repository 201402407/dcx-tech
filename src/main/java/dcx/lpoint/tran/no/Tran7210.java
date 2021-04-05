package dcx.lpoint.tran.no;

import dcx.lpoint.tran.BaseTran;
import dcx.lpoint.tran.TranHeader;

public class Tran7210 extends BaseTran {

	public Tran7210() {
		TranHeader header = new TranHeader("7210");
		this.setHeader(header);
	}
}
