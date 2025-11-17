package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class ClassKeywordToken extends KeywordToken {
	public ClassKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
