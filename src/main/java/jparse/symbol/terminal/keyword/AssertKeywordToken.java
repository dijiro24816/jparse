package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class AssertKeywordToken extends KeywordToken {
	public AssertKeywordToken(int beg, int end) {
		super(SymbolEnum.ASSERT_KEYWORD_TOKEN, beg, end);
	}
}
