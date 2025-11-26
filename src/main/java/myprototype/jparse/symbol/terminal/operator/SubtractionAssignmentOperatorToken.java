package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '-='
public class SubtractionAssignmentOperatorToken extends OperatorToken {
	public SubtractionAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.SUBTRACTION_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
