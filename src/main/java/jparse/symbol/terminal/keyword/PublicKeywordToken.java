package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class PublicKeywordToken extends KeywordToken {
	public PublicKeywordToken(int beg, int end) {
		super(SymbolEnum.PUBLIC_KEYWORD_TOKEN, beg, end);
	}
}
