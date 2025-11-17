package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class StaticKeywordToken extends KeywordToken {
	public StaticKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
