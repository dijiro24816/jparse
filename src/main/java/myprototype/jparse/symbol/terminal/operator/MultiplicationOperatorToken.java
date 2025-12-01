package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '*'
public class MultiplicationOperatorToken extends OperatorToken {
	public MultiplicationOperatorToken(int beg, int end) {
		super(SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN, beg, end);
	}
}
