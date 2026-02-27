package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '<'
public class LessThanOperatorToken extends OperatorToken {
	public LessThanOperatorToken(int beg, int end) {
		super(SymbolEnum.LESS_THAN_OPERATOR_TOKEN, beg, end);
	}
}
