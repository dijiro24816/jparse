package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class NewKeywordToken extends KeywordToken {
	public NewKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
