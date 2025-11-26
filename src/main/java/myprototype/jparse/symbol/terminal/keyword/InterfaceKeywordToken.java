package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class InterfaceKeywordToken extends KeywordToken {
	public InterfaceKeywordToken(int beg, int end) {
		super(SymbolKind.INTERFACE_KEYWORD_TOKEN, beg, end);
	}
}
