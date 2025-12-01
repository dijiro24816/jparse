package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&&'
public class LogicalAndOperatorToken extends OperatorToken {
	public LogicalAndOperatorToken(int beg, int end) {
		super(SymbolEnum.LOGICAL_AND_OPERATOR_TOKEN, beg, end);
	}
}
