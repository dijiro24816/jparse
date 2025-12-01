package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '='
public class AssignmentOperatorToken extends OperatorToken {
	public AssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
