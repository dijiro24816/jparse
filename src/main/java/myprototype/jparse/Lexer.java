package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.terminal.InvalidTokenException;

public interface Lexer {
	public Symbol getSymbol(InputStream inStrm) throws IOException, InvalidTokenException;
}
