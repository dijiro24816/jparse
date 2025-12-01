package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class VolatileKeywordToken extends KeywordToken {
	public VolatileKeywordToken(int beg, int end) {
		super(SymbolEnum.VOLATILE_KEYWORD_TOKEN, beg, end);
	}
}
