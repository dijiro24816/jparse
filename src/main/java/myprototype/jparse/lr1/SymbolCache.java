package myprototype.jparse.lr1;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.terminal.InvalidTokenException;

public class SymbolCache {
	private Symbol[] symbols;
	private int index;
	
	public SymbolCache(int k) {
		this.symbols = new Symbol[k + 1];
		this.index = 0;
	}
	
	private 
	
	public Symbol read(InputStream source) throws IOException, InvalidTokenException {
		if (this.symbol != null) {
			Symbol symbol = this.symbol;
			this.symbol = null;
			return symbol;
		}
		
		return this.lexer.tokenize(source);
	}
	
	public Symbol peek(InputStream source) throws IOException, InvalidTokenException {
		if (this.symbol != null)
			return this.symbol;
		
		return this.symbol = read(source);
	}
}
