package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class AbstractKeywordToken extends KeywordToken {

	public AbstractKeywordToken(int beg, int end) {
		super(SymbolEnum.ABSTRACT_KEYWORD_TOKEN, beg, end);
	}
}
