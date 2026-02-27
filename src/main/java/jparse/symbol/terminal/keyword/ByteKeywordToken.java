package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ByteKeywordToken extends KeywordToken {
	public ByteKeywordToken(int beg, int end) {
		super(SymbolEnum.BYTE_KEYWORD_TOKEN, beg, end);
	}
}
