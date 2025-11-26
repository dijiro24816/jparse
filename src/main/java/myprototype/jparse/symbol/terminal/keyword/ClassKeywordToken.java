package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ClassKeywordToken extends KeywordToken {
	public ClassKeywordToken(int beg, int end) {
		super(SymbolKind.CLASS_KEYWORD_TOKEN, beg, end);
	}
}
