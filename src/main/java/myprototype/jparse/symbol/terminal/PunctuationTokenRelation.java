package myprototype.jparse.symbol.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import myprototype.jparse.Symbol;
import myprototype.jparse.symbol.terminal.operator.AdditionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.AdditionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.AssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseAndAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseAndOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseNotOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseOrAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseOrOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseXorAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.BitwiseXorOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ColonOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ConditionalOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DecrementOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DivisionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.DivisionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.EqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.GreaterThanEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.GreaterThanOperatorToken;
import myprototype.jparse.symbol.terminal.operator.IncrementOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LeftShiftAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LeftShiftOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LessThanEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LessThanOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalAndOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalNotOperatorToken;
import myprototype.jparse.symbol.terminal.operator.LogicalOrOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ModuloAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.ModuloOperatorToken;
import myprototype.jparse.symbol.terminal.operator.MultiplicationAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.MultiplicationOperatorToken;
import myprototype.jparse.symbol.terminal.operator.NotEqualOperatorToken;
import myprototype.jparse.symbol.terminal.operator.RightShiftAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.RightShiftOperatorToken;
import myprototype.jparse.symbol.terminal.operator.SubtractionAssignmentOperatorToken;
import myprototype.jparse.symbol.terminal.operator.SubtractionOperatorToken;
import myprototype.jparse.symbol.terminal.operator.UnsignedRightShiftOperatorToken;
import myprototype.jparse.symbol.terminal.separator.CommaSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.CurlyBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.CurlyBracketOpenSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.PeriodSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.RoundBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.RoundBracketOpenSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SemicolonSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SquareBracketCloseSeparatorToken;
import myprototype.jparse.symbol.terminal.separator.SquareBracketOpenSeparatorToken;

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

	private Symbol extractInternal(TextBuffer textBuffer, InputStream inStrm)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {
		return extractInternal(textBuffer, inStrm, 0);
	}

	private Symbol extractInternal(TextBuffer textBuffer, InputStream inStrm, int count)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();
		int ch = textBuffer.getCharacter(inStrm);
		if (ch != this.symbol) {
			textBuffer.getPreviousCharacter(inStrm);
			return null;
		}

		for (PunctuationTokenRelation relation : relations) {
			Symbol tok = relation.extractInternal(textBuffer, inStrm, count + 1);
			if (tok != null)
				return tok;
		}


		int begIndex = index - count;
		int endIndex = index + 1;
		int beg = textBuffer.getByteCount(begIndex);
		int end = textBuffer.getByteCount(endIndex);
		String s = textBuffer.extract(begIndex, endIndex);
		return new Symbol(s, beg, end);
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

	public static Symbol extract(TextBuffer textBuffer, InputStream inStrm)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();

		for (PunctuationTokenRelation relation : rootRelations) {
			Symbol symbol = relation.extractInternal(textBuffer, inStrm);
			if (symbol != null)
				return symbol;
			
			textBuffer.jump(inStrm, index);
		}

		return null;
	}
}
