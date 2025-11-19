package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import myprototype.jparse.token.operator.AdditionAssignmentOperatorToken;
import myprototype.jparse.token.operator.AdditionOperatorToken;
import myprototype.jparse.token.operator.AssignmentOperatorToken;
import myprototype.jparse.token.operator.BitwiseAndAssignmentOperatorToken;
import myprototype.jparse.token.operator.BitwiseAndOperatorToken;
import myprototype.jparse.token.operator.BitwiseNotOperatorToken;
import myprototype.jparse.token.operator.BitwiseOrAssignmentOperatorToken;
import myprototype.jparse.token.operator.BitwiseOrOperatorToken;
import myprototype.jparse.token.operator.BitwiseXorAssignmentOperatorToken;
import myprototype.jparse.token.operator.BitwiseXorOperatorToken;
import myprototype.jparse.token.operator.ColonOperatorToken;
import myprototype.jparse.token.operator.ConditionalOperatorToken;
import myprototype.jparse.token.operator.DecrementOperatorToken;
import myprototype.jparse.token.operator.DivisionAssignmentOperatorToken;
import myprototype.jparse.token.operator.DivisionOperatorToken;
import myprototype.jparse.token.operator.EqualOperatorToken;
import myprototype.jparse.token.operator.GreaterThanEqualOperatorToken;
import myprototype.jparse.token.operator.GreaterThanOperatorToken;
import myprototype.jparse.token.operator.IncrementOperatorToken;
import myprototype.jparse.token.operator.LeftShiftAssignmentOperatorToken;
import myprototype.jparse.token.operator.LeftShiftOperatorToken;
import myprototype.jparse.token.operator.LessThanEqualOperatorToken;
import myprototype.jparse.token.operator.LessThanOperatorToken;
import myprototype.jparse.token.operator.LogicalAndOperatorToken;
import myprototype.jparse.token.operator.LogicalNotOperatorToken;
import myprototype.jparse.token.operator.LogicalOrOperatorToken;
import myprototype.jparse.token.operator.ModuloAssignmentOperatorToken;
import myprototype.jparse.token.operator.ModuloOperatorToken;
import myprototype.jparse.token.operator.MultiplicationAssignmentOperatorToken;
import myprototype.jparse.token.operator.MultiplicationOperatorToken;
import myprototype.jparse.token.operator.NotEqualOperatorToken;
import myprototype.jparse.token.operator.RightShiftAssignmentOperatorToken;
import myprototype.jparse.token.operator.RightShiftOperatorToken;
import myprototype.jparse.token.operator.SubtractionAssignmentOperatorToken;
import myprototype.jparse.token.operator.SubtractionOperatorToken;
import myprototype.jparse.token.operator.UnsignedRightShiftOperatorToken;
import myprototype.jparse.token.separator.CommaSeparatorToken;
import myprototype.jparse.token.separator.CurlyBracketCloseSeparatorToken;
import myprototype.jparse.token.separator.CurlyBracketOpenSeparatorToken;
import myprototype.jparse.token.separator.PeriodSeparatorToken;
import myprototype.jparse.token.separator.RoundBracketCloseSeparatorToken;
import myprototype.jparse.token.separator.RoundBracketOpenSeparatorToken;
import myprototype.jparse.token.separator.SemicolonSeparatorToken;
import myprototype.jparse.token.separator.SquareBracketCloseSeparatorToken;
import myprototype.jparse.token.separator.SquareBracketOpenSeparatorToken;

public class SymbolTokenRelation {
	public int symbol;
	public Class<? extends Token> klass;

	public SymbolTokenRelation[] relations;

