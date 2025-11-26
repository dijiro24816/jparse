package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CharKeywordToken extends KeywordToken {
	public CharKeywordToken(int beg, int end) {
		super(SymbolKind.CHAR_KEYWORD_TOKEN, beg, end);
	}
}
