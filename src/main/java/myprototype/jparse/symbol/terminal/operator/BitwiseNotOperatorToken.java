package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '~'
public class BitwiseNotOperatorToken extends OperatorToken {
	public BitwiseNotOperatorToken(int beg, int end) {
		super(SymbolKind.BITWISE_NOT_OPERATOR_TOKEN, beg, end);
	}
}
