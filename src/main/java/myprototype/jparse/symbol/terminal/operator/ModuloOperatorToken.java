package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '%'
public class ModuloOperatorToken extends OperatorToken {
	public ModuloOperatorToken(int beg, int end) {
		super(SymbolEnum.MODULO_OPERATOR_TOKEN, beg, end);
	}
}
