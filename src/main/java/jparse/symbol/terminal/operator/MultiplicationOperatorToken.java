package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '*'
public class MultiplicationOperatorToken extends OperatorToken {
	public MultiplicationOperatorToken(int beg, int end) {
		super(SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN, beg, end);
	}
}
