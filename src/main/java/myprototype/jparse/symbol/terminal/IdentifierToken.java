package myprototype.jparse.symbol.terminal;

public final class IdentifierToken extends Terminal {
	public String value;
	
	@Override
	public String toString() {
		return "IdentifierToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}

	public IdentifierToken(int beg, int end, String value) {
		super(beg, end);
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IdentifierToken tok) {
			return super.equals(obj) && this.value.equals(tok.value);
		}
		return false;
	}
	
	
}
