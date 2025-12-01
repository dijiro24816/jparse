package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CatchKeywordToken extends KeywordToken {
	public CatchKeywordToken(int beg, int end) {
		super(SymbolEnum.CATCH_KEYWORD_TOKEN, beg, end);
	}
}
