package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ThrowKeywordToken extends KeywordToken {
	public ThrowKeywordToken(int beg, int end) {
		super(SymbolEnum.THROW_KEYWORD_TOKEN, beg, end);
	}
}
