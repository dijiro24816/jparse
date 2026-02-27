package jparse;

import java.io.IOException;
import java.io.InputStream;

import jparse.symbol.terminal.InvalidTokenException;

public interface Lexer {
	public Token getSymbol(InputStream inStrm) throws IOException, InvalidTokenException;
}
