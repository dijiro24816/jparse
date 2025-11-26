package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '/'
public class DivisionOperatorToken extends OperatorToken {
	public DivisionOperatorToken(int beg, int end) {
		super(SymbolKind.DIVISION_OPERATOR_TOKEN, beg, end);
	}
}
