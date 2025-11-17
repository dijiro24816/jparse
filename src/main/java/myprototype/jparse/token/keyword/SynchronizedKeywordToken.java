package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class SynchronizedKeywordToken extends KeywordToken {
	public SynchronizedKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
