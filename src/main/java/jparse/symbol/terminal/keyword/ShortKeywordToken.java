package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ShortKeywordToken extends KeywordToken {
	public ShortKeywordToken(int beg, int end) {
		super(SymbolEnum.SHORT_KEYWORD_TOKEN, beg, end);
	}
}
