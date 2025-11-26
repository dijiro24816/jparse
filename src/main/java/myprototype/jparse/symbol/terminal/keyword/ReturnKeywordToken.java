package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ReturnKeywordToken extends KeywordToken {
	public ReturnKeywordToken(int beg, int end) {
		super(SymbolKind.RETURN_KEYWORD_TOKEN, beg, end);
	}
}
