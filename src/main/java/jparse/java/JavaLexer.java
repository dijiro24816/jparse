package jparse.java;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HexFormat;

import jparse.InvalidTokenException;
import jparse.Lexer;
import jparse.TextBuffer;
import jparse.Token;
import jparse.Util;

public class JavaLexer implements Lexer {
	private TextBuffer textBuffer;
	private String endSymbol;
	
	public JavaLexer(TextBuffer textBuffer, String endSymbol) {
		this.textBuffer = textBuffer;
		this.endSymbol = endSymbol;
	}
	
	public JavaLexer() {
		this("$");
	}
	
	public JavaLexer(String endSymbol) {
		this(new TextBuffer(), endSymbol);
	}

	public Token extractAfterJavaLetter(InputStream inStrm) throws IOException, InvalidTokenException {
		int ch, length = 0;
		while ((ch = textBuffer.getCharacter(inStrm)) >= 0 && (isJavaLetter(ch) || Character.isDigit(ch)))
			length++;

		int beg = textBuffer.getFirstByteCount();
		String s = textBuffer.extract(length);
		int end = textBuffer.getFirstByteCount();

		Token symbol;

		symbol = captureKeywordToken(beg, end, s);
		if (symbol != null)
			return symbol;

		symbol = captureNullLiteralToken(beg, end, s);
		if (symbol != null)
			return symbol;

		symbol = captureBooleanLiteralToken(beg, end, s);
		if (symbol != null)
			return symbol;

		return new Token("Identifier", beg, end, s);
	}
	
	public static Token captureNullLiteralToken(int beg, int end, String s) {
		return s.equals("null") ? new Token("NullLiteral", beg, end) : null;
	}
	
	
	public static Token captureKeywordToken(int beg, int end, String s) {
		switch (s) {

 		case "abstract":
			return new Token("abstract", beg, end);
		case "assert":
			return new Token("assert", beg, end);
		case "boolean":
			return new Token("boolean", beg, end);
		case "break":
			return new Token("break", beg, end);
		case "byte":
			return new Token("byte", beg, end);
		case "case":
			return new Token("case", beg, end);
		case "catch":
			return new Token("catch", beg, end);
		case "char":
			return new Token("char", beg, end);
		case "class":
			return new Token("class", beg, end);
		case "const":
			return new Token("const", beg, end);
		case "continue":
			return new Token("continue", beg, end);
		case "default":
			return new Token("default", beg, end);
		case "do":
			return new Token("do", beg, end);
		case "double":
			return new Token("double", beg, end);
		case "else":
			return new Token("else", beg, end);
		case "enum":
			return new Token("enum", beg, end);
		case "extends":
			return new Token("extends", beg, end);
		case "final":
			return new Token("final", beg, end);
		case "finally":
			return new Token("finally", beg, end);
		case "float":
			return new Token("float", beg, end);
		case "for":
			return new Token("for", beg, end);
		case "if":
			return new Token("if", beg, end);
		case "goto":
			return new Token("goto", beg, end);
		case "implements":
			return new Token("implements", beg, end);
		case "import":
			return new Token("import", beg, end);
		case "instanceof":
			return new Token("instanceof", beg, end);
		case "int":
			return new Token("int", beg, end);
		case "interface":
			return new Token("interface", beg, end);
		case "long":
			return new Token("long", beg, end);
		case "native":
			return new Token("native", beg, end);
		case "new":
			return new Token("new", beg, end);
		case "package":
			return new Token("package", beg, end);
		case "private":
			return new Token("private", beg, end);
		case "protected":
			return new Token("protected", beg, end);
		case "public":
			return new Token("public", beg, end);
		case "return":
			return new Token("return", beg, end);
		case "short":
			return new Token("short", beg, end);
		case "static":
			return new Token("static", beg, end);
		case "strictfp":
			return new Token("strictfp", beg, end);
		case "super":
			return new Token("super", beg, end);
		case "switch":
			return new Token("switch", beg, end);
		case "synchronized":
			return new Token("synchronized", beg, end);
		case "this":
			return new Token("this", beg, end);
		case "throw":
			return new Token("throw", beg, end);
		case "throws":
			return new Token("throws", beg, end);
		case "transient":
			return new Token("transient", beg, end);
		case "try":
			return new Token("try", beg, end);
		case "void":
			return new Token("void", beg, end);
		case "volatile":
			return new Token("volatile", beg, end);
		case "while":
			return new Token("while", beg, end);
		}
		return null;
	}
	
	public static Token captureBooleanLiteralToken(int beg, int end, String s) {
		boolean value;
		
		switch (s) {
		case "true":
			value = true;
			break;
		case "false":
			value = false;
			break;
		default:
			return null;
		}
		
		return new Token("BooleanLiteral", beg, end, value);
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

	public Token extractAfterBinaryExponent(InputStream inStrm, String left)
			throws IOException, InvalidTokenException {
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

		return new Token("FlotingPointLiteral", beg, end, value);
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
				return new Token("FloatingPointLiteral", beg, end, floatingPointValue);
			}
			value = Long.parseLong(s);

			return new Token("IntegerLiteral", beg, end, value);
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

			return new Token("IntegerLiteral", beg, end, value);

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

			return new Token("IntegerLiteral", beg, end, value);
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

			return new Token("IntegerLiteral", beg, end, value);
		}

		}
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
			sb.append((char) ch);
		}

		textBuffer.erase(offset, offset + 2); // erase '"' + '"'

		return new Token("StringLiteral", beg, textBuffer.getByteCount(offset), sb.toString());
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
		return new Token("CharacterLiteral", ch, beg, textBuffer.getFirstByteCount());
	}

	public Token extractPunctuation(InputStream inStrm) throws IOException {
		try {
			return PunctuationTokenRelation.extract(textBuffer, inStrm);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | IOException e) {
			e.printStackTrace();
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

			if (Character.isDigit(ch) || (ch == '.' && Character.isDigit(textBuffer.peek(inStrm, 1)))) // Digit or
																										// FloatingPoint
				return extractAfterDigit(inStrm);

			String s = "";
			if (ch == '"') {
				return extractStringLiteralToken(inStrm);
			} else if (ch == '\'') {
				return extractCharacterLiteralToken(inStrm);
			}

			return extractPunctuation(inStrm);
		}

		int offset = this.textBuffer.getFirstByteCount();
		return new Token(this.endSymbol, offset, offset);
	}
}
