package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;


// '&='
public class BitwiseXorAssignmentOperatorToken extends OperatorToken {
	public BitwiseXorAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.BITWISE_XOR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
