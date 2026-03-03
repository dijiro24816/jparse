package jparse;

import java.io.IOException;
import java.io.InputStream;

public class BufferedLexer {
	private int headIndex;
	private Lexer lexer;
	private int tailIndex;
	private Token[] tokens;

	public BufferedLexer(int lookaheadMax, Lexer lexer) {
		this.tokens = new Token[lookaheadMax + 1];
		this.headIndex = 0;
		this.tailIndex = 0;
		this.lexer = lexer;
	}

	public BufferedLexer(Lexer lexer) {
		this(1, lexer);
	}

	private int distanceBetweenHeadAndTail() {
		return distanceIndex(getTokens().length, getHeadIndex(), getTailIndex());
	}

	private int distanceIndex(int length, int begIndex, int endIndex) {
		return begIndex <= endIndex ? endIndex - begIndex : length - begIndex + endIndex;
	}

	public int getHeadIndex() {
		return headIndex;
	}

	private Token getHeadToken() {
		return getTokens()[getHeadIndex()];
	}

	public Lexer getLexer() {
		return lexer;
	}

	public Token getSymbol(InputStream source) throws IOException, InvalidTokenException {
		if (distanceBetweenHeadAndTail() == 0)
			read(source);

		Token token = getHeadToken();
		setHeadIndex(increaseIndex(getHeadIndex()));
		return token;
	}

	public int getTailIndex() {
		return tailIndex;
	}

	public Token[] getTokens() {
		return tokens;
	}

	private int increaseIndex(int index) {
		return increaseIndex(index, 1);
	}

	private int increaseIndex(int index, int k) {
		return (index + k) % getTokens().length;
	}

	public Token peek(InputStream source) throws IOException, InvalidTokenException {
		return peek(source, 0);
	}

	// MUST: nth < this.lookaheadMax
	public Token peek(InputStream source, int nth) throws IOException, InvalidTokenException {
		int existing = distanceBetweenHeadAndTail() - nth;
		if (existing > 0)
			return getTokens()[increaseIndex(getHeadIndex(), nth)];

		int demand = -existing + 1;
		while (demand-- > 0)
			read(source);

		return getTokens()[increaseIndex(getHeadIndex(), nth)];
	}

	private Token read(InputStream source) throws IOException, InvalidTokenException {
		if (distanceIndex(getTokens().length, getHeadIndex(), increaseIndex(getTailIndex())) == 0)
			throw new RuntimeException("BufferedLexer overflow");

		Token token = getTokens()[getTailIndex()] = getLexer().tokenize(source);
		setTailIndex(increaseIndex(getTailIndex()));
		return token;
	}

	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

	public void setTailIndex(int tailIndex) {
		this.tailIndex = tailIndex;
	}
}
