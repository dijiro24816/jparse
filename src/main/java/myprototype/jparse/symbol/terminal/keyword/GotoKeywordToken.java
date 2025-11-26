package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class GotoKeywordToken extends KeywordToken {
	public GotoKeywordToken(int beg, int end) {
		super(SymbolKind.GOTO_KEYWORD_TOKEN, beg, end);
	}
}
