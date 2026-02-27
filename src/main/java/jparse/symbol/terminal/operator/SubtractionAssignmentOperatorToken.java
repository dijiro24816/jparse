package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '-='
public class SubtractionAssignmentOperatorToken extends OperatorToken {
	public SubtractionAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.SUBTRACTION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
