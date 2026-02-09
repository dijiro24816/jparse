package myprototype.jparse.symbol.terminal;

import myprototype.jparse.Symbol;
import myprototype.jparse.symbol.SymbolEnum;

public class NullLiteralToken extends LiteralToken {

	public NullLiteralToken(int beg, int end) {
		super(SymbolEnum.NULL_LITERAL_TOKEN, beg, end);
	}
	
	public static Symbol capture(int beg, int end, String s) {
		return s.equals("null") ? new Symbol("NullLiteral", beg, end) : null;
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
