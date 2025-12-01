package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '/='
public class DivisionAssignmentOperatorToken extends OperatorToken {
	public DivisionAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.DIVISION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
