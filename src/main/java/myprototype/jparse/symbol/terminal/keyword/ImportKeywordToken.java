package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.KeywordToken;

public class ImportKeywordToken extends KeywordToken {
	public ImportKeywordToken(int beg, int end) {
		super(SymbolKind.IMPORT_KEYWORD_TOKEN, beg, end);
	}
}
