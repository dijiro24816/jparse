package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ExtendsKeywordToken extends KeywordToken {
	public ExtendsKeywordToken(int beg, int end) {
		super(SymbolKind.EXTENDS_KEYWORD_TOKEN, beg, end);
	}
}
