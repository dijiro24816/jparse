package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ThisKeywordToken extends KeywordToken {
	public ThisKeywordToken(int beg, int end) {
		super(SymbolKind.THIS_KEYWORD_TOKEN, beg, end);
	}
}
