package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '|='
public class BitwiseOrAssignmentOperatorToken extends OperatorToken {
	public BitwiseOrAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_OR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
