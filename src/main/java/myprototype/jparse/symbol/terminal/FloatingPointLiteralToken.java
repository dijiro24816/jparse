package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public final class FloatingPointLiteralToken extends LiteralToken {
	
	public double value;

	public FloatingPointLiteralToken(int beg, int end, double value) {
		super(SymbolKind.FLOATING_POINT_LITERAL_TOKEN, beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "FloatingPointLiteralToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FloatingPointLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
