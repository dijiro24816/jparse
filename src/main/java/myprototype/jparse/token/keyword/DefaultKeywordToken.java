package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class DefaultKeywordToken extends KeywordToken {
	public DefaultKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
