package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ConstKeywordToken extends KeywordToken {
	public ConstKeywordToken(int beg, int end) {
		super(SymbolEnum.CONST_KEYWORD_TOKEN, beg, end);
	}
}
