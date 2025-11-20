package myprototype.jparse.syntax.symbol.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import myprototype.jparse.syntax.symbol.terminal.operator.AdditionAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.AdditionOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.AssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseAndAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseAndOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseNotOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseOrAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseOrOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseXorAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.BitwiseXorOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.ColonOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.ConditionalOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.DecrementOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.DivisionAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.DivisionOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.EqualOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.GreaterThanEqualOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.GreaterThanOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.IncrementOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LeftShiftAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LeftShiftOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LessThanEqualOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LessThanOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LogicalAndOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LogicalNotOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.LogicalOrOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.ModuloAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.ModuloOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.MultiplicationAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.MultiplicationOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.NotEqualOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.RightShiftAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.RightShiftOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.SubtractionAssignmentOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.SubtractionOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.operator.UnsignedRightShiftOperatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.CommaSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.CurlyBracketCloseSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.CurlyBracketOpenSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.PeriodSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.RoundBracketCloseSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.RoundBracketOpenSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.SemicolonSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.SquareBracketCloseSeparatorToken;
import myprototype.jparse.syntax.symbol.terminal.separator.SquareBracketOpenSeparatorToken;

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

	private Terminal extractInternal(TextBuffer textBuffer, InputStream inStrm)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {
		return extractInternal(textBuffer, inStrm, 0);
	}

	private Terminal extractInternal(TextBuffer textBuffer, InputStream inStrm, int count)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();
		int ch = textBuffer.getCharacter(inStrm);
		if (ch != this.symbol) {
			textBuffer.getPreviousCharacter(inStrm);
			return null;
		}

		for (PunctuationTokenRelation relation : relations) {
			Terminal tok = relation.extractInternal(textBuffer, inStrm, count + 1);
			if (tok != null)
				return tok;
		}


		int begIndex = index - count;
		int endIndex = index + 1;
		int beg = textBuffer.getByteCount(begIndex);
		int end = textBuffer.getByteCount(endIndex);
		textBuffer.erase(begIndex, endIndex);
		return klass.getConstructor(int.class, int.class).newInstance(beg, end);
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

	public static Terminal extract(TextBuffer textBuffer, InputStream inStrm)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();

		for (PunctuationTokenRelation relation : rootRelations) {
			Terminal tok = relation.extractInternal(textBuffer, inStrm);
			if (tok != null)
				return tok;
			
			textBuffer.jump(inStrm, index);
		}

		return null;
	}
}
