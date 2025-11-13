package myprototype.jparse.token;

public final class SeparatorToken extends Token {

	public int symbol;

	public SeparatorToken(int beg, int end, int symbol) {
		super(beg, end);
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "SeparatorToken [beg=" + beg + " end=" + end + " symbol=" + (char) symbol + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) &&
				obj instanceof SeparatorToken tok
						? this.symbol == tok.symbol
						: false;
	}
}
