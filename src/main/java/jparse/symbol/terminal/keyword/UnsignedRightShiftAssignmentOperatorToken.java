package jparse.symbol.terminal.keyword;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// >>>=
public class UnsignedRightShiftAssignmentOperatorToken extends OperatorToken {
	public UnsignedRightShiftAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.UNSIGNED_RIGHT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
