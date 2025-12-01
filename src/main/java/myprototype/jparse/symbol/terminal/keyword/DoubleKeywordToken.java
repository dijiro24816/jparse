package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class DoubleKeywordToken extends KeywordToken {
	public DoubleKeywordToken(int beg, int end) {
		super(SymbolEnum.DOUBLE_KEYWORD_TOKEN, beg, end);
	}
}
