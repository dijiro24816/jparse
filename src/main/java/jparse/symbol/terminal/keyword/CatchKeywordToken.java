package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class CatchKeywordToken extends KeywordToken {
	public CatchKeywordToken(int beg, int end) {
		super(SymbolEnum.CATCH_KEYWORD_TOKEN, beg, end);
	}
}
