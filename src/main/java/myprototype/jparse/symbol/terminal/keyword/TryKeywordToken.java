package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class TryKeywordToken extends KeywordToken {
	public TryKeywordToken(int beg, int end) {
		super(SymbolKind.TRY_KEYWORD_TOKEN, beg, end);
	}
}
