package myprototype.jparse.token;

public final class StringLiteralToken extends LiteralToken {
	public String value;
	
	public StringLiteralToken(int beg, int end, String value) {
		super(beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "StringLiteralToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		return super.equals(obj) &&
//				obj instanceof StringLiteralToken
//	}
}
