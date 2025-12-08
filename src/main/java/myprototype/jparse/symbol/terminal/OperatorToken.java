package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolEnum;

public class OperatorToken extends Terminal {
	public OperatorToken(SymbolEnum kind) {
		super(kind);
	}
	
	public OperatorToken(SymbolEnum kind, int beg, int end) {
		super(kind, beg, end);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [beg=" + getBeg() + " end=" + getEnd() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
