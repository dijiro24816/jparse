package myprototype.jparse.token;

public final class BooleanLiteralToken extends LiteralToken {

	public boolean value;
	
	public BooleanLiteralToken(int beg, int end, boolean value) {
		super(beg, end);
		this.value = value;
	}
	
	public static BooleanLiteralToken capture(int beg, int end, String s) {
		boolean value;
		
		switch (s) {
		case "true":
			value = true;
			break;
		case "false":
			value = false;
			break;
		default:
			return null;
		}
		
		return new BooleanLiteralToken(beg, end, value);
	}

	@Override
	public String toString() {
		return "BooleanLiteralToken [beg=" + beg + " end=" + end + " value=" + (value ? "true" : "false") + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BooleanLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
