package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ThrowKeywordToken extends KeywordToken {
	public ThrowKeywordToken(int beg, int end) {
		super(SymbolEnum.THROW_KEYWORD_TOKEN, beg, end);
	}
}
