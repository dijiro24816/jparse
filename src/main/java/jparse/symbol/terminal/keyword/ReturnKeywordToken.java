package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ReturnKeywordToken extends KeywordToken {
	public ReturnKeywordToken(int beg, int end) {
		super(SymbolEnum.RETURN_KEYWORD_TOKEN, beg, end);
	}
}
