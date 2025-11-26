package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '%='
public class ModuloAssignmentOperatorToken extends OperatorToken {
	public ModuloAssignmentOperatorToken(int beg, int end) {
		super(SymbolKind.MODULO_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
