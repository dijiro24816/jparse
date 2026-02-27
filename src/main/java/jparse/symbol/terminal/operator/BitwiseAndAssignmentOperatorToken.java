package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '&='
public class BitwiseAndAssignmentOperatorToken extends OperatorToken {
	public BitwiseAndAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_AND_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
