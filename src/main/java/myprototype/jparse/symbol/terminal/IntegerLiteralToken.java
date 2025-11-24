package myprototype.jparse.symbol.terminal;

public final class IntegerLiteralToken extends LiteralToken {
	
	public long value; // max value of integer

	public IntegerLiteralToken(int beg, int end, long value) {
		super(beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntegerliteralToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntegerLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
