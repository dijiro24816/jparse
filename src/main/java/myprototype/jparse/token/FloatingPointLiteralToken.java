package myprototype.jparse.token;

public final class FloatingPointLiteralToken extends LiteralToken {
	
	public double value;

	public FloatingPointLiteralToken(int beg, int end, double value) {
		super(beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "FloatingPointLiteralToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}
}
