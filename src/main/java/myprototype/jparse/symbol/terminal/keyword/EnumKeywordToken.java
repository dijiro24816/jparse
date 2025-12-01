package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class EnumKeywordToken extends KeywordToken {
	public EnumKeywordToken(int beg, int end) {
		super(SymbolEnum.ENUM_KEYWORD_TOKEN, beg, end);
	}
}
