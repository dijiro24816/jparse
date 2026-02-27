package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '&&'
public class LogicalAndOperatorToken extends OperatorToken {
	public LogicalAndOperatorToken(int beg, int end) {
		super(SymbolEnum.LOGICAL_AND_OPERATOR_TOKEN, beg, end);
	}
}
