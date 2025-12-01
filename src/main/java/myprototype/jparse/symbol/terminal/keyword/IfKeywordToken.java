package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class IfKeywordToken extends KeywordToken {
	public IfKeywordToken(int beg, int end) {
		super(SymbolEnum.IF_KEYWORD_TOKEN, beg, end);
	}
}
