package myprototype.jparse.token;

public final class CharacterLiteralToken extends LiteralToken {

	public int value;
	
	public CharacterLiteralToken(int beg, int end, int value) {
		super(beg, end);
		
		this.value = value;
		
		double a = 0x0p1f;
	}

	@Override
	public String toString() {
		return "CharacterLiteralToken [beg=" + beg + " end=" + end + " value=" + (char)value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CharacterLiteralToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
