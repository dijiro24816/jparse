package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// '%='
public class ModuloAssignmentOperatorToken extends OperatorToken {
	public ModuloAssignmentOperatorToken(int beg, int end) {
		super(SymbolEnum.MODULO_ASSIGNMENT_OPERATOR_TOKEN, beg, end);
	}
}
