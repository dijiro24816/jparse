package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class StaticKeywordToken extends KeywordToken {
	public StaticKeywordToken(int beg, int end) {
		super(SymbolKind.STATIC_KEYWORD_TOKEN, beg, end);
	}
}
