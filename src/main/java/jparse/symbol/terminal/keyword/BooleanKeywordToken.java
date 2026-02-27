package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class BooleanKeywordToken extends KeywordToken {
	public BooleanKeywordToken(int beg, int end) {
		super(SymbolEnum.BOOLEAN_KEYWORD_TOKEN, beg, end);
	}
}
