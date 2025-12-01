package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class GotoKeywordToken extends KeywordToken {
	public GotoKeywordToken(int beg, int end) {
		super(SymbolEnum.GOTO_KEYWORD_TOKEN, beg, end);
	}
}
