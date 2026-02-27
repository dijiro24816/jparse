package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '|'
public class BitwiseOrOperatorToken extends OperatorToken {
	public BitwiseOrOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_OR_OPERATOR_TOKEN, beg, end);
	}
}
