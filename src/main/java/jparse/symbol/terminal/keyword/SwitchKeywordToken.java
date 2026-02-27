package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class SwitchKeywordToken extends KeywordToken {
	public SwitchKeywordToken(int beg, int end) {
		super(SymbolEnum.SWITCH_KEYWORD_TOKEN, beg, end);
	}
}
