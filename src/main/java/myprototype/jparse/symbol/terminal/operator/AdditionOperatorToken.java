package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// +
public class AdditionOperatorToken extends OperatorToken {
	public AdditionOperatorToken(int beg, int end) {
		super(SymbolKind.ADDITION_OPERATOR_TOKEN, beg, end);
	}
}
