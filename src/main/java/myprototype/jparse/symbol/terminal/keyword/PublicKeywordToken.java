package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class PublicKeywordToken extends KeywordToken {
	public PublicKeywordToken(int beg, int end) {
		super(SymbolEnum.PUBLIC_KEYWORD_TOKEN, beg, end);
	}
}
