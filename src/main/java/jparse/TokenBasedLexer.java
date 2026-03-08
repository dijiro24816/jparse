package jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class TokenBasedLexer extends Lexer {
	public int i = 0;
	public List<Token> symbols;
	
	public TokenBasedLexer(String src) {
		super("$");
		this.symbols = Arrays.asList(src.trim().split("\\s+")).stream().map(e -> new Token(e, -1, -1)).toList();
		this.i = 0;
	}

	@Override
	public Token tokenize(InputStream inStrm) throws IOException, InvalidTokenException {
		if (this.i < this.symbols.size())
			return this.symbols.get(this.i++);

		return null;
	}
}
