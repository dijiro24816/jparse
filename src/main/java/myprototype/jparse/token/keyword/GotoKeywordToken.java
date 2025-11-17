package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class GotoKeywordToken extends KeywordToken {
	public GotoKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
