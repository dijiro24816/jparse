package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class FinallyKeywordToken extends KeywordToken {
	public FinallyKeywordToken(int beg, int end) {
		super(SymbolEnum.FINALLY_KEYWORD_TOKEN, beg, end);
	}
}
