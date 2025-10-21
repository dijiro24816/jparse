package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;

public class Tokenizer {
	public InputStream inputStream;
	
	public int lineNumber;
	public int columnNumber;
	
	class TokenBuffer {
		public StringBuilder stringBuilder = new StringBuilder();
		
		public void push(int character) {
			stringBuilder.append(character);
		}
		
		public String pop(int length) {
			String string = stringBuilder.substring(0, length);
			stringBuilder.delete(0, length);
			return string;
		}
		
		public int length() {
			return stringBuilder.length();
		}
	}
	
	public TokenBuffer buffer = new TokenBuffer();
	
	public Tokenizer(InputStream inputStream, int lineNumber, int columnNumber) {
		super();
		this.inputStream = inputStream;
		this.lineNumber = lineNumber;
		this.columnNumber = columnNumber;
	}
	
	
	public Token extractAfterDigit() {
		
//		while ((ch = inputStream.read()) != -1 && Character.isDigit(ch))
//			buffer.push(ch);
		return new LiteralToken(0, 0);
	}
	
	public Token extractAfterAlphabet() {
		return new LiteralToken(0, 0);
	}

	public Token tokenize() throws IOException {
		boolean is_identifier;
		boolean is_number;
		
		int ch;
		while ((ch = inputStream.read()) != -1) {
			buffer.push(ch);
			if (Character.isDigit(ch))
				return extractAfterDigit();
				
			if (Character.isAlphabetic(ch)) {
				
			}
			
		}
		
		return new Token(0, 0);
	}
}
