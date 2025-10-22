package myprototype.jparse.token;

public final class IdentifierToken extends Token {
	public String value;
	
	@Override
	public String toString() {
		return "IdentifierToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}

	public IdentifierToken(int beg, int end, String value) {
		super(beg, end);
		this.value = value;
	}
}
