package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class SquareBracketCloseSeparatorToken extends SeparatorToken {
	public SquareBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolEnum.SQUARE_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
