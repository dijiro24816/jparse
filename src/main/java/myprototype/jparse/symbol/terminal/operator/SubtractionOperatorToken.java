package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '-'
public class SubtractionOperatorToken extends OperatorToken {
	public SubtractionOperatorToken(int beg, int end) {
		super(SymbolKind.SUBTRACTION_OPERATOR_TOKEN, beg, end);
	}
}
