package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<<'
public class LeftShiftOperatorToken extends OperatorToken {
	public LeftShiftOperatorToken(int beg, int end) {
		super(SymbolKind.LEFT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
