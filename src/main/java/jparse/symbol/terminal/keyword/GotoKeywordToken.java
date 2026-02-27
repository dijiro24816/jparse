package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class GotoKeywordToken extends KeywordToken {
	public GotoKeywordToken(int beg, int end) {
		super(SymbolEnum.GOTO_KEYWORD_TOKEN, beg, end);
	}
}
