package myprototype.jparse.token;

public sealed class Token permits IdentifierToken, KeywordToken, LiteralToken, SeparatorToken, OperatorToken {
	public int beg;
	public int end;

	public Token(int beg, int end) {
		super();
		this.beg = beg;
		this.end = end;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Token tok) {
			return this.beg == tok.beg && this.end == tok.end;
		}
		return false;
	}
}

