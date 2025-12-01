package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class FloatKeywordToken extends KeywordToken {
	public FloatKeywordToken(int beg, int end) {
		super(SymbolEnum.FLOAT_KEYWORD_TOKEN, beg, end);
	}
}
