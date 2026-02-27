package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '=='
public class EqualOperatorToken extends OperatorToken {
	public EqualOperatorToken(int beg, int end) {
		super(SymbolEnum.EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
