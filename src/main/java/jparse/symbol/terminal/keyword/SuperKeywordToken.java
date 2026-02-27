package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class SuperKeywordToken extends KeywordToken {
	public SuperKeywordToken(int beg, int end) {
		super(SymbolEnum.SUPER_KEYWORD_TOKEN, beg, end);
	}
}
