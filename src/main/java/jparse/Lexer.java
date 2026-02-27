package jparse;

import java.io.IOException;
import java.io.InputStream;

public interface Lexer {
	public Token getSymbol(InputStream inStrm) throws IOException, InvalidTokenException;
}
