package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ExtendsKeywordToken extends KeywordToken {
	public ExtendsKeywordToken(int beg, int end) {
		super(SymbolEnum.EXTENDS_KEYWORD_TOKEN, beg, end);
	}
}
