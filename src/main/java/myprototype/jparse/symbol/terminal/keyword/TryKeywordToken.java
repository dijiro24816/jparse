package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class TryKeywordToken extends KeywordToken {
	public TryKeywordToken(int beg, int end) {
		super(SymbolEnum.TRY_KEYWORD_TOKEN, beg, end);
	}
}
