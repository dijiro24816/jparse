package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class IntKeywordToken extends KeywordToken {
	public IntKeywordToken(int beg, int end) {
		super(SymbolEnum.INT_KEYWORD_TOKEN, beg, end);
	}

//	@Override
//	public String toString() {
//		return "IntKeywordToken [beg=" + this.beg + " end=" + this.end +"]";
//	}
}
