package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class FinallyKeywordToken extends KeywordToken {
	public FinallyKeywordToken(int beg, int end) {
		super(SymbolKind.FINALLY_KEYWORD_TOKEN, beg, end);
	}
}
