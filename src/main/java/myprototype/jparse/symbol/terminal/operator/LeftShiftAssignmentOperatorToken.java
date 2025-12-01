package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<<='
public class LeftShiftAssignmentOperatorToken extends OperatorToken {
	public LeftShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.LEFT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
