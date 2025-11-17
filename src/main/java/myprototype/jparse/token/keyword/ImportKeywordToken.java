package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class ImportKeywordToken extends KeywordToken {
	public ImportKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
