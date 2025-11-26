package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<'
public class LessThanOperatorToken extends OperatorToken {
	public LessThanOperatorToken(int beg, int end) {
		super(SymbolKind.LESS_THAN_OPERATOR_TOKEN, beg, end);
	}
}
