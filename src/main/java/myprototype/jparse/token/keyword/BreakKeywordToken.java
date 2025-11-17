package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class BreakKeywordToken extends KeywordToken {
	public BreakKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
