package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class IfKeywordToken extends KeywordToken {
	public IfKeywordToken(int beg, int end) {
		super(SymbolKind.IF_KEYWORD_TOKEN, beg, end);
	}
}
