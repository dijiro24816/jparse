package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '<<'
public class LeftShiftOperatorToken extends OperatorToken {
	public LeftShiftOperatorToken(int beg, int end) {
		super(SymbolEnum.LEFT_SHIFT_OPERATOR_TOKEN, beg, end);
	}
}
