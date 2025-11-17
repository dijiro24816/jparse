package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class EnumKeywordToken extends KeywordToken {
	public EnumKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
