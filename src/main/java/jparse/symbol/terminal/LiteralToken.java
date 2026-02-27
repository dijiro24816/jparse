package jparse.symbol.terminal;

import jparse.symbol.SymbolEnum;

public class LiteralToken extends Terminal {

	public LiteralToken(SymbolEnum kind, int beg, int end) {
		super(kind, beg, end);
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
