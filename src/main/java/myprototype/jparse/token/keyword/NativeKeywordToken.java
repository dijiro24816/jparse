package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class NativeKeywordToken extends KeywordToken {
	public NativeKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
