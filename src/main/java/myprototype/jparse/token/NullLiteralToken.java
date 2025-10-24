package myprototype.jparse.token;

public final class NullLiteralToken extends LiteralToken {

	public NullLiteralToken(int beg, int end) {
		super(beg, end);
	}
	
	public static NullLiteralToken capture(int beg, int end, String s) {
		return s.equals("null") ? new NullLiteralToken(beg, end) : null;
	}

	@Override
	public String toString() {
		return "NullLiteralToken [beg=" + beg + " end=" + end + "]";
	}
}
