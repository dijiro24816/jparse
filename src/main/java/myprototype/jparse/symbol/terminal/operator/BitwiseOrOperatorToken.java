package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '|'
public class BitwiseOrOperatorToken extends OperatorToken {
	public BitwiseOrOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_OR_OPERATOR_TOKEN, beg, end);
	}
}
