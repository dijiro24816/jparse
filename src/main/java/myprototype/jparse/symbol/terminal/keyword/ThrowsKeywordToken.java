package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ThrowsKeywordToken extends KeywordToken {
	public ThrowsKeywordToken(int beg, int end) {
		super(SymbolEnum.THROWS_KEYWORD_TOKEN, beg, end);
	}
}
