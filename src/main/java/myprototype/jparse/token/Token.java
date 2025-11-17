package myprototype.jparse.token;

public class Token {
	public int beg;
	public int end;

	public Token(int beg, int end) {
		super();
		this.beg = beg;
		this.end = end;
	}
	
	public Token() {
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Token tok) {
			return this.beg == tok.beg && this.end == tok.end;
		}
		return false;
	}
}

