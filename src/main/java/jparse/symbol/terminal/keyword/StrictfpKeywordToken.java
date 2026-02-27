package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class StrictfpKeywordToken extends KeywordToken {
	public StrictfpKeywordToken(int beg, int end) {
		super(SymbolEnum.STRICTFP_KEYWORD_TOKEN, beg, end);
	}
}
