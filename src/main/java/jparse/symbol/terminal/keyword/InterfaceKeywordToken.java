package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class InterfaceKeywordToken extends KeywordToken {
	public InterfaceKeywordToken(int beg, int end) {
		super(SymbolEnum.INTERFACE_KEYWORD_TOKEN, beg, end);
	}
}
