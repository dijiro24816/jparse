package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class CurlyBracketOpenSeparatorToken extends SeparatorToken {
	public CurlyBracketOpenSeparatorToken(int beg, int end) {
		super(SymbolEnum.CURLY_BRACKET_OPEN_SEPARATOR_TOKEN, beg, end);
	}
}
