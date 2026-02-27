package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '<='
public class LessThanEqualOperatorToken extends OperatorToken {
	public LessThanEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.LESS_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
