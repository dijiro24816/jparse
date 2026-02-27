package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class TransientKeywordToken extends KeywordToken {
	public TransientKeywordToken(int beg, int end) {
		super(SymbolEnum.TRANSIENT_KEYWORD_TOKEN, beg, end);
	}
}
