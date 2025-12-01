package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '||'
public class LogicalOrOperatorToken extends OperatorToken {
	public LogicalOrOperatorToken(int beg, int end) {
		super(SymbolEnum.LOGICAL_OR_OPERATOR_TOKEN, beg, end);
	}
}
