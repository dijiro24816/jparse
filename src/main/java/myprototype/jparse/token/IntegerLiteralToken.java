package myprototype.jparse.token;

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
}
