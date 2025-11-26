package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class TransientKeywordToken extends KeywordToken {
	public TransientKeywordToken(int beg, int end) {
		super(SymbolKind.TRANSIENT_KEYWORD_TOKEN, beg, end);
	}
}
