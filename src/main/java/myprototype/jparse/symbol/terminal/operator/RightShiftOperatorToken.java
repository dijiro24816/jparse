package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '>>'
public class RightShiftOperatorToken extends OperatorToken {
	public RightShiftOperatorToken(int beg, int end) {
		super(SymbolKind.RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
