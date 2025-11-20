package myprototype.jparse.syntax.symbol.terminal;

public class OperatorToken extends Terminal {
	public OperatorToken() {}
	
	public OperatorToken(int beg, int end) {
		super(beg, end);
	}

	@Override
	public String toString() {
		return "OperatorToken [beg=" + beg + " end=" + end + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
