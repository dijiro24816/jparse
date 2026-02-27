package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ProtectedKeywordToken extends KeywordToken {
	public ProtectedKeywordToken(int beg, int end) {
		super(SymbolEnum.PROTECTED_KEYWORD_TOKEN, beg, end);
	}
}
