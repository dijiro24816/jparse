package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>'
public class GreaterThanOperatorToken extends OperatorToken {
	public GreaterThanOperatorToken(int beg, int end) {
		super(SymbolEnum.GREATER_THAN_OPERATOR_TOKEN, beg, end);
	}
}
