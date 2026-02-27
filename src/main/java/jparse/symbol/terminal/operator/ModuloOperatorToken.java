package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '%'
public class ModuloOperatorToken extends OperatorToken {
	public ModuloOperatorToken(int beg, int end) {
		super(SymbolEnum.MODULO_OPERATOR_TOKEN, beg, end);
	}
}
