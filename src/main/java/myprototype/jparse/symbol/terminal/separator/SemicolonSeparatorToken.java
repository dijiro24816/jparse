package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class SemicolonSeparatorToken extends SeparatorToken {
	public SemicolonSeparatorToken(int beg, int end) {
		super(SymbolEnum.SEMICOLON_SEPARATOR_TOKEN, beg, end);
	}
}
