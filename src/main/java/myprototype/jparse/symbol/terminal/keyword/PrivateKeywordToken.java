package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class PrivateKeywordToken extends KeywordToken {
	public PrivateKeywordToken(int beg, int end) {
		super(SymbolKind.PRIVATE_KEYWORD_TOKEN, beg, end);
	}
}
