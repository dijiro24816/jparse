package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public class NullLiteralToken extends LiteralToken {

	public NullLiteralToken(int beg, int end) {
		super(SymbolKind.NULL_LITERAL_TOKEN, beg, end);
	}
	
	public static NullLiteralToken capture(int beg, int end, String s) {
		return s.equals("null") ? new NullLiteralToken(beg, end) : null;
	}

	@Override
	public String toString() {
		return "NullLiteralToken [beg=" + getBeg() + " end=" + getEnd() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NullLiteralToken tok) {
			return super.equals(obj);
		}
		return false;
	}
}
