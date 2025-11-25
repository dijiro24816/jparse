package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.SymbolKind;

public class Terminal extends Symbol {
	private int beg;
	private int end;

	public Terminal(SymbolKind kind, int beg, int end) {
		super(kind);
		this.beg = beg;
		this.end = end;
	}
	
	public Terminal(SymbolKind kind) {
		this(kind, -1, -1);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Terminal tok) {
			return getBeg() == tok.getBeg() && getEnd() == tok.getEnd();
		}
		return false;
	}

	public int getBeg() {
		return beg;
	}

	public int getEnd() {
		return end;
	}
}

