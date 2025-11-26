package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>='
public class GreaterThanEqualOperatorToken extends OperatorToken {
	public GreaterThanEqualOperatorToken(int beg, int end) {
		super(SymbolKind.GREATER_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
