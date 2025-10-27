package myprototype.jparse.token;

public final class OperatorToken extends Token {

	public String symbols;
	
	public OperatorToken(int beg, int end, String symbols) {
		super(beg, end);
		this.symbols = symbols;
	}

	@Override
	public String toString() {
		return "OperatorToken [beg=" + beg + " end=" + end + " symbol=" + symbols + "]";
	}
}
