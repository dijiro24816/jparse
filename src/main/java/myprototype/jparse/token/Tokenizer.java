package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;
import java.util.HexFormat;

public class Tokenizer {
	public int lineNumber;
	public int columnNumber;

	public TextBuffer textBuffer = new TextBuffer();

	public Token extractAfterJavaLetter(InputStream inStrm) throws IOException, InvalidTokenException {
		int ch, length = 0;
		while ((ch = textBuffer.getCharacter(inStrm)) >= 0 && (isJavaLetter(ch) || Character.isDigit(ch)))
			length++;

		int beg = textBuffer.getFirstByteCount();
		String s = textBuffer.extract(length);
		int end = textBuffer.getFirstByteCount();

		Token tok;

		tok = KeywordToken.capture(beg, end, s);
		if (tok != null)
			return tok;

		tok = NullLiteralToken.capture(beg, end, s);
		if (tok != null)
			return tok;

		tok = BooleanLiteralToken.capture(beg, end, s);
		if (tok != null)
			return tok;

		return new IdentifierToken(beg, end, s);
	}

	public long extractOctalDigits(InputStream inStrm) throws IOException, InvalidTokenException {
		int start = textBuffer.getIndex();
		int end = start + textBuffer.countLength(inStrm, (Integer ch) -> {
			return Util.isOctalDigit(ch);
		});

		return Util.parseOctalDigits(textBuffer.extract(start, end));
	}

	public long extractHexdecimalDigits(InputStream inStrm) throws IOException, InvalidTokenException {
		int start = textBuffer.getIndex();
		int end = start + textBuffer.countLength(inStrm, (Integer ch) -> {
			return Util.isHexDecimalDigit(ch);
		});

		return Util.parseHexDecimalDigits(textBuffer.extract(start, end));
	}

	public Token extractAfterBinaryExponent(InputStream inStrm, String left) throws IOException, InvalidTokenException {
		int beg = textBuffer.getFirstByteCount() - left.length();

		double value = 0;
		long exponent = 0;

		int ch = textBuffer.peek(inStrm, 1);
		boolean minus = false;
		switch (ch) {
		case '-':
			minus = true;
		case '+':
			textBuffer.erase(2);
			break;
		default:
			textBuffer.erase(1);
		}

		int length = 0;
		while ((ch = textBuffer.getCharacter(inStrm)) >= 0 && Util.isHexDecimalDigit(ch))
			length++;

		String right = textBuffer.extract(length);

		value = Util.parseHexDecimalDigits(left);
		exponent = Util.parseDecimalDigits(right);

		value = Math.pow(value, minus ? -exponent : exponent);

		textBuffer.erase(length);
		int end = textBuffer.getFirstByteCount();

		return new FloatingPointLiteralToken(beg, end, value);
	}

