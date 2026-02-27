package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '/='
public class DivisionAssignmentOperatorToken extends OperatorToken {
	public DivisionAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.DIVISION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
