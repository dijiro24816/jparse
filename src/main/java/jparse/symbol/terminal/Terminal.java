package jparse.symbol.terminal;

import jparse.symbol.Symbol;
import jparse.symbol.SymbolEnum;

public class Terminal extends Symbol {
	protected int beg;
	protected int end;

	public Terminal(SymbolEnum kind, int beg, int end) {
		super(kind);
		this.beg = beg;
		this.end = end;
	}
	
	public Terminal(SymbolEnum kind) {
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

