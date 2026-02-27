package jparse.symbol.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import jparse.Token;
import jparse.symbol.terminal.operator.AdditionAssignmentOperatorToken;
import jparse.symbol.terminal.operator.AdditionOperatorToken;
import jparse.symbol.terminal.operator.AssignmentOperatorToken;
import jparse.symbol.terminal.operator.BitwiseAndAssignmentOperatorToken;
import jparse.symbol.terminal.operator.BitwiseAndOperatorToken;
import jparse.symbol.terminal.operator.BitwiseNotOperatorToken;
import jparse.symbol.terminal.operator.BitwiseOrAssignmentOperatorToken;
import jparse.symbol.terminal.operator.BitwiseOrOperatorToken;
import jparse.symbol.terminal.operator.BitwiseXorAssignmentOperatorToken;
import jparse.symbol.terminal.operator.BitwiseXorOperatorToken;
import jparse.symbol.terminal.operator.ColonOperatorToken;
import jparse.symbol.terminal.operator.ConditionalOperatorToken;
import jparse.symbol.terminal.operator.DecrementOperatorToken;
import jparse.symbol.terminal.operator.DivisionAssignmentOperatorToken;
import jparse.symbol.terminal.operator.DivisionOperatorToken;
import jparse.symbol.terminal.operator.EqualOperatorToken;
import jparse.symbol.terminal.operator.GreaterThanEqualOperatorToken;
import jparse.symbol.terminal.operator.GreaterThanOperatorToken;
import jparse.symbol.terminal.operator.IncrementOperatorToken;
import jparse.symbol.terminal.operator.LeftShiftAssignmentOperatorToken;
import jparse.symbol.terminal.operator.LeftShiftOperatorToken;
import jparse.symbol.terminal.operator.LessThanEqualOperatorToken;
import jparse.symbol.terminal.operator.LessThanOperatorToken;
import jparse.symbol.terminal.operator.LogicalAndOperatorToken;
import jparse.symbol.terminal.operator.LogicalNotOperatorToken;
import jparse.symbol.terminal.operator.LogicalOrOperatorToken;
import jparse.symbol.terminal.operator.ModuloAssignmentOperatorToken;
import jparse.symbol.terminal.operator.ModuloOperatorToken;
import jparse.symbol.terminal.operator.MultiplicationAssignmentOperatorToken;
import jparse.symbol.terminal.operator.MultiplicationOperatorToken;
import jparse.symbol.terminal.operator.NotEqualOperatorToken;
import jparse.symbol.terminal.operator.RightShiftAssignmentOperatorToken;
import jparse.symbol.terminal.operator.RightShiftOperatorToken;
import jparse.symbol.terminal.operator.SubtractionAssignmentOperatorToken;
import jparse.symbol.terminal.operator.SubtractionOperatorToken;
import jparse.symbol.terminal.operator.UnsignedRightShiftOperatorToken;
import jparse.symbol.terminal.separator.CommaSeparatorToken;
import jparse.symbol.terminal.separator.CurlyBracketCloseSeparatorToken;
import jparse.symbol.terminal.separator.CurlyBracketOpenSeparatorToken;
import jparse.symbol.terminal.separator.PeriodSeparatorToken;
import jparse.symbol.terminal.separator.RoundBracketCloseSeparatorToken;
import jparse.symbol.terminal.separator.RoundBracketOpenSeparatorToken;
import jparse.symbol.terminal.separator.SemicolonSeparatorToken;
import jparse.symbol.terminal.separator.SquareBracketCloseSeparatorToken;
import jparse.symbol.terminal.separator.SquareBracketOpenSeparatorToken;

public class PunctuationTokenRelation {
	public int symbol;
	public Class<? extends Terminal> klass;

	public PunctuationTokenRelation[] relations;

