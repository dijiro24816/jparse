package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolEnum;

public class IdentifierToken extends Terminal {
	public String value;
	
	@Override
	public String toString() {
		return "IdentifierToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + value + "]";
	}

	public IdentifierToken(int beg, int end, String value) {
		super(SymbolEnum.IDENTIFIER_TOKEN, beg, end);
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
