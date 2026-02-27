package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '!='
public class NotEqualOperatorToken extends OperatorToken {
	public NotEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.NOT_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
