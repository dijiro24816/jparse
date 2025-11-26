package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '--'
public class DecrementOperatorToken extends OperatorToken {
	public DecrementOperatorToken(int beg, int end) {
		super(SymbolKind.DECREMENT_OPERATOR_TOKEN, beg, end);
	}
}
