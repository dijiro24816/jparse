package myprototype.jparse.token;

public final class KeywordToken extends Token {
	enum Type {
		PUBLIC,
		PRIVATE,
		PROTECTED
	};
	
	public Type value;

	public KeywordToken(int beg, int end, Type value) {
		super(beg, end);
		this.value = value;
	}
}
