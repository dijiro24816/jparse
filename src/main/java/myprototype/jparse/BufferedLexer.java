package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.JavaLexer;

public class BufferedLexer {
	private Symbol[] symbols;
	private int headIndex;
	private int tailIndex;
	
	private JavaLexer lexer;
	
	public BufferedLexer(int lookaheadMax, JavaLexer lexer) {
		this.symbols = new Symbol[lookaheadMax + 1];
		this.headIndex = 0;
		this.tailIndex = 0;
		
		this.lexer = lexer;
	}
	
	public BufferedLexer(JavaLexer lexer) { 
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
	
	private Symbol getHeadSymbol() {
		return this.symbols[this.headIndex];
	}
	
	private Symbol read(InputStream source) throws IOException, InvalidTokenException {
		if (distanceIndex(this.symbols.length, this.headIndex, increaseIndex(this.tailIndex)) == 0)
			throw new RuntimeException("BufferedLexer overflow");
		
		Symbol symbol = this.symbols[this.tailIndex] = this.lexer.getSymbol(source);
		this.tailIndex = increaseIndex(this.tailIndex);
		return symbol;
	}
	
	public Symbol getSymbol(InputStream source) throws IOException, InvalidTokenException {
		if (distanceBetweenHeadAndTail() == 0)
			read(source);
		
		Symbol symbol = getHeadSymbol();
		this.headIndex = increaseIndex(this.headIndex);
		return symbol;
	}
	
	// MUST: nth < this.lookaheadMax
	public Symbol peek(InputStream source, int nth) throws IOException, InvalidTokenException {
		int existing = distanceBetweenHeadAndTail() - nth;
		if (existing > 0)
			return this.symbols[increaseIndex(this.headIndex, nth)];
		
		int demand = -existing + 1;
		while (demand-- > 0)
			read(source);
		
		return this.symbols[increaseIndex(this.headIndex, nth)];
	}
	
	public Symbol peek(InputStream source) throws IOException, InvalidTokenException {
		return peek(source, 0);
	}
}
