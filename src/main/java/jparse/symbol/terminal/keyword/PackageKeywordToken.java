package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class PackageKeywordToken extends KeywordToken {
	public PackageKeywordToken(int beg, int end) {
		super(SymbolEnum.PACKAGE_KEYWORD_TOKEN, beg, end);
	}
}
