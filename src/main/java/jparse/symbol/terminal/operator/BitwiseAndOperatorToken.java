package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '&'
public class BitwiseAndOperatorToken extends OperatorToken {
	public BitwiseAndOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_AND_OPERATOR_TOKEN, beg, end);
	}
}
