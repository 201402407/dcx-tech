// VO : Request Object(가공 X, Read Only)

package dcx.lpoint.vo;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LPointVo implements Serializable {

	private static final long serialVersionUID = 7309037350199349365L;

	// 추적번호
	private String trackingNo;

	// 카드번호
	private String cardNo1;
	private String cardNo2;
	private String cardNo3;
	private String cardNo4;
	private String cardNo;

	// LPoint 회원번호
	private String custNo;

	// 제휴사 승인번호
	private String mConfirmNo;

	// 예약번호
	private String reservationNo;

	// 지점코드
	private String branchCd;

	// 거래일자
	private String dealDate;

	// 거래시간
	private String dealTime;

	// 승인번호
	private String confirmNo;

	// 승인일자
	private String confirmDate;

	// 승인시간
	private String confirmTime;

	// 사용포인트
	private int usePoint;

	// 원거래번호
	private String originDealConfirmNo;

	// 비밀번호
	private String password;


	public String getCardNo() {
		return StringUtils.isEmpty(this.cardNo) ? this.cardNo1 + this.cardNo2 + this.cardNo3 + this.cardNo4 : this.cardNo;
	}

	// RFC 사용 승인 처리용 Parameter 생성
	public Map<String, String> getRfcParamMap() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("originDealConfirmNo", this.originDealConfirmNo);	// 원거래 승인번호
		param.put("mConfirmNo", this.mConfirmNo);				// 제휴사 승인번호 - LPOINT 처리 후 전달
		param.put("reservationNo", this.reservationNo);			// 계약번호 - REV : 예약번호
		param.put("branchCd", this.branchCd);					// 수납부서 - REV : 지점코드
		param.put("branchCd", this.branchCd);					// 가맹점부서 - REV : 지점코드
		param.put("dealDate", this.dealDate);					// 거래일자 - LPOINT : 거래일자
		param.put("dealTime", this.dealTime);					// 거래시간 - LPOINT : 거래시간
		param.put("cardNo", getCardNo());						// 카드번호 - LPOINT : 카드번호 (없으면?)
		param.put("confirmNo", this.confirmNo);					// 승인번호 - LPOINT : 승인번호
		param.put("confirmDate", this.confirmDate);				// 승인일자 - LPOINT : 승인일자
		param.put("confirmTime", this.confirmTime);				// 승인시간 - LPOINT : 승인시간
		param.put("usePoint", this.usePoint + "");				// L.POINT 사용금액 보냄
		param.put("usePoint", this.usePoint + "");				// 매출금액 - L-POINT 사용금액 보냄
		param.put("trackingNo", this.trackingNo);				// 추적번호 - LPOINT 처리 후 전달
		return param;
	}
}
