package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class SwitchKeywordToken extends KeywordToken {
	public SwitchKeywordToken(int beg, int end) {
		super(SymbolEnum.SWITCH_KEYWORD_TOKEN, beg, end);
	}
}
