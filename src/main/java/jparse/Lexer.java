package jparse;

import java.io.IOException;
import java.io.InputStream;

public interface Lexer {
	public Token tokenize(InputStream inStrm) throws IOException, InvalidTokenException;
}
