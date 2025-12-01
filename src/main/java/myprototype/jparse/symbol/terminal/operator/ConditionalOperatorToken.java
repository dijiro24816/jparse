package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '?'
public class ConditionalOperatorToken extends OperatorToken {
	public ConditionalOperatorToken(int beg, int end) {
		super(SymbolEnum.CONDITIONAL_OPERATOR_TOKEN, beg, end);
	}
}
