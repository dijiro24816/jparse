package myprototype.jparse.symbol.terminal;

import myprototype.jparse.Symbol;
import myprototype.jparse.symbol.SymbolEnum;

public class BooleanLiteralToken extends LiteralToken {

	public boolean value;
	
	public BooleanLiteralToken(int beg, int end, boolean value) {
		super(SymbolEnum.BOOLEAN_LITERAL_TOKEN, beg, end);
		this.value = value;
	}
	
	public static Symbol capture(int beg, int end, String s) {
		boolean value;
		
		switch (s) {
		case "true":
			value = true;
			break;
		case "false":
			value = false;
			break;
		default:
			return null;
		}
		
		return new Symbol("BooleanLiteral", beg, end, value);
	}

	@Override
	public String toString() {
		return "BooleanLiteralToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + (value ? "true" : "false") + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BooleanLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
