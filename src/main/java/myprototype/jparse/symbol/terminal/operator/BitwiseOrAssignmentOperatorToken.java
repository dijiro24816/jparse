package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '|='
public class BitwiseOrAssignmentOperatorToken extends OperatorToken {
	public BitwiseOrAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.BITWISE_OR_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
