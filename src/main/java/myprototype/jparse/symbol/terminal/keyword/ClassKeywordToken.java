package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ClassKeywordToken extends KeywordToken {
	public ClassKeywordToken(int beg, int end) {
		super(SymbolEnum.CLASS_KEYWORD_TOKEN, beg, end);
	}
}
