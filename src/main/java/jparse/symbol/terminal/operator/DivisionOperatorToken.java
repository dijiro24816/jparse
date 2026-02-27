package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '/'
public class DivisionOperatorToken extends OperatorToken {
	public DivisionOperatorToken(int beg, int end) {
		super(SymbolEnum.DIVISION_OPERATOR_TOKEN, beg, end);
	}
}
