package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ElseKeywordToken extends KeywordToken {
	public ElseKeywordToken(int beg, int end) {
		super(SymbolKind.ELSE_KEYWORD_TOKEN, beg, end);
	}
}
