package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SquareBracketOpenSeparatorToken extends SeparatorToken {
	public SquareBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolKind.SQUARE_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
