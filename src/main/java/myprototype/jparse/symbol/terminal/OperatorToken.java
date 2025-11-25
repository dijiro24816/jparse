package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public class OperatorToken extends Terminal {
	public OperatorToken(SymbolKind kind) {
		super(kind);
	}
	
	public OperatorToken(SymbolKind kind, int beg, int end) {
		super(kind, beg, end);
	}

	@Override
	public String toString() {
		return "OperatorToken [beg=" + getBeg() + " end=" + getEnd() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
