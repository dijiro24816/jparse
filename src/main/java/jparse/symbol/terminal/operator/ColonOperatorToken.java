package jparse.symbol.terminal.operator;

import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.OperatorToken;

// ':'
public class ColonOperatorToken extends OperatorToken {
	public ColonOperatorToken(int beg, int end) {
		super(SymbolEnum.COLON_OPERATOR_TOKEN, beg, end);
	}
}
