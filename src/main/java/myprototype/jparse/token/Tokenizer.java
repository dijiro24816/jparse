package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;

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
		public int peek(InputStream inStrm, int count) throws IOException {
			int index = this.index;
			for (int i = 0; i < count - 2; i++)
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

		// Extract from stringBuilder, but keep stringBuilder's character after the specified length
		public String extract(int length) {
			String string = stringBuilder.substring(0, length);
			erase(length);
			return string;
		}

		public int length() {
			return stringBuilder.length();
		}
	}

	public TokenBuffer textBuffer = new TokenBuffer();

	//	public int peek() throws IOException {
	//		return textBuffer.peek(inputStream);
	//	}
	//
	//	public int peek(int count) throws IOException {
	//		return textBuffer.peek(inputStream, count);
	//	}
	//
	//	public int read() throws IOException {
	//		return textBuffer.read(inputStream);
	//	}
	//
	//	public void erase(int length) {
	//		textBuffer.erase(length);
	//	}
	//
	//	public int skip() throws IOException {
	//		return textBuffer.skip(inputStream);
	//	}

	public Token extractAfterAlphabet(InputStream inStrm) throws IOException {
		int ch, length = 1;
		while ((ch = textBuffer.read(inStrm)) != -1 && (Character.isAlphabetic(ch) || Character.isDigit(ch)))
			length++;

		int beg = textBuffer.getFirstByteCount();
		String s = textBuffer.extract(length);
		int end = textBuffer.getFirstByteCount();

		System.out.println(s);
		return new IdentifierToken(beg, end, s);
	}

	public Token extractAfterDigit() {
		return new LiteralToken(0, 0);
	}

	public Token tokenize(InputStream inStrm) throws IOException {
		int ch;
		while ((ch = textBuffer.peek(inStrm)) != -1) {

			// Skip whitespace
			if (Character.isWhitespace(ch)) {
				textBuffer.erase(1);

				while ((ch = textBuffer.skip(inStrm)) != -1) {
					if (!Character.isWhitespace(ch)) {
						textBuffer.unskip(inStrm, (char) ch);
						break;
					}
				}
			}

			// Skip comment
			if (ch == '/' && textBuffer.peek(inStrm, 2) == '*') {
				textBuffer.erase(2); // Erase stored character '/' and '*'

				int ch1, ch2 = 0;
				do {
					ch1 = ch2;

				} while ((ch2 = textBuffer.skip(inStrm)) != -1 && (ch1 != '*' || ch2 != '/'));
				continue;
			}

			//			System.out.println(textBuffer.count);

			if (Character.isAlphabetic(ch))
				return extractAfterAlphabet(inStrm);

			//			if (Character.isDigit(ch))
			//				return extractAfterDigit();

			break;
		}

		return new Token(0, 0);
	}
}
