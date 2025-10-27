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
		return "CharacterLiteralToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}

}
