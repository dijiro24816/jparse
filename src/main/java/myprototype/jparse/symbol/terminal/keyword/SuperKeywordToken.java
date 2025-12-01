package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class SuperKeywordToken extends KeywordToken {
	public SuperKeywordToken(int beg, int end) {
		super(SymbolEnum.SUPER_KEYWORD_TOKEN, beg, end);
	}
}
