package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class VoidKeywordToken extends KeywordToken {
	public VoidKeywordToken(int beg, int end) {
		super(SymbolKind.VOID_KEYWORD_TOKEN, beg, end);
	}
}
