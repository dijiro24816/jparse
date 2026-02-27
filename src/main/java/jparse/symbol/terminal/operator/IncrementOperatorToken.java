package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '++'
public class IncrementOperatorToken extends OperatorToken {
	public IncrementOperatorToken(int beg, int end) {
		super(SymbolEnum.INCREMENT_OPERATOR_TOKEN, beg, end);
	}
}
