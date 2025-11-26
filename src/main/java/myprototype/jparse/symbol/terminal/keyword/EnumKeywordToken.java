package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class EnumKeywordToken extends KeywordToken {
	public EnumKeywordToken(int beg, int end) {
		super(SymbolKind.ENUM_KEYWORD_TOKEN, beg, end);
	}
}
