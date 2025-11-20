package myprototype.jparse.syntax.symbol.terminal;

import myprototype.jparse.syntax.symbol.Symbol;

public class Terminal extends Symbol {
	public int beg;
	public int end;

	public Terminal(int beg, int end) {
		super();
		this.beg = beg;
		this.end = end;
	}
	
	public Terminal() {
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Terminal tok) {
			return this.beg == tok.beg && this.end == tok.end;
		}
		return false;
	}
}

