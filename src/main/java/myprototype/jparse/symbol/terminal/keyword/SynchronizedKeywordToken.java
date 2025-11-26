package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class SynchronizedKeywordToken extends KeywordToken {
	public SynchronizedKeywordToken(int beg, int end) {
		super(SymbolKind.SYNCHRONIZED_KEYWORD_TOKEN, beg, end);
	}
}
