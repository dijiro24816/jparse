package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class PrivateKeywordToken extends KeywordToken {
	public PrivateKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