	public Token extractAfterDigit(InputStream inStrm) throws IOException, InvalidTokenException {
		int ch = textBuffer.peek(inStrm);
		int length = 0;
		
		int offset = textBuffer.getIndex();
		
		int beg = textBuffer.getByteCount(offset);
		int end;
		String s;
		long value = 0;

		boolean isFloatingPoint = false;
		double floatingPointValue;

		// Decimal Integer Literal or Decimal Floating Point Literal
		if (ch != '0' || ch == '.' || textBuffer.peek(inStrm, 1) == '.') {
			while ((ch = textBuffer.getCharacter(inStrm)) >= 0) {
				if (ch == '.' && !isFloatingPoint) { // Don't allow this process second time
					isFloatingPoint = true;
					length++;
					continue;
				}

				int num = ch - '0';
				if (num < 0 || num > 9)
					break;

				length++;
			}

			s = textBuffer.extract(length);
			end = textBuffer.getFirstByteCount();

			if (isFloatingPoint) {
				floatingPointValue = Double.parseDouble(s);
				return new FloatingPointLiteralToken(beg, end, floatingPointValue);
			}
			value = Long.parseLong(s);

			return new IntegerLiteralToken(beg, end, value);
		}

		switch (textBuffer.peek(inStrm, 1)) {
		case 'x':
		case 'X': // Hexdecimal Integer Literal
			textBuffer.erase(2);

			while ((ch = textBuffer.getCharacter(inStrm)) >= 0) {
				int num;

				if (ch == 'p')
					return extractAfterBinaryExponent(inStrm, textBuffer.extract(length));

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
			value = HexFormat.fromHexDigitsToLong(s);
			end = textBuffer.getFirstByteCount();

			return new IntegerLiteralToken(beg, end, value);

		case 'b':
		case 'B': {
			// Binary Integer Literal
			textBuffer.erase(2);
			while ((ch = textBuffer.getCharacter(inStrm)) >= 0) {
				if (ch < '0' || ch > '1')
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
			// Octal Integer Literal
			textBuffer.erase(1);

			while ((ch = textBuffer.getCharacter(inStrm)) >= 0) {
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

	public int extractEscapeSequence(InputStream inStrm) throws IOException, InvalidTokenException {
		int beg = textBuffer.getFirstByteCount();
		int offset = textBuffer.getIndex();
		
		textBuffer.jump(inStrm, offset + 1);
		int ch = textBuffer.getCharacter(inStrm); // go after '\' character
		
		switch (ch) {
		case 'b':
			ch = '\b';
			break;

		case 't':
			ch = '\t';
			break;

		case 'n':
			ch = '\n';
			break;

		case 'f':
			ch = '\f';
			break;

		case 'r':
			ch = '\r';
			break;

		case '"':
			ch = '"';
			break;

		case '\'':
			ch = '\'';
			break;

		case '\\':
			ch = '\\';
			break;

		default:
			if (!Util.isOctalDigit(ch)) {
				// Should we throw exception?
				return 0;
			}
			
			textBuffer.getPreviousCharacter(inStrm);		
			ch = (int) extractOctalDigits(inStrm);
			textBuffer.erase(offset, offset + 1); // erase '\\'
			return ch;
		}
		
		textBuffer.erase(offset, offset + 2); // erase '\\' + '[btnfr"\'\\]'
		return ch;
	}

	public Token extractStringLiteralToken(InputStream inStrm) throws IOException, InvalidTokenException {
		int beg = textBuffer.getFirstByteCount();
		int offset = textBuffer.getIndex();
		
		StringBuilder sb = new StringBuilder();
		
		textBuffer.jump(inStrm, offset + 1); // jump after '"'
		int ch;
		while ((ch = textBuffer.peek(inStrm)) != '"') {
			if (ch < 0)
				throw new InvalidTokenException();
			ch = extractSingleCharacter(inStrm);
			sb.append((char)ch);
		}

		textBuffer.erase(offset, offset + 2); // erase '"' + '"'

		return new StringLiteralToken(beg, textBuffer.getByteCount(offset), sb.toString());
	}

	public int extractSingleCharacter(InputStream inStrm) throws IOException, InvalidTokenException {
		int start = textBuffer.getIndex();
		
		int ch = textBuffer.peek(inStrm); // go after '\''
		if (ch < 0)
			throw new InvalidTokenException();
		
		if (ch == '\\') {
			ch = extractEscapeSequence(inStrm);
		} else {
			textBuffer.erase(start, start + 1); // erase single character
		}

		return ch;
	}

	public Token extractCharacterLiteralToken(InputStream inStrm) throws IOException, InvalidTokenException {
		int beg = textBuffer.getFirstByteCount();
		int offset = textBuffer.getIndex();
		
		textBuffer.jump(inStrm, offset + 1); // jump after '\''
		if (textBuffer.peek(inStrm) == '\'')
			throw new InvalidTokenException();

		int ch;
		try {
			ch = extractSingleCharacter(inStrm); // extract inside '\'' and '\''
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		if (textBuffer.getCharacter(inStrm) != '\'')
			throw new InvalidTokenException();
		
		textBuffer.erase(offset, offset + 2); // erase '\'' + '\''
		return new CharacterLiteralToken(beg, textBuffer.getFirstByteCount(), ch);
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

		String peekS = textBuffer.getHeadString(4);
		switch (peekS.charAt(0)) {
		case '=':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);

			return eatOperatorToken(1);

		case '>':
			switch (peekS.charAt(1)) {
			case '=':
				return eatOperatorToken(2);
			case '>':
				switch (peekS.charAt(2)) {
				case '>':
					if (peekS.charAt(3) == '=')
						return eatOperatorToken(4);

					return eatOperatorToken(3);
				case '=':
					return eatOperatorToken(3);
				}
				return eatOperatorToken(2);
			}
			return eatOperatorToken(1);

		case '<':
			switch (peekS.charAt(1)) {
			case '=':
				return eatOperatorToken(2);

			case '<':
				if (peekS.charAt(2) == '=')
					return eatOperatorToken(3);
				return eatOperatorToken(2);
			}

			return eatOperatorToken(1);

		case '!':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);

			return eatOperatorToken(1);

		case '~':
			return eatOperatorToken(1);

		case '?':
			return eatOperatorToken(1);

		case ':':
			return eatOperatorToken(1);

		case '&':
			switch (peekS.charAt(1)) {
			case '&':
				return eatOperatorToken(2);

			case '=':
				return eatOperatorToken(2);
			}

			return eatOperatorToken(1);

		case '|':
			switch (peekS.charAt(1)) {
			case '|':
				return eatOperatorToken(2);

			case '=':
				return eatOperatorToken(2);
			}

			return eatOperatorToken(1);

		case '+':
			switch (peekS.charAt(1)) {
			case '+':
				return eatOperatorToken(2);

			case '=':
				return eatOperatorToken(2);
			}

			return eatOperatorToken(1);

		case '-':
			switch (peekS.charAt(1)) {
			case '-':
				return eatOperatorToken(2);

			case '=':
				return eatOperatorToken(2);
			}

			return eatOperatorToken(1);

		case '*':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);

			return eatOperatorToken(1);

		case '/':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);

			return eatOperatorToken(1);

		case '^':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);
			return eatOperatorToken(1);

		case '%':
			if (peekS.charAt(1) == '=')
				return eatOperatorToken(2);

			return eatOperatorToken(1);
		}

		return null;
	}

	public boolean isJavaLetter(int ch) {
		if (Character.isAlphabetic(ch) || ch == '_')
			return true;

		return false;
	}

	public Token tokenize(InputStream inStrm) throws IOException, InvalidTokenException {
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
				continue;
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

			if (Character.isDigit(ch) || ch == '.') // Digit or FloatingPoint
				return extractAfterDigit(inStrm);

			String s = "";
			if (ch == '"') {
				return extractStringLiteralToken(inStrm);
			} else if (ch == '\'') {
				return extractCharacterLiteralToken(inStrm);
			}

			return extractSymbol(inStrm);
		}

		return null;
	}
}
