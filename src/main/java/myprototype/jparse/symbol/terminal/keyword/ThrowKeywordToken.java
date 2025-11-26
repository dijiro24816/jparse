package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ThrowKeywordToken extends KeywordToken {
	public ThrowKeywordToken(int beg, int end) {
		super(SymbolKind.THROW_KEYWORD_TOKEN, beg, end);
	}
}
