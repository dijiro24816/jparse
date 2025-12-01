package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SquareBracketOpenSeparatorToken extends SeparatorToken {
	public SquareBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolEnum.SQUARE_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
