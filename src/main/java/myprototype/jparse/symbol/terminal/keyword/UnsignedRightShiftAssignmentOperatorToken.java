package myprototype.jparse.symbol.terminal.keyword;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// >>>=
public class UnsignedRightShiftAssignmentOperatorToken extends OperatorToken {
	public UnsignedRightShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.UNSIGNED_RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
