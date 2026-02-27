package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;


// '&='
public class BitwiseXorAssignmentOperatorToken extends OperatorToken {
	public BitwiseXorAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_XOR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
