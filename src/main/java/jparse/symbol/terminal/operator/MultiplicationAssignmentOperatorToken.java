package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '*='
public class MultiplicationAssignmentOperatorToken extends OperatorToken {
	public MultiplicationAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.MULTIPLICATION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
