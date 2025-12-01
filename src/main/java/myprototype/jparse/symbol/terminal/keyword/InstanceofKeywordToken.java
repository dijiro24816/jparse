package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class InstanceofKeywordToken extends KeywordToken {
	public InstanceofKeywordToken(int beg, int end) {
		super(SymbolEnum.INSTANCEOF_KEYWORD_TOKEN, beg, end);
	}
}
