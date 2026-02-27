package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '>>>'
public class UnsignedRightShiftOperatorToken extends OperatorToken {
	public UnsignedRightShiftOperatorToken(int beg, int end) {
		super(SymbolEnum.UNSIGNED_RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
