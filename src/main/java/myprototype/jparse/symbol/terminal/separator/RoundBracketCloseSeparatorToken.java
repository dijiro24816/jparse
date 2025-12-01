package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class RoundBracketCloseSeparatorToken extends SeparatorToken {
	public RoundBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolEnum.ROUND_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
