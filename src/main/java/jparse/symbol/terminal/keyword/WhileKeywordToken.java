package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class WhileKeywordToken extends KeywordToken {
	public WhileKeywordToken(int beg, int end) {
		super(SymbolEnum.WHILE_KEYWORD_TOKEN, beg, end);
	}
}
