package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ProtectedKeywordToken extends KeywordToken {
	public ProtectedKeywordToken(int beg, int end) {
		super(SymbolKind.PROTECTED_KEYWORD_TOKEN, beg, end);
	}
}
