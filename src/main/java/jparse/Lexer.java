package jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Lexer {
	protected String endSymbol;
	
	protected Lexer(String endSymbol) {
		this.endSymbol = endSymbol;
	}
	
	public abstract Token tokenize(InputStream inStrm) throws IOException, InvalidTokenException;
	
	public List<Token> tokenizeAll(InputStream inStrm) throws IOException, InvalidTokenException {
		ArrayList<Token> tokens = new ArrayList<>();
		for (;;) {
			Token symbol = tokenize(inStrm);

			if (symbol.label().equals(endSymbol)) {
				tokens.add(symbol);
				break;
			}

			tokens.add(symbol);
		}
		
		return tokens;
	}
}
