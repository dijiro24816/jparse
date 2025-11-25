package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SquareBracketCloseSeparatorToken extends SeparatorToken {
	public SquareBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolKind.SQUARE_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
