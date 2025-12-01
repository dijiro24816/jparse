package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;


// '&='
public class BitwiseXorAssignmentOperatorToken extends OperatorToken {
	public BitwiseXorAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_XOR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
