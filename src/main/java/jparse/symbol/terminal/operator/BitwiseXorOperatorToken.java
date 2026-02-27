package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '^='
public class BitwiseXorOperatorToken extends OperatorToken {
	public BitwiseXorOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_XOR_OPERATOR_TOKEN, beg, end);
	}
}
