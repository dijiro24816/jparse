package jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import jparse.symbol.terminal.InvalidTokenException;

public class TokenBasedLexer implements Lexer {
	public List<Token> symbols;
	public int i = 0;
	
	public TokenBasedLexer(String src) {
		this.symbols = Arrays.asList(src.trim().split("\\s+")).stream().map(e -> new Token(e, -1, -1)).toList();
		this.i = 0;
	}

	@Override
	public Token getSymbol(InputStream inStrm) throws IOException, InvalidTokenException {
		if (this.i < this.symbols.size())
			return this.symbols.get(this.i++);

		return null;
	}
}
