package jparse.java;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HexFormat;

import jparse.InvalidTokenException;
import jparse.Lexer;
import jparse.Scratchpad;
import jparse.Token;
import jparse.Util;

public class JavaLexer implements Lexer {
	private Scratchpad scratchpad;
	private String endSymbol;
	
	public JavaLexer(Scratchpad scratchpad, String endSymbol) {
		this.scratchpad = scratchpad;
		this.endSymbol = endSymbol;
	}
	
	public JavaLexer() {
		this("$");
	}
	
	public JavaLexer(String endSymbol) {
		this(new Scratchpad() {
			public int pullCharacter(InputStream inStrm) throws IOException {
				int begIndex = length();
				
				int ch = getCharacter(inStrm);
				
				if (ch != '\\')
					return ch;
				
				if (pullByteCharacter(inStrm) != 'u')
					return ch;
				
				for (int i = 0; i < 4; i++)
					pullByteCharacter(inStrm);
				
				ch = (int) Util.parseHexDecimalDigits(copy(begIndex + 2, begIndex + 6));
				replaceAsCharacter(begIndex, begIndex + 6, ch);
				
				return ch;
			}

			public void replaceAsCharacter(int begIndex, int endIndex, int ch) {
				erase(begIndex, endIndex, 1);
				buffer.insert(begIndex, (char)ch);
			}
		}, endSymbol);
	}

	public Token extractAfterJavaLetter(InputStream inStrm) throws IOException, InvalidTokenException {
		int ch, length = 0;
		while ((ch = scratchpad.getCharacter(inStrm)) >= 0 && (isJavaLetter(ch) || Character.isDigit(ch)))
			length++;

		int beg = scratchpad.getFirstByteCount();
		String s = scratchpad.extract(0, length);
		int end = scratchpad.getFirstByteCount();

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
		int start = scratchpad.getIndex();
		int end = start + scratchpad.countLength(inStrm, (Integer ch) -> {
			return Util.isOctalDigit(ch);
		});

		return Util.parseOctalDigits(scratchpad.extract(start, end));
	}

	public long extractHexdecimalDigits(InputStream inStrm) throws IOException, InvalidTokenException {
		int start = scratchpad.getIndex();
		int end = start + scratchpad.countLength(inStrm, (Integer ch) -> {
			return Util.isHexDecimalDigit(ch);
		});

		return Util.parseHexDecimalDigits(scratchpad.extract(start, end));
	}

	public Token extractAfterBinaryExponent(InputStream inStrm, String left)
			throws IOException, InvalidTokenException {
		int beg = scratchpad.getFirstByteCount() - left.length();

		double value = 0;
		long exponent = 0;

		int ch = scratchpad.peek(inStrm, 1);
		boolean minus = false;
		switch (ch) {
		case '-':
			minus = true;
		case '+':
			scratchpad.erase(0, 2);
			break;
		default:
			scratchpad.erase(0, 1);
		}

		int length = 0;
		while ((ch = scratchpad.getCharacter(inStrm)) >= 0 && Util.isHexDecimalDigit(ch))
			length++;

		String right = scratchpad.extract(0, length);

		value = Util.parseHexDecimalDigits(left);
		exponent = Util.parseDecimalDigits(right);

		value = Math.pow(value, minus ? -exponent : exponent);

		scratchpad.erase(0, length);
		int end = scratchpad.getFirstByteCount();

		return new Token("FlotingPointLiteral", beg, end, value);
	}

