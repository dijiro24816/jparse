package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>'
public class GreaterThanOperatorToken extends OperatorToken {
	public GreaterThanOperatorToken(int beg, int end) {
		super(SymbolKind.GREATER_THAN_OPERATOR_TOKEN, beg, end);
	}
}
