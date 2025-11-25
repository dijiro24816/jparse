package myprototype.jparse.symbol.terminal;

import myprototype.jparse.symbol.SymbolKind;

public final class CharacterLiteralToken extends LiteralToken {

	public int value;
	
	public CharacterLiteralToken(int beg, int end, int value) {
		super(SymbolKind.CHARACTER_LITERAL_TOKEN, beg, end);
		
		this.value = value;
		
		double a = 0x0p1f;
	}

	@Override
	public String toString() {
		return "CharacterLiteralToken [beg=" + getBeg() + " end=" + getEnd() + " value=" + (char)value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CharacterLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
