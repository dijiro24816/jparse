package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ByteKeywordToken extends KeywordToken {
	public ByteKeywordToken(int beg, int end) {
		super(SymbolKind.BYTE_KEYWORD_TOKEN, beg, end);
	}
}
