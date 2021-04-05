package dcx.lpoint.prop;

// 모든 항목의 기본 값은 숫자형(N)일 경우 '0'으로 문자숫자형(AN) 또는 문자형(A)일 경우 ' '(공백)으로 채워야 함
public enum DataType {

	N (
			'0', "숫자형"
	),
	AN (
			' ', "문자숫자형"
			//Character.MIN_VALUE, "문자숫자형"
	),
	A (
			' ', "문자형"
			//Character.MIN_VALUE, "문자형"
	);

	private char padChar;
	private String description;

	DataType(char padChar, String description) {
		this.padChar = padChar;
		this.description = description;
	}

	public char getPadChar() {
		return padChar;
	}

	public void setPadChar(char padChar) {
		this.padChar = padChar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
