package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class DoKeywordToken extends KeywordToken {
	public DoKeywordToken(int beg, int end) {
		super(SymbolEnum.DO_KEYWORD_TOKEN, beg, end);
	}
}
