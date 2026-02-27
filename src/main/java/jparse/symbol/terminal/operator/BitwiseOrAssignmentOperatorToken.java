package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '|='
public class BitwiseOrAssignmentOperatorToken extends OperatorToken {
	public BitwiseOrAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_OR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
