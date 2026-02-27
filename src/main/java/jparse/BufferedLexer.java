package jparse;

import java.io.IOException;
import java.io.InputStream;

import jparse.symbol.terminal.InvalidTokenException;

public class BufferedLexer {
	private Token[] symbols;
	private int headIndex;
	private int tailIndex;
	
	private Lexer lexer;
	
	public BufferedLexer(int lookaheadMax, Lexer lexer) {
		this.symbols = new Token[lookaheadMax + 1];
		this.headIndex = 0;
		this.tailIndex = 0;
		
		this.lexer = lexer;
	}
	
	public BufferedLexer(Lexer lexer) { 
		this(1, lexer);
	}
	
	private int increaseIndex(int index, int k) {
		return (index + k) % this.symbols.length;
	}
	
	private int increaseIndex(int index) {
		return increaseIndex(index, 1);
	}
	
	private int distanceIndex(int length, int begIndex, int endIndex) {
		return begIndex <= endIndex ? endIndex - begIndex : length - begIndex + endIndex;
	}
	
	private int distanceBetweenHeadAndTail() {
		return distanceIndex(this.symbols.length, this.headIndex, this.tailIndex);
	}
	
	private Token getHeadSymbol() {
		return this.symbols[this.headIndex];
	}
	
	private Token read(InputStream source) throws IOException, InvalidTokenException {
		if (distanceIndex(this.symbols.length, this.headIndex, increaseIndex(this.tailIndex)) == 0)
			throw new RuntimeException("BufferedLexer overflow");
		
		Token symbol = this.symbols[this.tailIndex] = this.lexer.getSymbol(source);
		this.tailIndex = increaseIndex(this.tailIndex);
		return symbol;
	}
	
	public Token getSymbol(InputStream source) throws IOException, InvalidTokenException {
		if (distanceBetweenHeadAndTail() == 0)
			read(source);
		
		Token symbol = getHeadSymbol();
		this.headIndex = increaseIndex(this.headIndex);
		return symbol;
	}
	
	// MUST: nth < this.lookaheadMax
	public Token peek(InputStream source, int nth) throws IOException, InvalidTokenException {
		int existing = distanceBetweenHeadAndTail() - nth;
		if (existing > 0)
			return this.symbols[increaseIndex(this.headIndex, nth)];
		
		int demand = -existing + 1;
		while (demand-- > 0)
			read(source);
		
		return this.symbols[increaseIndex(this.headIndex, nth)];
	}
	
	public Token peek(InputStream source) throws IOException, InvalidTokenException {
		return peek(source, 0);
	}
}
