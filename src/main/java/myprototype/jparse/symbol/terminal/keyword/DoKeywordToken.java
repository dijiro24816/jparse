package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class DoKeywordToken extends KeywordToken {
	public DoKeywordToken(int beg, int end) {
		super(SymbolKind.DO_KEYWORD_TOKEN, beg, end);
	}
}
