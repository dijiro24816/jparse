package myprototype.jparse.token;

public class SeparatorToken extends Token {

	public int symbol;
	
	public SeparatorToken() {}
			
	public SeparatorToken(int beg, int end) {
		super(beg, end);
	}

	public SeparatorToken(int beg, int end, int symbol) {
		super(beg, end);
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [beg=" + beg + " end=" + end + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) &&
				obj instanceof SeparatorToken tok
						? this.symbol == tok.symbol
						: false;
	}
}
