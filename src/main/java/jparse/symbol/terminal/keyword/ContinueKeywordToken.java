package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ContinueKeywordToken extends KeywordToken {
	public ContinueKeywordToken(int beg, int end) {
		super(SymbolEnum.CONTINUE_KEYWORD_TOKEN, beg, end);
	}
}
