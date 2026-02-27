package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '>>='
public class RightShiftAssignmentOperatorToken extends OperatorToken {
	public RightShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.RIGHT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
