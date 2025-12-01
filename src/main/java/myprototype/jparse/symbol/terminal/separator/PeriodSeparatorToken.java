package myprototype.jparse.symbol.terminal.separator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.SeparatorToken;

public class PeriodSeparatorToken extends SeparatorToken {
	public PeriodSeparatorToken(int beg, int end) {
		super(SymbolEnum.PERIOD_SEPARATOR_TOKEN, beg, end);
	}
}
