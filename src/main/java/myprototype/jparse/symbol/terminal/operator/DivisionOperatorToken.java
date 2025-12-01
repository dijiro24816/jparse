package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '/'
public class DivisionOperatorToken extends OperatorToken {
	public DivisionOperatorToken(int beg, int end) {
		super(SymbolEnum.DIVISION_OPERATOR_TOKEN, beg, end);
	}
}
