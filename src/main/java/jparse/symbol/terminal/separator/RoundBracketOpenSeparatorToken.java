package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class RoundBracketOpenSeparatorToken extends SeparatorToken {
	public RoundBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolEnum.ROUND_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
