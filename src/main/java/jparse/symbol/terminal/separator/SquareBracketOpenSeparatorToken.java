package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class SquareBracketOpenSeparatorToken extends SeparatorToken {
	public SquareBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolEnum.SQUARE_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
