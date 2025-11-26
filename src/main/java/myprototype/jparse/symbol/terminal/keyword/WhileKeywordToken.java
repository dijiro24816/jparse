package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class WhileKeywordToken extends KeywordToken {
	public WhileKeywordToken(int beg, int end) {
		super(SymbolKind.WHILE_KEYWORD_TOKEN, beg, end);
	}
}
