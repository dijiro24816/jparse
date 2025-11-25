package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// +=
public class AdditionAssignmentOperatorToken extends OperatorToken {
	public AdditionAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.ADDITION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
