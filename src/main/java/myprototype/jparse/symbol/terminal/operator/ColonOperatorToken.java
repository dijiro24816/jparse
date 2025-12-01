package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.OperatorToken;

// ':'
public class ColonOperatorToken extends OperatorToken {
	public ColonOperatorToken(int beg, int end) {
		super(SymbolEnum.COLON_OPERATOR_TOKEN, beg, end);
	}
}
