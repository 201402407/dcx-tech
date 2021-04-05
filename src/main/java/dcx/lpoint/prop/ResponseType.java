package dcx.lpoint.prop;

public enum ResponseType {

	SUCCESS	("00", "정상"),
	ERROR_SYSTEM	("22", "시스템 장애"),
	REJECT	("44", "승인 거절"),
	ERROR_TRAN	("77", "전문 오류"),
	ERROR_DB	("80", "DB 미등록"),
	ERROR_LPOINT_DB	("88", "운영사 DBMS 장애"),
	ERROR_92	("92", "[92] Control 오류 "),
	ERROR_99	("99", "[99] Control 오류"),
	ERROR		("", "오류");

	private String code;
	private String message;

	ResponseType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResponseType getResponseType(String code) {
		for (ResponseType responseType  : ResponseType.values()) {
			if (responseType.getCode().equals(code)) {
				return responseType;
			}
		}
		return ERROR;
	}
}
