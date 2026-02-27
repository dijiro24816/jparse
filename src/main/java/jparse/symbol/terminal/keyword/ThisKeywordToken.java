package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ThisKeywordToken extends KeywordToken {
	public ThisKeywordToken(int beg, int end) {
		super(SymbolEnum.THIS_KEYWORD_TOKEN, beg, end);
	}
}
