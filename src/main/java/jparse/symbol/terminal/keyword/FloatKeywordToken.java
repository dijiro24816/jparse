package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class FloatKeywordToken extends KeywordToken {
	public FloatKeywordToken(int beg, int end) {
		super(SymbolEnum.FLOAT_KEYWORD_TOKEN, beg, end);
	}
}
