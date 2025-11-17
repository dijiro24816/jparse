package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class ProtectedKeywordToken extends KeywordToken {
	public ProtectedKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
