package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.KeywordToken;

public class ImportKeywordToken extends KeywordToken {
	public ImportKeywordToken(int beg, int end) {
		super(SymbolEnum.IMPORT_KEYWORD_TOKEN, beg, end);
	}
}
