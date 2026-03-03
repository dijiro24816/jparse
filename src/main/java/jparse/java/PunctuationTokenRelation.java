package jparse.java;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import jparse.TextBuffer;
import jparse.Token;

public class PunctuationTokenRelation {
	public int symbol;
	public PunctuationTokenRelation[] relations;

	public PunctuationTokenRelation(int symbol, PunctuationTokenRelation... relations) {
		super();
		this.symbol = symbol;
		this.relations = relations;
	}

	private Token extractInternal(TextBuffer textBuffer, InputStream inStrm)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {
		return extractInternal(textBuffer, inStrm, 0);
	}

	private Token extractInternal(TextBuffer textBuffer, InputStream inStrm, int count)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();
		int ch = textBuffer.getCharacter(inStrm);
		if (ch != this.symbol) {
			textBuffer.getPreviousCharacter(inStrm);
			return null;
		}

		for (PunctuationTokenRelation relation : relations) {
			Token tok = relation.extractInternal(textBuffer, inStrm, count + 1);
			if (tok != null)
				return tok;
		}

		int begIndex = index - count;
		int endIndex = index + 1;
		int beg = textBuffer.getByteCount(begIndex);
		int end = textBuffer.getByteCount(endIndex);
		String s = textBuffer.extract(begIndex, endIndex);
		return new Token(s, beg, end);
	}

	public static PunctuationTokenRelation[] rootRelations = {
			// Separator Token
			new PunctuationTokenRelation('('),
			new PunctuationTokenRelation(')'),
			new PunctuationTokenRelation('{'),
			new PunctuationTokenRelation('}'),
			new PunctuationTokenRelation('['),
			new PunctuationTokenRelation(']'),
			new PunctuationTokenRelation(';'),
			new PunctuationTokenRelation(','),
			new PunctuationTokenRelation('.'),

			// Operator Token
			new PunctuationTokenRelation('=',
					new PunctuationTokenRelation('=')),
			new PunctuationTokenRelation('>',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('>',
							new PunctuationTokenRelation('='),
							new PunctuationTokenRelation('>'))),
			new PunctuationTokenRelation('<',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('<',
							new PunctuationTokenRelation('='))),
			new PunctuationTokenRelation('!',
					new PunctuationTokenRelation('=')),
			new PunctuationTokenRelation('~'),
			new PunctuationTokenRelation('?'),
			new PunctuationTokenRelation(':'),
			new PunctuationTokenRelation('&',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('&')),
			new PunctuationTokenRelation('|',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('|')),
			new PunctuationTokenRelation('+',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('+')),
			new PunctuationTokenRelation('-',
					new PunctuationTokenRelation('='),
					new PunctuationTokenRelation('-')),
			new PunctuationTokenRelation('*',
					new PunctuationTokenRelation('=')),
			new PunctuationTokenRelation('/',
					new PunctuationTokenRelation('=')),
			new PunctuationTokenRelation('^',
					new PunctuationTokenRelation('=')),
			new PunctuationTokenRelation('%',
					new PunctuationTokenRelation('='))
	};

	public static Token extract(TextBuffer textBuffer, InputStream inStrm)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();

		for (PunctuationTokenRelation relation : rootRelations) {
			Token symbol = relation.extractInternal(textBuffer, inStrm);
			if (symbol != null)
				return symbol;

			textBuffer.jump(inStrm, index);
		}

		return null;
	}
}
