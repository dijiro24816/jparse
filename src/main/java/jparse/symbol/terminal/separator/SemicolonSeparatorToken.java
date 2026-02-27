package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class SemicolonSeparatorToken extends SeparatorToken {
	public SemicolonSeparatorToken(int beg, int end) {
		super(SymbolEnum.SEMICOLON_SEPARATOR_TOKEN, beg, end);
	}
}
