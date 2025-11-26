package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '&&'
public class LogicalAndOperatorToken extends OperatorToken {
	public LogicalAndOperatorToken(int beg, int end) {
		super(SymbolKind.LOGICAL_AND_OPERATOR_TOKEN, beg, end);
	}
}
