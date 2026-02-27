package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class PrivateKeywordToken extends KeywordToken {
	public PrivateKeywordToken(int beg, int end) {
		super(SymbolEnum.PRIVATE_KEYWORD_TOKEN, beg, end);
	}
}
