package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class IntKeywordToken extends KeywordToken {
	public IntKeywordToken(int beg, int end) {
		super(SymbolKind.INT_KEYWORD_TOKEN, beg, end);
	}
}
