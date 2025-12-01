package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<'
public class LessThanOperatorToken extends OperatorToken {
	public LessThanOperatorToken(int beg, int end) {
		super(SymbolEnum.LESS_THAN_OPERATOR_TOKEN, beg, end);
	}
}
