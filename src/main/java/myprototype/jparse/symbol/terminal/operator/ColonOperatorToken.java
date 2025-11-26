package myprototype.jparse.symbol.terminal.operator;

import myprototype.jparse.symbol.SymbolKind;
import myprototype.jparse.symbol.terminal.OperatorToken;

// ':'
public class ColonOperatorToken extends OperatorToken {
	public ColonOperatorToken(int beg, int end) {
		super(SymbolKind.COLON_OPERATOR_TOKEN, beg, end);
	}
}
