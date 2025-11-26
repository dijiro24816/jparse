package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class BooleanKeywordToken extends KeywordToken {
	public BooleanKeywordToken(int beg, int end) {
		super(SymbolKind.BOOLEAN_KEYWORD_TOKEN, beg, end);
	}
}
