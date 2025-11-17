package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class VolatileKeywordToken extends KeywordToken {
	public VolatileKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
