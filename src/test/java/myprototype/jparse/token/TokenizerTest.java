package myprototype.jparse.token;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import myprototype.jparse.symbol.terminal.IdentifierToken;
import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.Lexer;
import myprototype.jparse.symbol.terminal.Terminal;
import myprototype.jparse.symbol.terminal.keyword.AbstractKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.AssertKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BooleanKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BreakKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ByteKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CaseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CatchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CharKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ClassKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ConstKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ContinueKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DefaultKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoubleKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ElseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.EnumKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ExtendsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinalKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinallyKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FloatKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ForKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.GotoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IfKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImplementsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImportKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InstanceofKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IntKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InterfaceKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.LongKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NativeKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NewKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PackageKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PrivateKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ProtectedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PublicKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ReturnKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ShortKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StaticKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StrictfpKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SuperKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SwitchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SynchronizedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThisKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TransientKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TryKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VoidKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VolatileKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.WhileKeywordToken;
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

class TokenizerTest {

	Terminal tokenize(String src) {
		try {
			Lexer lexer = new Lexer();
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			return lexer.getSymbol(inStrm);
		} catch (IOException | InvalidTokenException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Test
	void test() {
		//		fail("まだ実装されていません");
	}

	@Test
	void tokenizeIdentifer() {
		String src = """
				hello
				""";
		assertEquals(tokenize(src), new IdentifierToken(0, 5, "hello"));
	}

	@Test
	void tokenizeBooleanLiteral() {

	}

	@Test
	void tokenizePunctuation() {
		// Separator Token
		assertEquals(tokenize("("), new RoundBracketOpenSeparatorToken(0, "(".length()));
		assertEquals(tokenize(")"), new RoundBracketCloseSeparatorToken(0, ")".length()));
		assertEquals(tokenize("{"), new CurlyBracketOpenSeparatorToken(0, "{".length()));
		assertEquals(tokenize("}"), new CurlyBracketCloseSeparatorToken(0, "}".length()));
		assertEquals(tokenize("["), new SquareBracketOpenSeparatorToken(0, "[".length()));
		assertEquals(tokenize("]"), new SquareBracketCloseSeparatorToken(0, "]".length()));
		assertEquals(tokenize(";"), new SemicolonSeparatorToken(0, ";".length()));
		assertEquals(tokenize(","), new CommaSeparatorToken(0, ",".length()));
		assertEquals(tokenize("."), new PeriodSeparatorToken(0, ".".length()));

		// Operator Token
		assertEquals(tokenize("="), new AssignmentOperatorToken(0, "=".length()));
		assertEquals(tokenize("=="), new EqualOperatorToken(0, "==".length()));
		assertEquals(tokenize(">"), new GreaterThanOperatorToken(0, ">".length()));
		assertEquals(tokenize(">="), new GreaterThanEqualOperatorToken(0, ">=".length()));
		assertEquals(tokenize(">>"), new RightShiftOperatorToken(0, ">>".length()));
		assertEquals(tokenize(">>="), new RightShiftAssignmentOperatorToken(0, ">>=".length()));
		assertEquals(tokenize(">>>"), new UnsignedRightShiftOperatorToken(0, ">>>".length()));
		assertEquals(tokenize("<"), new LessThanOperatorToken(0, "<".length()));
		assertEquals(tokenize("<="), new LessThanEqualOperatorToken(0, "<=".length()));
		assertEquals(tokenize("<<"), new LeftShiftOperatorToken(0, "<<".length()));
		assertEquals(tokenize("<<="), new LeftShiftAssignmentOperatorToken(0, "<<=".length()));
		assertEquals(tokenize("!"), new LogicalNotOperatorToken(0, "!".length()));
		assertEquals(tokenize("!="), new NotEqualOperatorToken(0, "!=".length()));
		assertEquals(tokenize("~"), new BitwiseNotOperatorToken(0, "~".length()));
		assertEquals(tokenize("?"), new ConditionalOperatorToken(0, "?".length()));
		assertEquals(tokenize(":"), new ColonOperatorToken(0, ":".length()));
		assertEquals(tokenize("&"), new BitwiseAndOperatorToken(0, "&".length()));
		assertEquals(tokenize("&="), new BitwiseAndAssignmentOperatorToken(0, "&=".length()));
		assertEquals(tokenize("&&"), new LogicalAndOperatorToken(0, "&&".length()));
		assertEquals(tokenize("|"), new BitwiseOrOperatorToken(0, "|".length()));
		assertEquals(tokenize("|="), new BitwiseOrAssignmentOperatorToken(0, "|=".length()));
		assertEquals(tokenize("||"), new LogicalOrOperatorToken(0, "||".length()));
		assertEquals(tokenize("+"), new AdditionOperatorToken(0, "+".length()));
		assertEquals(tokenize("+="), new AdditionAssignmentOperatorToken(0, "+=".length()));
		assertEquals(tokenize("++"), new IncrementOperatorToken(0, "++".length()));
		assertEquals(tokenize("-"), new SubtractionOperatorToken(0, "-".length()));
		assertEquals(tokenize("-="), new SubtractionAssignmentOperatorToken(0, "-=".length()));
		assertEquals(tokenize("--"), new DecrementOperatorToken(0, "--".length()));
		assertEquals(tokenize("*"), new MultiplicationOperatorToken(0, "*".length()));
		assertEquals(tokenize("*="), new MultiplicationAssignmentOperatorToken(0, "*=".length()));
		assertEquals(tokenize("/"), new DivisionOperatorToken(0, "/".length()));
		assertEquals(tokenize("/="), new DivisionAssignmentOperatorToken(0, "/=".length()));
		assertEquals(tokenize("^"), new BitwiseXorOperatorToken(0, "^".length()));
		assertEquals(tokenize("^="), new BitwiseXorAssignmentOperatorToken(0, "^=".length()));
		assertEquals(tokenize("%"), new ModuloOperatorToken(0, "%".length()));
		assertEquals(tokenize("%="), new ModuloAssignmentOperatorToken(0, "%=".length()));
	}

	@Test
	void tokenizeKeyword() {
		assertEquals(tokenize("abstract"), new AbstractKeywordToken(0, "abstract".length()));
		assertEquals(tokenize("continue"), new ContinueKeywordToken(0, "continue".length()));
		assertEquals(tokenize("for"), new ForKeywordToken(0, "for".length()));
		assertEquals(tokenize("new"), new NewKeywordToken(0, "new".length()));
		assertEquals(tokenize("switch"), new SwitchKeywordToken(0, "switch".length()));
		assertEquals(tokenize("assert"), new AssertKeywordToken(0, "assert".length()));
		assertEquals(tokenize("default"), new DefaultKeywordToken(0, "default".length()));
		assertEquals(tokenize("if"), new IfKeywordToken(0, "if".length()));
		assertEquals(tokenize("package"), new PackageKeywordToken(0, "package".length()));
		assertEquals(tokenize("synchronized"), new SynchronizedKeywordToken(0, "synchronized".length()));
		assertEquals(tokenize("boolean"), new BooleanKeywordToken(0, "boolean".length()));
		assertEquals(tokenize("do"), new DoKeywordToken(0, "do".length()));
		assertEquals(tokenize("goto"), new GotoKeywordToken(0, "goto".length()));
		assertEquals(tokenize("private"), new PrivateKeywordToken(0, "private".length()));
		assertEquals(tokenize("this"), new ThisKeywordToken(0, "this".length()));
		assertEquals(tokenize("break"), new BreakKeywordToken(0, "break".length()));
		assertEquals(tokenize("double"), new DoubleKeywordToken(0, "double".length()));
		assertEquals(tokenize("implements"), new ImplementsKeywordToken(0, "implements".length()));
		assertEquals(tokenize("protected"), new ProtectedKeywordToken(0, "protected".length()));
		assertEquals(tokenize("throw"), new ThrowKeywordToken(0, "throw".length()));
		assertEquals(tokenize("byte"), new ByteKeywordToken(0, "byte".length()));
		assertEquals(tokenize("else"), new ElseKeywordToken(0, "else".length()));
		assertEquals(tokenize("import"), new ImportKeywordToken(0, "import".length()));
		assertEquals(tokenize("public"), new PublicKeywordToken(0, "public".length()));
		assertEquals(tokenize("throws"), new ThrowsKeywordToken(0, "throws".length()));
		assertEquals(tokenize("case"), new CaseKeywordToken(0, "case".length()));
		assertEquals(tokenize("enum"), new EnumKeywordToken(0, "enum".length()));
		assertEquals(tokenize("instanceof"), new InstanceofKeywordToken(0, "instanceof".length()));
		assertEquals(tokenize("return"), new ReturnKeywordToken(0, "return".length()));
		assertEquals(tokenize("transient"), new TransientKeywordToken(0, "transient".length()));
		assertEquals(tokenize("catch"), new CatchKeywordToken(0, "catch".length()));
		assertEquals(tokenize("extends"), new ExtendsKeywordToken(0, "extends".length()));
		assertEquals(tokenize("int"), new IntKeywordToken(0, "int".length()));
		assertEquals(tokenize("short"), new ShortKeywordToken(0, "short".length()));
		assertEquals(tokenize("try"), new TryKeywordToken(0, "try".length()));
		assertEquals(tokenize("char"), new CharKeywordToken(0, "char".length()));
		assertEquals(tokenize("final"), new FinalKeywordToken(0, "final".length()));
		assertEquals(tokenize("interface"), new InterfaceKeywordToken(0, "interface".length()));
		assertEquals(tokenize("static"), new StaticKeywordToken(0, "static".length()));
		assertEquals(tokenize("void"), new VoidKeywordToken(0, "void".length()));
		assertEquals(tokenize("class"), new ClassKeywordToken(0, "class".length()));
		assertEquals(tokenize("finally"), new FinallyKeywordToken(0, "finally".length()));
		assertEquals(tokenize("long"), new LongKeywordToken(0, "long".length()));
		assertEquals(tokenize("strictfp"), new StrictfpKeywordToken(0, "strictfp".length()));
		assertEquals(tokenize("volatile"), new VolatileKeywordToken(0, "volatile".length()));
		assertEquals(tokenize("const"), new ConstKeywordToken(0, "const".length()));
		assertEquals(tokenize("float"), new FloatKeywordToken(0, "float".length()));
		assertEquals(tokenize("native"), new NativeKeywordToken(0, "native".length()));
		assertEquals(tokenize("super"), new SuperKeywordToken(0, "super".length()));
		assertEquals(tokenize("while"), new WhileKeywordToken(0, "while".length()));
	}

}
