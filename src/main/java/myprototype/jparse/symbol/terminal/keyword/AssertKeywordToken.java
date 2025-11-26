package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class AssertKeywordToken extends KeywordToken {
	public AssertKeywordToken(int beg, int end) {
		super(SymbolKind.ASSERT_KEYWORD_TOKEN, beg, end);
	}
}
