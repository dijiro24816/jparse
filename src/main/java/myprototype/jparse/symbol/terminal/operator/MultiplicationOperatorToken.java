package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '*'
public class MultiplicationOperatorToken extends OperatorToken {
	public MultiplicationOperatorToken(int beg, int end) {
		super(SymbolKind.MULTIPLICATION_OPERATOR_TOKEN, beg, end);
	}
}
