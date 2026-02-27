package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '<<='
public class LeftShiftAssignmentOperatorToken extends OperatorToken {
	public LeftShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.LEFT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
