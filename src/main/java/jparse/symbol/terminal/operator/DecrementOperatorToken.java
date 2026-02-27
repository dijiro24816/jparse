package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '--'
public class DecrementOperatorToken extends OperatorToken {
	public DecrementOperatorToken(int beg, int end) {
		super(SymbolEnum.DECREMENT_OPERATOR_TOKEN, beg, end);
	}
}
