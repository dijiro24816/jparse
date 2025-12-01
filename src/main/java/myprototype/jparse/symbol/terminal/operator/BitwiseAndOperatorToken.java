package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&'
public class BitwiseAndOperatorToken extends OperatorToken {
	public BitwiseAndOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_AND_OPERATOR_TOKEN, beg, end);
	}
}
