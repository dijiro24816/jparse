package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CatchKeywordToken extends KeywordToken {
	public CatchKeywordToken(int beg, int end) {
		super(SymbolKind.CATCH_KEYWORD_TOKEN, beg, end);
	}
}
