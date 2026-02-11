package myprototype.jparse.symbol.terminal;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.Symbol;

public interface Lexer {
	public Symbol getSymbol(InputStream inStrm) throws IOException, InvalidTokenException;
}
