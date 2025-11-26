package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ConstKeywordToken extends KeywordToken {
	public ConstKeywordToken(int beg, int end) {
		super(SymbolKind.CONST_KEYWORD_TOKEN, beg, end);
	}
}
