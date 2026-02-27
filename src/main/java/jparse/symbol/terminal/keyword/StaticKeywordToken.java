package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class StaticKeywordToken extends KeywordToken {
	public StaticKeywordToken(int beg, int end) {
		super(SymbolEnum.STATIC_KEYWORD_TOKEN, beg, end);
	}
}
