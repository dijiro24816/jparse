package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class FinalKeywordToken extends KeywordToken {
	public FinalKeywordToken(int beg, int end) {
		super(SymbolEnum.FINAL_KEYWORD_TOKEN, beg, end);
	}
}
