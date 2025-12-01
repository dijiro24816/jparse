package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolEnum;

public final class StringLiteralToken extends LiteralToken {
	public String value;
	
	public StringLiteralToken(int beg, int end, String value) {
		super(SymbolEnum.STRING_LITERAL_TOKEN, beg, end);
		
		this.value = value;
	}

	@Override
	public String toString() {
		return "StringLiteralToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + value + "]";
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		return super.equals(obj) &&
//				obj instanceof StringLiteralToken
//	}
}
