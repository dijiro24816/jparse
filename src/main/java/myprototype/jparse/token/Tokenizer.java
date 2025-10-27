package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;
import java.util.HexFormat;

public class Tokenizer {
	public int lineNumber;
	public int columnNumber;

	class TokenBuffer {
		public StringBuilder stringBuilder = new StringBuilder();
		public int count = 0; // Counting from beginning of InputStream
		public int index = 0;

		public int peek(InputStream inStrm) throws IOException {
			// If stringBuilder stocks next character, return stringBuilder's character
			if (index < length())
				return stringBuilder.charAt(index);

			int ch = inStrm.read();
			count++;

			// (new StringBuilder()).append((int)v);
			// (new StringBuilder()).append((char)v);
			// behave differ
			stringBuilder.append((char) ch);
			return ch;
		}

		// Peek character after the specified count
		public int peek(InputStream inStrm, int nth) throws IOException {
			int index = this.index;
			for (int i = 0; i < nth; i++)
				read(inStrm);

			int ch = peek(inStrm);
			this.index = index;
			return ch;
		}

		public int read(InputStream inStrm) throws IOException {
			int ch = peek(inStrm);
			index++;
			return ch;
		}

		// Read character without putting on stringBuilder
		public int skip(InputStream inStrm) throws IOException {
			int ch = inStrm.read();
			count++;
			return ch;
		}

		public void unskip(InputStream inStrm, char ch) throws IOException {
			stringBuilder.append(ch);
		}

		// Tokenizer should use this method, before and after call pop() to get token's begginning index and end index. 
		public int getFirstByteCount() {
			return count - length();
		}

		public void erase(int length) {
			stringBuilder.delete(0, length);
			index = 0; // reset index
		}
		
		public String substring(int length) {
			return stringBuilder.substring(0, length);
		}

		// Extract from stringBuilder, but keep stringBuilder's character after the specified length
		public String extract(int length) {
			String string = substring(length);
			erase(length);
			return string;
		}

		public int length() {
			return stringBuilder.length();
		}
	}

	public TokenBuffer textBuffer = new TokenBuffer();

	public Token extractAfterJavaLetter(InputStream inStrm) throws IOException {
		int ch, length = 0;
		while ((ch = textBuffer.read(inStrm)) >= 0 && (isJavaLetter(ch) || Character.isDigit(ch)))
			length++;

		int beg = textBuffer.getFirstByteCount();
		String s = textBuffer.extract(length);
		int end = textBuffer.getFirstByteCount();
		
		
		Token tok;
		
		tok = KeywordToken.capture(beg, end, s);
		if (tok != null) return tok;
		
		tok = NullLiteralToken.capture(beg, end, s);
		if (tok != null) return tok;
		
		return new IdentifierToken(beg, end, s);
	}

	public Token extractAfterDigit(InputStream inStrm) throws IOException {
		int ch = textBuffer.peek(inStrm);
		int length = 0;
		int beg = textBuffer.getFirstByteCount();
		int end;
		String s;
		long value = 0;
		
		if (ch != '0') { // Decimal Integer Literal
			while ((ch = textBuffer.read(inStrm)) >= 0) {
				int num = ch - '0';
				if (num <= 0 || num > 9)
					break;
				
				length++;
			}
			
			s = textBuffer.extract(length);
			end = textBuffer.getFirstByteCount();
			
			value = Long.parseLong(s);
			
			return new IntegerLiteralToken(beg, end, value);
		}
		
		switch (textBuffer.peek(inStrm, 1)) {
		case 'x':
		case 'X': // Hexdecimal Integer Literal
			textBuffer.erase(2);
			while ((ch = textBuffer.read(inStrm)) >= 0) {
				int num;
				
				if (ch >= '0' && ch <= '9')
					num = ch - '0';
				else if (ch >= 'a' && ch <= 'f')
					num = ch - 'a';
				else if (ch >= 'A' && ch <= 'F')
					num = ch - 'A';
				else
					break;
				
				length++;
			}
			
			s = textBuffer.extract(length);
			end = textBuffer.getFirstByteCount();
			
			value = HexFormat.fromHexDigitsToLong(s);
			
			return new IntegerLiteralToken(beg, end, value);
			
		case 'b':
		case 'B': {
			// Binary Integer Literal
			textBuffer.erase(2);
			while ((ch = textBuffer.read(inStrm)) >= 0) {
				if (ch < '0'|| ch > '1')
					break;
				
				length++;
			}
			
			s = textBuffer.extract(length);
			end = textBuffer.getFirstByteCount();
			
			int m = 1;
			int i = s.length();
			while ((i--) > 0) {
				int num = s.charAt(i) - '0';
				value += num * m;
				m *= 2;
			}
			
			return new IntegerLiteralToken(beg, end, value);
		}
		
		default: {
			// Octal Integer Literal or 0
			textBuffer.erase(1);
			
			while ((ch = textBuffer.read(inStrm)) >= 0) {
				if (ch < '0' || ch > '7')
					break;
				
				length++;
			}
			
			s = textBuffer.extract(length);
			end = textBuffer.getFirstByteCount();
			
			
			int m = 1;
			int i = s.length();
			while ((i--) > 0) {
				int num = s.charAt(i) - '0';
				value += num * m;
				m *= 8;
			}
			
			return new IntegerLiteralToken(beg, end, value);
		}
			
		}
		
		
//		textBuffer.erase(1);
//		end = textBuffer.getFirstByteCount();
//		return new IntegerLiteralToken(beg, end, 0);
	}
	
