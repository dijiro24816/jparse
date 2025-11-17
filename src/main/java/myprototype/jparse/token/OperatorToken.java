package myprototype.jparse.token;

public class OperatorToken extends Token {

	public String symbols;
	
	public OperatorToken() {}
	
	public OperatorToken(int beg, int end) {
		super(beg, end);
	}

	public OperatorToken(int beg, int end, String symbols) {
		super(beg, end);
		this.symbols = symbols;
	}

	@Override
	public String toString() {
		return "OperatorToken [beg=" + beg + " end=" + end + " symbol=" + symbols + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof OperatorToken tok
				? super.equals(obj) && this.symbols.equals(tok.symbols)
				: false;
	}

}
