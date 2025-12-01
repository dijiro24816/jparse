package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CaseKeywordToken extends KeywordToken {
	public CaseKeywordToken(int beg, int end) {
		super(SymbolEnum.CASE_KEYWORD_TOKEN, beg, end);
	}
}
