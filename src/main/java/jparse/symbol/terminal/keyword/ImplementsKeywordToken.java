package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ImplementsKeywordToken extends KeywordToken {
	public ImplementsKeywordToken(int beg, int end) {
		super(SymbolEnum.IMPLEMENTS_KEYWORD_TOKEN, beg, end);
	}
}
