package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;
import java.util.HexFormat;
import java.util.function.Function;

public class Tokenizer {
	public int lineNumber;
	public int columnNumber;

	public TextBuffer textBuffer = new TextBuffer();

	public Token extractAfterJavaLetter(InputStream inStrm) throws IOException {
		int ch, length = 0;
		while ((ch = textBuffer.read(inStrm)) >= 0 && (isJavaLetter(ch) || Character.isDigit(ch)))
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

	public boolean isHexDecimalDigit(int ch) {
		return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
	}

	public boolean isDecimalDigit(int ch) {
		return (ch >= '0' && ch <= '9');
	}

	public boolean isOctalDigit(int ch) {
		return (ch >= '0' && ch <= '7');
	}

	public long parseNumber(String src, int digit, Function<Integer, Integer> parseDigit) {
		long value = 0;

		int m = 1;
		int i = src.length();
		while ((i--) > 0) {
			int num = parseDigit.apply((int) src.charAt(i));
			value += num * m;
			m *= digit;
		}

		return value;
	}

	public int parseHexDecimalDigit(int ch) {
		if (ch >= '0' && ch <= '9')
			return ch - '0';
		else if (ch >= 'a' && ch <= 'f')
			return ch - 'a';
		else if (ch >= 'A' && ch <= 'F')
			return ch - 'A';

		return -1;
	}

	public int parseDecimalDigit(int ch) {
		return ch - '0';
	}

	public int parseOctalDigit(int ch) {
		return parseDecimalDigit(ch);
	}

	public long parseHexDecimalDigits(String src) {
		return (long) parseNumber(src, 16, (Integer ch) -> {
			return parseHexDecimalDigit(ch);
		});
	}

	public long parseDecimalDigits(String src) {
		return (long) parseNumber(src, 10, (Integer ch) -> {
			return parseDecimalDigit(ch);
		});
	}

	public long parseOctalDigits(String src) {
		return (long) parseNumber(src, 8, (Integer ch) -> {
			return parseOctalDigit(ch);
		});
	}

	public int getLength(InputStream inStrm, Function<Integer, Boolean> cmp) throws IOException {
		int length = 0;
		int ch;
		while ((ch = textBuffer.read(inStrm)) >= 0 && cmp.apply(ch))
			length++;

		return length;
	}

	public long extractOctalDigits(InputStream inStrm) throws IOException {
		int start = textBuffer.getIndex();
		int end = start + getLength(inStrm, (Integer ch) -> {
			return isOctalDigit(ch);
		});

		String src = textBuffer.substring(start, end);
		return parseOctalDigits(src);
	}
	
	public long captureHexdecimalDigits(InputStream inStrm) throws IOException {
		int start = textBuffer.getIndex();
		int end = start + getLength(inStrm, (Integer ch) -> {
			return isHexDecimalDigit(ch);
		});
		
		String src = textBuffer.substring(start, end);
		return parseHexDecimalDigits(src);
	}

	public Token extractAfterBinaryExponent(InputStream inStrm, String left) throws IOException {
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
		while ((ch = textBuffer.read(inStrm)) >= 0 && isHexDecimalDigit(ch))
			length++;

		String right = textBuffer.extract(length);

		value = parseHexDecimalDigits(left);
		exponent = parseDecimalDigits(right);

		value = Math.pow(value, minus ? -exponent : exponent);

		textBuffer.erase(length);
		int end = textBuffer.getFirstByteCount();

		return new FloatingPointLiteralToken(beg, end, value);
	}

	public Token extractAfterDigit(InputStream inStrm) throws IOException {
		int ch = textBuffer.peek(inStrm);
		int length = 0;
		int beg = textBuffer.getFirstByteCount();
		int end;
		String s;
		long value = 0;

		boolean isFloatingPoint = false;
		double floatingPointValue;

		// Decimal Integer Literal or Decimal Floating Point Literal
		if (ch != '0' || ch == '.' || textBuffer.peek(inStrm, 1) == '.') {
			while ((ch = textBuffer.read(inStrm)) >= 0) {
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

			while ((ch = textBuffer.read(inStrm)) >= 0) {
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
			while ((ch = textBuffer.read(inStrm)) >= 0) {
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

	public long captureOctalEscapeSequence(InputStream inStrm) throws IOException {

		return 0;
	}

	public int extractAfterBackslashCharacter(InputStream inStrm) throws IOException {
		int beg = textBuffer.getFirstByteCount();
		int ch = textBuffer.read(inStrm, 1); // next character

		switch (ch) {
		case 'b':
			return '\b';

		case 't':
			return '\t';

		case 'n':
			return '\n';

		case 'f':
			return '\f';

		case 'r':
			return '\r';

		case '"':
			return '"';

		case '\'':
			return '\'';

		case '\\':
			return '\\';

		case 'u':
			return 0;
		}

		if (isOctalDigit(ch))
			return (int)captureOctalEscapeSequence(inStrm);

		
		// Should we throw exception?
		
		return 0;
	}

	public Token extractAfterDoubleQuote(InputStream inStrm) throws IOException {
		return null;
	}
	
	public Token extractAfterSingleQuote(InputStream inStrm) throws IOException {
		int beg = textBuffer.getFirstByteCount();
		
		int index = textBuffer.setIndex(inStrm, textBuffer.getIndex() + 1); // go next character
		if (index < 0)
			return null;
		
		int ch;
		while ((ch = textBuffer.read(inStrm)) >= 0) {
			if (ch == '\\') {
			}
		}
		
		
		
//		switch ()
		
		
		
		
//		if ()
		
		
		
		return null;
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

			if (Character.isDigit(ch) || ch == '.') // Digit or FloatingPoint
				return extractAfterDigit(inStrm);

			String s = "";
			if (ch == '"') {
				return extractAfterDoubleQuote(inStrm);
			} else if (ch == '\'') {
				return extractAfterSingleQuote(inStrm);
			}

			return extractSymbol(inStrm);
		}

		return null;
	}
}
