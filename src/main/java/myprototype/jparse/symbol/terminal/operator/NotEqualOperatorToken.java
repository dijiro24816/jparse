package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '!='
public class NotEqualOperatorToken extends OperatorToken {
	public NotEqualOperatorToken(int beg, int end) {
		super(SymbolEnum.NOT_EQUAL_OPERATOR_TOKEN, beg, end);
	}
}
