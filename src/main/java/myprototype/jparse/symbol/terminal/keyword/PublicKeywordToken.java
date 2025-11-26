package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class PublicKeywordToken extends KeywordToken {
	public PublicKeywordToken(int beg, int end) {
		super(SymbolKind.PUBLIC_KEYWORD_TOKEN, beg, end);
	}
}
