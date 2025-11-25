package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class CommaSeparatorToken extends SeparatorToken {
	public CommaSeparatorToken(int beg, int end) {
		super(SymbolKind.COMMA_SEPARATOR_TOKEN, beg, end);
	}
}
