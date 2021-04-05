package dcx.comn.exception;

public class TranTypeException extends IllegalArgumentException {

	public TranTypeException() {
		super();
	}

	public TranTypeException(String s) {
		super (s);
	}

	static TranTypeException forInputString(String s) {
		return new TranTypeException("For input string: \"" + s + "\"");
	}
}