	public PunctuationTokenRelation(int symbol, Class<? extends Terminal> klass, PunctuationTokenRelation... relations) {
		super();
		this.symbol = symbol;
		this.klass = klass;
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
			new PunctuationTokenRelation('(', RoundBracketOpenSeparatorToken.class),
			new PunctuationTokenRelation(')', RoundBracketCloseSeparatorToken.class),
			new PunctuationTokenRelation('{', CurlyBracketOpenSeparatorToken.class),
			new PunctuationTokenRelation('}', CurlyBracketCloseSeparatorToken.class),
			new PunctuationTokenRelation('[', SquareBracketOpenSeparatorToken.class),
			new PunctuationTokenRelation(']', SquareBracketCloseSeparatorToken.class),
			new PunctuationTokenRelation(';', SemicolonSeparatorToken.class),
			new PunctuationTokenRelation(',', CommaSeparatorToken.class),
			new PunctuationTokenRelation('.', PeriodSeparatorToken.class),

			// Operator Token
			new PunctuationTokenRelation('=', AssignmentOperatorToken.class,
					new PunctuationTokenRelation('=', EqualOperatorToken.class)),
			new PunctuationTokenRelation('>', GreaterThanOperatorToken.class,
					new PunctuationTokenRelation('=', GreaterThanEqualOperatorToken.class),
					new PunctuationTokenRelation('>', RightShiftOperatorToken.class,
							new PunctuationTokenRelation('=', RightShiftAssignmentOperatorToken.class),
							new PunctuationTokenRelation('>', UnsignedRightShiftOperatorToken.class))),
			new PunctuationTokenRelation('<', LessThanOperatorToken.class,
					new PunctuationTokenRelation('=', LessThanEqualOperatorToken.class),
					new PunctuationTokenRelation('<', LeftShiftOperatorToken.class,
							new PunctuationTokenRelation('=', LeftShiftAssignmentOperatorToken.class))),
			new PunctuationTokenRelation('!', LogicalNotOperatorToken.class,
					new PunctuationTokenRelation('=', NotEqualOperatorToken.class)),
			new PunctuationTokenRelation('~', BitwiseNotOperatorToken.class),
			new PunctuationTokenRelation('?', ConditionalOperatorToken.class),
			new PunctuationTokenRelation(':', ColonOperatorToken.class),
			new PunctuationTokenRelation('&', BitwiseAndOperatorToken.class,
					new PunctuationTokenRelation('=', BitwiseAndAssignmentOperatorToken.class),
					new PunctuationTokenRelation('&', LogicalAndOperatorToken.class)),
			new PunctuationTokenRelation('|', BitwiseOrOperatorToken.class,
					new PunctuationTokenRelation('=', BitwiseOrAssignmentOperatorToken.class),
					new PunctuationTokenRelation('|', LogicalOrOperatorToken.class)),
			new PunctuationTokenRelation('+', AdditionOperatorToken.class,
					new PunctuationTokenRelation('=', AdditionAssignmentOperatorToken.class),
					new PunctuationTokenRelation('+', IncrementOperatorToken.class)),
			new PunctuationTokenRelation('-', SubtractionOperatorToken.class,
					new PunctuationTokenRelation('=', SubtractionAssignmentOperatorToken.class),
					new PunctuationTokenRelation('-', DecrementOperatorToken.class)),
			new PunctuationTokenRelation('*', MultiplicationOperatorToken.class,
					new PunctuationTokenRelation('=', MultiplicationAssignmentOperatorToken.class)),
			new PunctuationTokenRelation('/', DivisionOperatorToken.class,
					new PunctuationTokenRelation('=', DivisionAssignmentOperatorToken.class)),
			new PunctuationTokenRelation('^', BitwiseXorOperatorToken.class,
					new PunctuationTokenRelation('=', BitwiseXorAssignmentOperatorToken.class)),
			new PunctuationTokenRelation('%', ModuloOperatorToken.class,
					new PunctuationTokenRelation('=', ModuloAssignmentOperatorToken.class))
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
