package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '-'
public class SubtractionOperatorToken extends OperatorToken {
	public SubtractionOperatorToken(int beg, int end) {
		super(SymbolEnum.SUBTRACTION_OPERATOR_TOKEN, beg, end);
	}
}
