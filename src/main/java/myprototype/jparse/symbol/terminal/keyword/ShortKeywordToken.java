package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ShortKeywordToken extends KeywordToken {
	public ShortKeywordToken(int beg, int end) {
		super(SymbolEnum.SHORT_KEYWORD_TOKEN, beg, end);
	}
}
