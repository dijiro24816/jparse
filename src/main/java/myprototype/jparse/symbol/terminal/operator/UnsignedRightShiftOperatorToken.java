package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>>>'
public class UnsignedRightShiftOperatorToken extends OperatorToken {
	public UnsignedRightShiftOperatorToken(int beg, int end) {
		super(SymbolKind.UNSIGNED_RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
