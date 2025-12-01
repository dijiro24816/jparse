package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class DoKeywordToken extends KeywordToken {
	public DoKeywordToken(int beg, int end) {
		super(SymbolEnum.DO_KEYWORD_TOKEN, beg, end);
	}
}
