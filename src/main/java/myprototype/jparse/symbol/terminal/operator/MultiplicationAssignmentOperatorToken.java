package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '*='
public class MultiplicationAssignmentOperatorToken extends OperatorToken {
	public MultiplicationAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.MULTIPLICATION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
