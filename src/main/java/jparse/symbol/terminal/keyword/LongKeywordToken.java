package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class LongKeywordToken extends KeywordToken {
	public LongKeywordToken(int beg, int end) {
		super(SymbolEnum.LONG_KEYWORD_TOKEN, beg, end);
	}
}
