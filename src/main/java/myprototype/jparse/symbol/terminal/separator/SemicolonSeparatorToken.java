package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SemicolonSeparatorToken extends SeparatorToken {
	public SemicolonSeparatorToken(int beg, int end) {
		super(SymbolKind.SEMICOLON_SEPARATOR_TOKEN, beg, end);
	}
}
