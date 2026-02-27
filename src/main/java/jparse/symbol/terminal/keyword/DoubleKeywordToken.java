package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class DoubleKeywordToken extends KeywordToken {
	public DoubleKeywordToken(int beg, int end) {
		super(SymbolEnum.DOUBLE_KEYWORD_TOKEN, beg, end);
	}
}
