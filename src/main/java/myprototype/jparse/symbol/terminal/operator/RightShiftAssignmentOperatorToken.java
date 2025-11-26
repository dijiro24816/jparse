package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>>='
public class RightShiftAssignmentOperatorToken extends OperatorToken {
	public RightShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.RIGHT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
