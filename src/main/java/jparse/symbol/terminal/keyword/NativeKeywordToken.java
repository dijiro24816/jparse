package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class NativeKeywordToken extends KeywordToken {
	public NativeKeywordToken(int beg, int end) {
		super(SymbolEnum.NATIVE_KEYWORD_TOKEN, beg, end);
	}
}
