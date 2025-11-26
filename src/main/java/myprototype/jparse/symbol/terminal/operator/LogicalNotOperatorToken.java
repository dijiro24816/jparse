package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '!'
public class LogicalNotOperatorToken extends OperatorToken {
	public LogicalNotOperatorToken(int beg, int end) {
		super(SymbolKind.LOGICAL_NOT_OPERATOR_TOKEN, beg, end);
	}
}
