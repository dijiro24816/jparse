package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// +
public class AdditionOperatorToken extends OperatorToken {
	public AdditionOperatorToken(int beg, int end) {
		super(SymbolEnum.ADDITION_OPERATOR_TOKEN, beg, end);
	}
}
