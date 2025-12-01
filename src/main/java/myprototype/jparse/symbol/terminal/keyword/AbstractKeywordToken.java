package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class AbstractKeywordToken extends KeywordToken {

	public AbstractKeywordToken(int beg, int end) {
		super(SymbolEnum.ABSTRACT_KEYWORD_TOKEN, beg, end);
	}
}
