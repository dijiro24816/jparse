package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '!'
public class LogicalNotOperatorToken extends OperatorToken {
	public LogicalNotOperatorToken(int beg, int end) {
		super(SymbolEnum.LOGICAL_NOT_OPERATOR_TOKEN, beg, end);
	}
}
