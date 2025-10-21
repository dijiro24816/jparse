package myprototype.jparse.token;

public final class IdentifierToken extends Token {
	public String value;

	public IdentifierToken(int beg, int end, String value) {
		super(beg, end);
		this.value = value;
	}
}
