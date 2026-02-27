package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class IfKeywordToken extends KeywordToken {
	public IfKeywordToken(int beg, int end) {
		super(SymbolEnum.IF_KEYWORD_TOKEN, beg, end);
	}
}
