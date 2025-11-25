package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public class LiteralToken extends Terminal {

	public LiteralToken(SymbolKind kind, int beg, int end) {
		super(kind, beg, end);
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
