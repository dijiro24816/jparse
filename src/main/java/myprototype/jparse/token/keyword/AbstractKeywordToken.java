package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class AbstractKeywordToken extends KeywordToken {

	public AbstractKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
