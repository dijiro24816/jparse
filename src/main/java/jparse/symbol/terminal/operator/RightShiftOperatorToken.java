package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '>>'
public class RightShiftOperatorToken extends OperatorToken {
	public RightShiftOperatorToken(int beg, int end) {
		super(SymbolEnum.RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
