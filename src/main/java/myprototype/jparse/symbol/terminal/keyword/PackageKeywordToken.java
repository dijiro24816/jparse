package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class PackageKeywordToken extends KeywordToken {
	public PackageKeywordToken(int beg, int end) {
		super(SymbolEnum.PACKAGE_KEYWORD_TOKEN, beg, end);
	}
}
