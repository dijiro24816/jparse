package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '^='
public class BitwiseXorOperatorToken extends OperatorToken {
	public BitwiseXorOperatorToken(int beg, int end) {
		super(SymbolEnum.BITWISE_XOR_OPERATOR_TOKEN, beg, end);
	}
}
