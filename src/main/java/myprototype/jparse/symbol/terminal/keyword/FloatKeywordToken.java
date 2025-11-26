package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class FloatKeywordToken extends KeywordToken {
	public FloatKeywordToken(int beg, int end) {
		super(SymbolKind.FLOAT_KEYWORD_TOKEN, beg, end);
	}
}
