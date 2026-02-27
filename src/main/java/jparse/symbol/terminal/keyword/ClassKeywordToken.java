package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ClassKeywordToken extends KeywordToken {
	public ClassKeywordToken(int beg, int end) {
		super(SymbolEnum.CLASS_KEYWORD_TOKEN, beg, end);
	}
}
