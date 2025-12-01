package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '=='
public class EqualOperatorToken extends OperatorToken {
	public EqualOperatorToken(int beg, int end) {
		super(SymbolEnum.EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
