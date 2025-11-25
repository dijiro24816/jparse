package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class PeriodSeparatorToken extends SeparatorToken {
	public PeriodSeparatorToken(int beg, int end) {
		super(SymbolKind.PERIOD_SEPARATOR_TOKEN, beg, end);
	}
}
