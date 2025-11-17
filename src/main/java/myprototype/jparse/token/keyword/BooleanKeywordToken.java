package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class BooleanKeywordToken extends KeywordToken {
	public BooleanKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
