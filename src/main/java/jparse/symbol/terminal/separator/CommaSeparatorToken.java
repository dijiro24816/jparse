package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class CommaSeparatorToken extends SeparatorToken {
	public CommaSeparatorToken(int beg, int end) {
		super(SymbolEnum.COMMA_SEPARATOR_TOKEN, beg, end);
	}
}
