package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '++'
public class IncrementOperatorToken extends OperatorToken {
	public IncrementOperatorToken(int beg, int end) {
		super(SymbolKind.INCREMENT_OPERATOR_TOKEN, beg, end);
	}
}
