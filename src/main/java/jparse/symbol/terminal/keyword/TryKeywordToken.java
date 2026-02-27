package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class TryKeywordToken extends KeywordToken {
	public TryKeywordToken(int beg, int end) {
		super(SymbolEnum.TRY_KEYWORD_TOKEN, beg, end);
	}
}
