package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// '%='
public class ModuloAssignmentOperatorToken extends OperatorToken {
	public ModuloAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.MODULO_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
