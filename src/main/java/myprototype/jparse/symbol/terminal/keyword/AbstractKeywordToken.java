package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class AbstractKeywordToken extends KeywordToken {

	public AbstractKeywordToken(int beg, int end) {
		super(SymbolKind.ABSTRACT_KEYWORD_TOKEN, beg, end);
	}
}
