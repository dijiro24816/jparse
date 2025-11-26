package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CaseKeywordToken extends KeywordToken {
	public CaseKeywordToken(int beg, int end) {
		super(SymbolKind.CASE_KEYWORD_TOKEN, beg, end);
	}
}