	public Token eatOperatorToken(int n) {
		int beg = textBuffer.getFirstByteCount();
		String symbols = textBuffer.extract(n);
		int end = beg + n;
		return new OperatorToken(beg, end, symbols);
	}
	
	public Token eatSeparatorToken() {
		int beg = textBuffer.getFirstByteCount();
		int symbol = textBuffer.extract(1).charAt(0);
		int end = beg + 1;
		return new SeparatorToken(beg, end, symbol);
	}
	
	public Token extractSymbol(InputStream inStrm) throws IOException {
		int beg = textBuffer.getFirstByteCount();
		int end;
		int ch = textBuffer.peek(inStrm);
		String symbols;
		
		switch (ch) {
		case '(':
		case ')':
		case '{':
		case '}':
		case '[':
		case ']':
		case ';':
		case ',':
		case '.':
			return eatSeparatorToken();
		}
		
		for (int i = 0; i < 4; i++)
			textBuffer.peek(inStrm, i);
		
		String peekS = textBuffer.substring(4);
		System.out.println(peekS.length());
		switch (peekS.charAt(0)) {
		case '=':
			if (peekS.charAt(1) == '=') {
				symbols = textBuffer.extract(2);
				end = textBuffer.getFirstByteCount();
				return new OperatorToken(beg, end, symbols);
			}
			
			return eatOperatorToken(1);
			
		case '>':
			return eatOperatorToken(1);
			
		case '<':
			switch (peekS.charAt(1)) {
			case '=':
				return eatOperatorToken(1);
				
			case '<':
				if (peekS.charAt(3) == '=') {
					
				}
			}
			
			return eatOperatorToken(1);
			
		case '!':
			return eatOperatorToken(1);
		
		case '~':
			return eatOperatorToken(1);

		case '?':
			return eatOperatorToken(1);
			
		case '-':
		
		case '?':
		
		case ':':
		
		}
		
		return null;
	}
	
	public boolean isJavaLetter(int ch) {
		if (Character.isAlphabetic(ch) || ch == '_')
			return true;
		
		return false;
	}

	public Token tokenize(InputStream inStrm) throws IOException {
		int ch;
		while ((ch = textBuffer.peek(inStrm)) >= 0) {
			if (ch != '\n' && (ch < ' ' || ch > 126)) // Invaild character
				break;

			// Skip whitespace
			if (Character.isWhitespace(ch)) {
				textBuffer.erase(1);

				while ((ch = textBuffer.skip(inStrm)) >= 0) {
					if (!Character.isWhitespace(ch)) {
						textBuffer.unskip(inStrm, (char) ch);
						break;
					}
				}
			}

			// Skip comment
			if (ch == '/' && textBuffer.peek(inStrm, 1) == '*') {
				textBuffer.erase(2); // Erase stored character '/' and '*'

				int ch1, ch2 = 0;
				do {
					ch1 = ch2;

				} while ((ch2 = textBuffer.skip(inStrm)) >= 0 && (ch1 != '*' || ch2 != '/'));
				continue;
			}

			if (isJavaLetter(ch))
				return extractAfterJavaLetter(inStrm);

			if (Character.isDigit(ch))
				return extractAfterDigit(inStrm);

			
			return extractSymbol(inStrm);
		}

		return null;
	}
}
