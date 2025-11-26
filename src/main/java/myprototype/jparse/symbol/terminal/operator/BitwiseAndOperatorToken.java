package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&'
public class BitwiseAndOperatorToken extends OperatorToken {
	public BitwiseAndOperatorToken(int beg, int end) {
		super(SymbolKind.BITWISE_AND_OPERATOR_TOKEN, beg, end);
	}
}
