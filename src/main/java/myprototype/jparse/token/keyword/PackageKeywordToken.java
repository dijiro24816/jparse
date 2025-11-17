package myprototype.jparse.token.keyword;

import myprototype.jparse.token.KeywordToken;

public class PackageKeywordToken extends KeywordToken {
	public PackageKeywordToken(int beg, int end) {
		this.beg = beg;
		this.end = end;
	}
}
