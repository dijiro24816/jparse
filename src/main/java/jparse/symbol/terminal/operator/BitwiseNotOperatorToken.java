package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '~'
public class BitwiseNotOperatorToken extends OperatorToken {
	public BitwiseNotOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_NOT_OPERATOR_TOKEN, beg, end);
	}
}
