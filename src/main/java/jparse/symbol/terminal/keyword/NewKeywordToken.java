package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class NewKeywordToken extends KeywordToken {
	public NewKeywordToken(int beg, int end) {
		super(SymbolEnum.NEW_KEYWORD_TOKEN, beg, end);
	}
}
