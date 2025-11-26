package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '%'
public class ModuloOperatorToken extends OperatorToken {
	public ModuloOperatorToken(int beg, int end) {
		super(SymbolKind.MODULO_OPERATOR_TOKEN, beg, end);
	}
}
