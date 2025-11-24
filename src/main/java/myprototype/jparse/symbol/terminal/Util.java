package myprototype.jparse.symbol.terminal;

import java.util.function.Function;

public class Util {
	public static boolean isHexDecimalDigit(int ch) {
		return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
	}

	public static boolean isDecimalDigit(int ch) {
		return (ch >= '0' && ch <= '9');
	}

	public static boolean isOctalDigit(int ch) {
		return (ch >= '0' && ch <= '7');
	}

	public static long parseNumber(String src, int digit, Function<Integer, Integer> parseDigit) {
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

	public static int parseHexDecimalDigit(int ch) {
		if (ch >= '0' && ch <= '9')
			return ch - '0';
		else if (ch >= 'a' && ch <= 'f')
			return ch - 'a' + 10;
		else if (ch >= 'A' && ch <= 'F')
			return ch - 'A' + 10;

		return -1;
	}

	public static int parseDecimalDigit(int ch) {
		return ch - '0';
	}

	public static int parseOctalDigit(int ch) {
		return parseDecimalDigit(ch);
	}

	public static long parseHexDecimalDigits(String src) {
		return (long) parseNumber(src, 16, (Integer ch) -> {
			return parseHexDecimalDigit(ch);
		});
	}

	public static long parseDecimalDigits(String src) {
		return (long) parseNumber(src, 10, (Integer ch) -> {
			return parseDecimalDigit(ch);
		});
	}

	public static long parseOctalDigits(String src) {
		return (long) parseNumber(src, 8, (Integer ch) -> {
			return parseOctalDigit(ch);
		});
	}
}
