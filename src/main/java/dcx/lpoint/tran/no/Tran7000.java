/**
 * 헤더에 알맞은 전문 번호를 넣어 통신객체 생성
 */

package dcx.lpoint.tran.no;

import dcx.lpoint.tran.BaseTran;
import dcx.lpoint.tran.TranHeader;

public class Tran7000 extends BaseTran {

	public Tran7000() {
		TranHeader header = new TranHeader("7000");
		this.setHeader(header);
	}
}
