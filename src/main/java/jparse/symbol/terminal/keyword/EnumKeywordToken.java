package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class EnumKeywordToken extends KeywordToken {
	public EnumKeywordToken(int beg, int end) {
		super(SymbolEnum.ENUM_KEYWORD_TOKEN, beg, end);
	}
}
