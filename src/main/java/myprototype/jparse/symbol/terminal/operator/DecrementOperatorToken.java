package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '--'
public class DecrementOperatorToken extends OperatorToken {
	public DecrementOperatorToken(int beg, int end) {
		super(SymbolEnum.DECREMENT_OPERATOR_TOKEN, beg, end);
	}
}
