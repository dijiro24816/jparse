package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<<='
public class LeftShiftAssignmentOperatorToken extends OperatorToken {
	public LeftShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.LEFT_SHIFT_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
