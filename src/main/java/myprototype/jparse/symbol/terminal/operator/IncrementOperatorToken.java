package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '++'
public class IncrementOperatorToken extends OperatorToken {
	public IncrementOperatorToken(int beg, int end) {
		super(SymbolEnum.INCREMENT_OPERATOR_TOKEN, beg, end);
	}
}
