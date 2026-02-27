package jparse.symbol.terminal.separator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.SeparatorToken;

public class PeriodSeparatorToken extends SeparatorToken {
	public PeriodSeparatorToken(int beg, int end) {
		super(SymbolEnum.PERIOD_SEPARATOR_TOKEN, beg, end);
	}
}
