package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '>='
public class GreaterThanEqualOperatorToken extends OperatorToken {
	public GreaterThanEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.GREATER_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
