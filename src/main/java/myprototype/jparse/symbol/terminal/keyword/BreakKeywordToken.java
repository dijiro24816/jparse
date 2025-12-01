package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class BreakKeywordToken extends KeywordToken {
	public BreakKeywordToken(int beg, int end) {
		super(SymbolEnum.BREAK_KEYWORD_TOKEN, beg, end);
	}
}
