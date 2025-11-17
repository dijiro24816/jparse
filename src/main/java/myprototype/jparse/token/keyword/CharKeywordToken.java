package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class CharKeywordToken extends KeywordToken {
	public CharKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
