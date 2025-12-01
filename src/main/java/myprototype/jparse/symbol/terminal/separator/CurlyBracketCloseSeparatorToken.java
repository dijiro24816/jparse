package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class CurlyBracketCloseSeparatorToken extends SeparatorToken {
	public CurlyBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolEnum.CURLY_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
