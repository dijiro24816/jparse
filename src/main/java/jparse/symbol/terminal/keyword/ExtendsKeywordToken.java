package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ExtendsKeywordToken extends KeywordToken {
	public ExtendsKeywordToken(int beg, int end) {
		super(SymbolEnum.EXTENDS_KEYWORD_TOKEN, beg, end);
	}
}
