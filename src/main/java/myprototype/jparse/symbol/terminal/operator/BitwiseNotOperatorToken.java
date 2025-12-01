package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '~'
public class BitwiseNotOperatorToken extends OperatorToken {
	public BitwiseNotOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_NOT_OPERATOR_TOKEN, beg, end);
	}
}
