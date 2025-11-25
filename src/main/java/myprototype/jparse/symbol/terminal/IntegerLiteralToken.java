package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public final class IntegerLiteralToken extends LiteralToken {
	
	public long value; // max value of integer

	public IntegerLiteralToken(int beg, int end, long value) {
		super(SymbolKind.INTEGER_LITERAL_TOKEN, beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntegerliteralToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntegerLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
