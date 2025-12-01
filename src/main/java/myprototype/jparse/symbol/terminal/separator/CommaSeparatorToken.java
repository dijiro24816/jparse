package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class CommaSeparatorToken extends SeparatorToken {
	public CommaSeparatorToken(int beg, int end) {
		super(SymbolEnum.COMMA_SEPARATOR_TOKEN, beg, end);
	}
}
