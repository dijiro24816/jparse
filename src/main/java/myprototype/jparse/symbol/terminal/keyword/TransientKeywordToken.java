package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class TransientKeywordToken extends KeywordToken {
	public TransientKeywordToken(int beg, int end) {
		super(SymbolEnum.TRANSIENT_KEYWORD_TOKEN, beg, end);
	}
}
