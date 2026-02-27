package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class VolatileKeywordToken extends KeywordToken {
	public VolatileKeywordToken(int beg, int end) {
		super(SymbolEnum.VOLATILE_KEYWORD_TOKEN, beg, end);
	}
}
