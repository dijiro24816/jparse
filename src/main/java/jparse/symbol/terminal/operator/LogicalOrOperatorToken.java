package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '||'
public class LogicalOrOperatorToken extends OperatorToken {
	public LogicalOrOperatorToken(int beg, int end) {
		super(SymbolEnum.LOGICAL_OR_OPERATOR_TOKEN, beg, end);
	}
}
