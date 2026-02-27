package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ForKeywordToken extends KeywordToken {
	public ForKeywordToken(int beg, int end) {
		super(SymbolEnum.FOR_KEYWORD_TOKEN, beg, end);
	}
}