	public Token extractAfterDigit(InputStream inStrm) throws IOException, InvalidTokenException {
		int ch = scratchpad.peek(inStrm);
		int length = 0;

		int offset = scratchpad.getIndex();

		int beg = scratchpad.getByteCount(offset);
		int end;
		String s;
		long value = 0;

		boolean isFloatingPoint = false;
		double floatingPointValue;

		// Decimal Integer Literal or Decimal Floating Point Literal
		if (ch != '0' || ch == '.' || scratchpad.peek(inStrm, 1) == '.') {
			while ((ch = scratchpad.getCharacter(inStrm)) >= 0) {
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

			s = scratchpad.extract(0, length);
			end = scratchpad.getFirstByteCount();

			if (isFloatingPoint) {
				floatingPointValue = Double.parseDouble(s);
				return new Token("FloatingPointLiteral", beg, end, floatingPointValue);
			}
			value = Long.parseLong(s);

			return new Token("IntegerLiteral", beg, end, value);
		}

		switch (scratchpad.peek(inStrm, 1)) {
		case 'x':
		case 'X': // Hexdecimal Integer Literal
			scratchpad.erase(0, 2);

			while ((ch = scratchpad.getCharacter(inStrm)) >= 0) {
				int num;

				if (ch == 'p')
					return extractAfterBinaryExponent(inStrm, scratchpad.extract(0, length));

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

			s = scratchpad.extract(0, length);
			value = HexFormat.fromHexDigitsToLong(s);
			end = scratchpad.getFirstByteCount();

			return new Token("IntegerLiteral", beg, end, value);

		case 'b':
		case 'B': {
			// Binary Integer Literal
			scratchpad.erase(0, 2);
			while ((ch = scratchpad.getCharacter(inStrm)) >= 0) {
				if (ch < '0' || ch > '1')
					break;

				length++;
			}

			s = scratchpad.extract(0, length);
			end = scratchpad.getFirstByteCount();

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
			scratchpad.erase(0, 1);

			while ((ch = scratchpad.getCharacter(inStrm)) >= 0) {
				if (ch < '0' || ch > '7')
					break;

				length++;
			}

			s = scratchpad.extract(0, length);
			end = scratchpad.getFirstByteCount();

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
		int beg = scratchpad.getFirstByteCount();
		int offset = scratchpad.getIndex();

		scratchpad.jump(inStrm, offset + 1);
		int ch = scratchpad.getCharacter(inStrm); // go after '\' character

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

			scratchpad.getPreviousCharacter(inStrm);
			ch = (int) extractOctalDigits(inStrm);
			scratchpad.erase(offset, offset + 1); // erase '\\'
			return ch;
		}

		scratchpad.erase(offset, offset + 2); // erase '\\' + '[btnfr"\'\\]'
		return ch;
	}

	public Token extractStringLiteralToken(InputStream inStrm) throws IOException, InvalidTokenException {
		int beg = scratchpad.getFirstByteCount();
		int offset = scratchpad.getIndex();

		StringBuilder sb = new StringBuilder();

		scratchpad.jump(inStrm, offset + 1); // jump after '"'
		int ch;
		while ((ch = scratchpad.peek(inStrm)) != '"') {
			if (ch < 0)
				throw new InvalidTokenException();
			ch = extractSingleCharacter(inStrm);
			sb.append((char) ch);
		}

		scratchpad.erase(offset, offset + 2); // erase '"' + '"'

		return new Token("StringLiteral", beg, scratchpad.getByteCount(offset), sb.toString());
	}

	public int extractSingleCharacter(InputStream inStrm) throws IOException, InvalidTokenException {
		int start = scratchpad.getIndex();

		int ch = scratchpad.peek(inStrm); // go after '\''
		if (ch < 0)
			throw new InvalidTokenException();

		if (ch == '\\') {
			ch = extractEscapeSequence(inStrm);
		} else {
			scratchpad.erase(start, start + 1); // erase single character
		}

		return ch;
	}

	public Token extractCharacterLiteralToken(InputStream inStrm) throws IOException, InvalidTokenException {
		int beg = scratchpad.getFirstByteCount();
		int offset = scratchpad.getIndex();

		scratchpad.jump(inStrm, offset + 1); // jump after '\''
		if (scratchpad.peek(inStrm) == '\'')
			throw new InvalidTokenException();

		int ch;
		try {
			ch = extractSingleCharacter(inStrm); // extract inside '\'' and '\''
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
			return null;
		}

		if (scratchpad.getCharacter(inStrm) != '\'')
			throw new InvalidTokenException();

		scratchpad.erase(offset, offset + 2); // erase '\'' + '\''
		return new Token("CharacterLiteral", ch, beg, scratchpad.getFirstByteCount());
	}

	public Token extractPunctuation(InputStream inStrm) throws IOException {
		try {
			return PunctuationTokenRelation.extract(scratchpad, inStrm);
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
		while ((ch = scratchpad.peek(inStrm)) >= 0) {
			if (ch != '\n' && (ch < ' ' || ch > 126)) // Invaild character
				break;

			// Skip whitespace
			if (Character.isWhitespace(ch)) {
				scratchpad.erase(0, 1);

				while ((ch = scratchpad.skip(inStrm)) >= 0) {
					if (!Character.isWhitespace(ch)) {
						scratchpad.unskip(inStrm, (char) ch);
						break;
					}
				}
				continue;
			}

			// Skip comment
			if (ch == '/' && scratchpad.peek(inStrm, 1) == '*') {
				scratchpad.erase(0, 2); // Erase stored character '/' and '*'

				int ch1, ch2 = 0;
				do {
					ch1 = ch2;

				} while ((ch2 = scratchpad.skip(inStrm)) >= 0 && (ch1 != '*' || ch2 != '/'));
				continue;
			}

			if (isJavaLetter(ch))
				return extractAfterJavaLetter(inStrm);

			if (Character.isDigit(ch) || (ch == '.' && Character.isDigit(scratchpad.peek(inStrm, 1)))) // Digit or
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

		int offset = this.scratchpad.getFirstByteCount();
		return new Token(this.endSymbol, offset, offset);
	}
}
