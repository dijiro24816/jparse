package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class CharKeywordToken extends KeywordToken {
	public CharKeywordToken(int beg, int end) {
		super(SymbolEnum.CHAR_KEYWORD_TOKEN, beg, end);
	}
}
