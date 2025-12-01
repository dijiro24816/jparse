package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class LongKeywordToken extends KeywordToken {
	public LongKeywordToken(int beg, int end) {
		super(SymbolEnum.LONG_KEYWORD_TOKEN, beg, end);
	}
}
