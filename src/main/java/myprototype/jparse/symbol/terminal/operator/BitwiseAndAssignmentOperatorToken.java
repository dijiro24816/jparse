package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&='
public class BitwiseAndAssignmentOperatorToken extends OperatorToken {
	public BitwiseAndAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.BITWISE_AND_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
