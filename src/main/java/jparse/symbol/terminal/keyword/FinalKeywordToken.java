package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class FinalKeywordToken extends KeywordToken {
	public FinalKeywordToken(int beg, int end) {
		super(SymbolEnum.FINAL_KEYWORD_TOKEN, beg, end);
	}
}
