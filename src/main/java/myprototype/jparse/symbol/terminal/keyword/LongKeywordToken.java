package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class LongKeywordToken extends KeywordToken {
	public LongKeywordToken(int beg, int end) {
		super(SymbolKind.LONG_KEYWORD_TOKEN, beg, end);
	}
}
