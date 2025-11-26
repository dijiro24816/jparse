package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ContinueKeywordToken extends KeywordToken {
	public ContinueKeywordToken(int beg, int end) {
		super(SymbolKind.CONTINUE_KEYWORD_TOKEN, beg, end);
	}
}
