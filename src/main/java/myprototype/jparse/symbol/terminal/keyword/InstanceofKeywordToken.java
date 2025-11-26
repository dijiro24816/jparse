package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class InstanceofKeywordToken extends KeywordToken {
	public InstanceofKeywordToken(int beg, int end) {
		super(SymbolKind.INSTANCEOF_KEYWORD_TOKEN, beg, end);
	}
}
