package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '?'
public class ConditionalOperatorToken extends OperatorToken {
	public ConditionalOperatorToken(int beg, int end) {
		super(SymbolEnum.CONDITIONAL_OPERATOR_TOKEN, beg, end);
	}
}
