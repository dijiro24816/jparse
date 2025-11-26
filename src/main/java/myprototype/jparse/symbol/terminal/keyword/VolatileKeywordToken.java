package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class VolatileKeywordToken extends KeywordToken {
	public VolatileKeywordToken(int beg, int end) {
		super(SymbolKind.VOLATILE_KEYWORD_TOKEN, beg, end);
	}
}
