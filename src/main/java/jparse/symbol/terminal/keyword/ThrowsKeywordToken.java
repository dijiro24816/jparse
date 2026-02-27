package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ThrowsKeywordToken extends KeywordToken {
	public ThrowsKeywordToken(int beg, int end) {
		super(SymbolEnum.THROWS_KEYWORD_TOKEN, beg, end);
	}
}
