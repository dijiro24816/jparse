package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// +=
public class AdditionAssignmentOperatorToken extends OperatorToken {
	public AdditionAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.ADDITION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
