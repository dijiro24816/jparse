package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class DefaultKeywordToken extends KeywordToken {
	public DefaultKeywordToken(int beg, int end) {
		super(SymbolEnum.DEFAULT_KEYWORD_TOKEN, beg, end);
	}
}
