package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '='
public class AssignmentOperatorToken extends OperatorToken {
	public AssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
