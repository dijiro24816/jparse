package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolEnum;

public class SeparatorToken extends Terminal {

	public int symbol;
	
	public SeparatorToken(SymbolEnum kind) {
		super(kind);
	}
			
	public SeparatorToken(SymbolEnum kind, int beg, int end) {
		this(kind, beg, end, -1);
	}

	public SeparatorToken(SymbolEnum kind, int beg, int end, int symbol) {
		super(kind, beg, end);
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [beg=" + getBeg() + " end=" + getEnd() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) &&
				obj instanceof SeparatorToken tok
						? this.symbol == tok.symbol
						: false;
	}
}
