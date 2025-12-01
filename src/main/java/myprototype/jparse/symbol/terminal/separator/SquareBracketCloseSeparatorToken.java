package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SquareBracketCloseSeparatorToken extends SeparatorToken {
	public SquareBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolEnum.SQUARE_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
