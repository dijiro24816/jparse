package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<='
public class LessThanEqualOperatorToken extends OperatorToken {
	public LessThanEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.LESS_THAN_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
