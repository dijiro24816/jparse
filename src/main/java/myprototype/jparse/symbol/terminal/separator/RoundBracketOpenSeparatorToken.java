package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class RoundBracketOpenSeparatorToken extends SeparatorToken {
	public RoundBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolEnum.ROUND_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
