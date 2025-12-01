package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class InterfaceKeywordToken extends KeywordToken {
	public InterfaceKeywordToken(int beg, int end) {
		super(SymbolEnum.INTERFACE_KEYWORD_TOKEN, beg, end);
	}
}
