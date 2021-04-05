/**
 * 통신 상태 코드
 */

package dcx.lpoint.prop;

public enum StatusCode {
	CHECK_SUCCESS("조회 성공"),
	CHECK_FAIL("조회 실패"),
	USE_SUCCESS("사용 처리 성공"),
	USE_FAIL("사용 처리 실패"),
	USE_FAIL_HANG("G5 승인 실패로 인한 취소 승인 실패"),
	CANCEL_SUCCESS("취소 처리 성공"),
	CANCEL_FAIL_HANG("G5 승인 실패로 인한 취소 승인 실패");

	private String message;

	StatusCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
