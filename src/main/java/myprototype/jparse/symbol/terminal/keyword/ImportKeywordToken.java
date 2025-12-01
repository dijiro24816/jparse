package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ImportKeywordToken extends KeywordToken {
	public ImportKeywordToken(int beg, int end) {
		super(SymbolEnum.IMPORT_KEYWORD_TOKEN, beg, end);
	}
}
