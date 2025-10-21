package myprototype.jparse.token;

public sealed class LiteralToken extends Token
		permits IntegerliteralToken, FloatingPointLiteralToken, BooleanLiteralToken, CharacterLiteralToken, StringLiteralToken, NullLiteralToken {

	public LiteralToken(int beg, int end) {
		super(beg, end);
	}
}
