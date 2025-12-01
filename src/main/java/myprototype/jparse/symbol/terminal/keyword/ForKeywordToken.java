package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ForKeywordToken extends KeywordToken {
	public ForKeywordToken(int beg, int end) {
		super(SymbolEnum.FOR_KEYWORD_TOKEN, beg, end);
	}
}
