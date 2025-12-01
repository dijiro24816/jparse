package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>='
public class GreaterThanEqualOperatorToken extends OperatorToken {
	public GreaterThanEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.GREATER_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
