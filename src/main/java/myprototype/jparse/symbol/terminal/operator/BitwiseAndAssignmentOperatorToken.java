package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&='
public class BitwiseAndAssignmentOperatorToken extends OperatorToken {
	public BitwiseAndAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_AND_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
