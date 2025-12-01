package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class CharKeywordToken extends KeywordToken {
	public CharKeywordToken(int beg, int end) {
		super(SymbolEnum.CHAR_KEYWORD_TOKEN, beg, end);
	}
}
