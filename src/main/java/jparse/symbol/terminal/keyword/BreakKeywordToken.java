package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class BreakKeywordToken extends KeywordToken {
	public BreakKeywordToken(int beg, int end) {
		super(SymbolEnum.BREAK_KEYWORD_TOKEN, beg, end);
	}
}
