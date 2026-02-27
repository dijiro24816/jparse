package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class InstanceofKeywordToken extends KeywordToken {
	public InstanceofKeywordToken(int beg, int end) {
		super(SymbolEnum.INSTANCEOF_KEYWORD_TOKEN, beg, end);
	}
}
