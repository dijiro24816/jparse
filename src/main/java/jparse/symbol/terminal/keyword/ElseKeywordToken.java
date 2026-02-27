package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ElseKeywordToken extends KeywordToken {
	public ElseKeywordToken(int beg, int end) {
		super(SymbolEnum.ELSE_KEYWORD_TOKEN, beg, end);
	}
}
