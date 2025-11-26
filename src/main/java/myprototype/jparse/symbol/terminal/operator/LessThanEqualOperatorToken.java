package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<='
public class LessThanEqualOperatorToken extends OperatorToken {
	public LessThanEqualOperatorToken(int beg, int end) {
		super(SymbolKind.LESS_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
