package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class WhileKeywordToken extends KeywordToken {
	public WhileKeywordToken(int beg, int end) {
		super(SymbolEnum.WHILE_KEYWORD_TOKEN, beg, end);
	}
}
