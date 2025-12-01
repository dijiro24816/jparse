package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class StrictfpKeywordToken extends KeywordToken {
	public StrictfpKeywordToken(int beg, int end) {
		super(SymbolEnum.STRICTFP_KEYWORD_TOKEN, beg, end);
	}
}
