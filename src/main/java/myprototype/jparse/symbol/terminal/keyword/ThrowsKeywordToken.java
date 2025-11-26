package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ThrowsKeywordToken extends KeywordToken {
	public ThrowsKeywordToken(int beg, int end) {
		super(SymbolKind.THROWS_KEYWORD_TOKEN, beg, end);
	}
}
