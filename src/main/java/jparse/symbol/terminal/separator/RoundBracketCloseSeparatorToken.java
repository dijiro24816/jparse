package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class RoundBracketCloseSeparatorToken extends SeparatorToken {
	public RoundBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolEnum.ROUND_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
