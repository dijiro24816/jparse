package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class AssertKeywordToken extends KeywordToken {
	public AssertKeywordToken(int beg, int end) {
		super(SymbolEnum.ASSERT_KEYWORD_TOKEN, beg, end);
	}
}
