package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class VoidKeywordToken extends KeywordToken {
	public VoidKeywordToken(int beg, int end) {
		super(SymbolEnum.VOID_KEYWORD_TOKEN, beg, end);
	}
}
