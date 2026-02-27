package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '>'
public class GreaterThanOperatorToken extends OperatorToken {
	public GreaterThanOperatorToken(int beg, int end) {
		super(SymbolEnum.GREATER_THAN_OPERATOR_TOKEN, beg, end);
	}
}
