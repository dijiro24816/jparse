package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class CurlyBracketCloseSeparatorToken extends SeparatorToken {
	public CurlyBracketCloseSeparatorToken(int beg, int end) {
		super(SymbolKind.CURLY_BRACKET_CLOSE_SEPARATOR_TOKEN, beg, end);
	}
}