	public SymbolTokenRelation(int symbol, Class<? extends Token> klass, SymbolTokenRelation... relations) {
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

		for (SymbolTokenRelation relation : relations) {
			Token tok = relation.extractInternal(textBuffer, inStrm, count + 1);
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

	public static SymbolTokenRelation[] rootRelations = {
			// Separator Token
			new SymbolTokenRelation('(', RoundBracketOpenSeparatorToken.class),
			new SymbolTokenRelation(')', RoundBracketCloseSeparatorToken.class),
			new SymbolTokenRelation('{', CurlyBracketOpenSeparatorToken.class),
			new SymbolTokenRelation('}', CurlyBracketCloseSeparatorToken.class),
			new SymbolTokenRelation('[', SquareBracketOpenSeparatorToken.class),
			new SymbolTokenRelation(']', SquareBracketCloseSeparatorToken.class),
			new SymbolTokenRelation(';', SemicolonSeparatorToken.class),
			new SymbolTokenRelation(',', CommaSeparatorToken.class),
			new SymbolTokenRelation('.', PeriodSeparatorToken.class),

			// Operator Token
			new SymbolTokenRelation('=', AssignmentOperatorToken.class,
					new SymbolTokenRelation('=', EqualOperatorToken.class)),
			new SymbolTokenRelation('>', GreaterThanOperatorToken.class,
					new SymbolTokenRelation('=', GreaterThanEqualOperatorToken.class),
					new SymbolTokenRelation('>', RightShiftOperatorToken.class,
							new SymbolTokenRelation('=', RightShiftAssignmentOperatorToken.class),
							new SymbolTokenRelation('>', UnsignedRightShiftOperatorToken.class))),
			new SymbolTokenRelation('<', LessThanOperatorToken.class,
					new SymbolTokenRelation('=', LessThanEqualOperatorToken.class),
					new SymbolTokenRelation('<', LeftShiftOperatorToken.class,
							new SymbolTokenRelation('=', LeftShiftAssignmentOperatorToken.class))),
			new SymbolTokenRelation('!', LogicalNotOperatorToken.class,
					new SymbolTokenRelation('=', NotEqualOperatorToken.class)),
			new SymbolTokenRelation('~', BitwiseNotOperatorToken.class),
			new SymbolTokenRelation('?', ConditionalOperatorToken.class),
			new SymbolTokenRelation(':', ColonOperatorToken.class),
			new SymbolTokenRelation('&', BitwiseAndOperatorToken.class,
					new SymbolTokenRelation('=', BitwiseAndAssignmentOperatorToken.class),
					new SymbolTokenRelation('&', LogicalAndOperatorToken.class)),
			new SymbolTokenRelation('|', BitwiseOrOperatorToken.class,
					new SymbolTokenRelation('=', BitwiseOrAssignmentOperatorToken.class),
					new SymbolTokenRelation('|', LogicalOrOperatorToken.class)),
			new SymbolTokenRelation('+', AdditionOperatorToken.class,
					new SymbolTokenRelation('=', AdditionAssignmentOperatorToken.class),
					new SymbolTokenRelation('+', IncrementOperatorToken.class)),
			new SymbolTokenRelation('-', SubtractionOperatorToken.class,
					new SymbolTokenRelation('=', SubtractionAssignmentOperatorToken.class),
					new SymbolTokenRelation('-', DecrementOperatorToken.class)),
			new SymbolTokenRelation('*', MultiplicationOperatorToken.class,
					new SymbolTokenRelation('=', MultiplicationAssignmentOperatorToken.class)),
			new SymbolTokenRelation('/', DivisionOperatorToken.class,
					new SymbolTokenRelation('=', DivisionAssignmentOperatorToken.class)),
			new SymbolTokenRelation('^', BitwiseXorOperatorToken.class,
					new SymbolTokenRelation('=', BitwiseXorAssignmentOperatorToken.class)),
			new SymbolTokenRelation('%', ModuloOperatorToken.class,
					new SymbolTokenRelation('=', ModuloAssignmentOperatorToken.class))
	};

	public static Token extract(TextBuffer textBuffer, InputStream inStrm)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		int index = textBuffer.getIndex();

		for (SymbolTokenRelation relation : rootRelations) {
			Token tok = relation.extractInternal(textBuffer, inStrm);
			if (tok != null)
				return tok;
			
			textBuffer.jump(inStrm, index);
		}

		return null;
	}
}
