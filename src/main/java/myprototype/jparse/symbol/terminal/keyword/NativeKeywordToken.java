package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class NativeKeywordToken extends KeywordToken {
	public NativeKeywordToken(int beg, int end) {
		super(SymbolKind.NATIVE_KEYWORD_TOKEN, beg, end);
	}
}
