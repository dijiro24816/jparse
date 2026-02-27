package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class DefaultKeywordToken extends KeywordToken {
	public DefaultKeywordToken(int beg, int end) {
		super(SymbolEnum.DEFAULT_KEYWORD_TOKEN, beg, end);
	}
}
