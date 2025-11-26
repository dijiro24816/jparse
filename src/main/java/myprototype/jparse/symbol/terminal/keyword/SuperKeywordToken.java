package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class SuperKeywordToken extends KeywordToken {
	public SuperKeywordToken(int beg, int end) {
		super(SymbolKind.SUPER_KEYWORD_TOKEN, beg, end);
	}
}
