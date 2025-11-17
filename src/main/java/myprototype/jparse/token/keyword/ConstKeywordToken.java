package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class ConstKeywordToken extends KeywordToken {
	public ConstKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
