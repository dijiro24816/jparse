package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '<<'
public class LeftShiftOperatorToken extends OperatorToken {
	public LeftShiftOperatorToken(int beg, int end) {
		super(SymbolEnum.LEFT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
