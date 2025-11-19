package myprototype.jparse.token;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import myprototype.jparse.token.keyword.AbstractKeywordToken;
import myprototype.jparse.token.keyword.AssertKeywordToken;
import myprototype.jparse.token.keyword.BooleanKeywordToken;
import myprototype.jparse.token.keyword.BreakKeywordToken;
import myprototype.jparse.token.keyword.ByteKeywordToken;
import myprototype.jparse.token.keyword.CaseKeywordToken;
import myprototype.jparse.token.keyword.CatchKeywordToken;
import myprototype.jparse.token.keyword.CharKeywordToken;
import myprototype.jparse.token.keyword.ClassKeywordToken;
import myprototype.jparse.token.keyword.ConstKeywordToken;
import myprototype.jparse.token.keyword.ContinueKeywordToken;
import myprototype.jparse.token.keyword.DefaultKeywordToken;
import myprototype.jparse.token.keyword.DoKeywordToken;
import myprototype.jparse.token.keyword.DoubleKeywordToken;
import myprototype.jparse.token.keyword.ElseKeywordToken;
import myprototype.jparse.token.keyword.EnumKeywordToken;
import myprototype.jparse.token.keyword.ExtendsKeywordToken;
import myprototype.jparse.token.keyword.FinalKeywordToken;
import myprototype.jparse.token.keyword.FinallyKeywordToken;
import myprototype.jparse.token.keyword.FloatKeywordToken;
import myprototype.jparse.token.keyword.ForKeywordToken;
import myprototype.jparse.token.keyword.GotoKeywordToken;
import myprototype.jparse.token.keyword.IfKeywordToken;
import myprototype.jparse.token.keyword.ImplementsKeywordToken;
import myprototype.jparse.token.keyword.ImportKeywordToken;
import myprototype.jparse.token.keyword.InstanceofKeywordToken;
import myprototype.jparse.token.keyword.IntKeywordToken;
import myprototype.jparse.token.keyword.InterfaceKeywordToken;
import myprototype.jparse.token.keyword.LongKeywordToken;
import myprototype.jparse.token.keyword.NativeKeywordToken;
import myprototype.jparse.token.keyword.NewKeywordToken;
import myprototype.jparse.token.keyword.PackageKeywordToken;
import myprototype.jparse.token.keyword.PrivateKeywordToken;
import myprototype.jparse.token.keyword.ProtectedKeywordToken;
import myprototype.jparse.token.keyword.PublicKeywordToken;
import myprototype.jparse.token.keyword.ReturnKeywordToken;
import myprototype.jparse.token.keyword.ShortKeywordToken;
import myprototype.jparse.token.keyword.StaticKeywordToken;
import myprototype.jparse.token.keyword.StrictfpKeywordToken;
import myprototype.jparse.token.keyword.SuperKeywordToken;
import myprototype.jparse.token.keyword.SwitchKeywordToken;
import myprototype.jparse.token.keyword.SynchronizedKeywordToken;
import myprototype.jparse.token.keyword.ThisKeywordToken;
import myprototype.jparse.token.keyword.ThrowKeywordToken;
import myprototype.jparse.token.keyword.ThrowsKeywordToken;
import myprototype.jparse.token.keyword.TransientKeywordToken;
import myprototype.jparse.token.keyword.TryKeywordToken;
import myprototype.jparse.token.keyword.VoidKeywordToken;
import myprototype.jparse.token.keyword.VolatileKeywordToken;
import myprototype.jparse.token.keyword.WhileKeywordToken;
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

class TokenizerTest {
	
	Token tokenize(String src) {
		try {
			Tokenizer tokenizer = new Tokenizer();
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			return tokenizer.tokenize(inStrm);
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
	void tokenizeSymbol() {
		// Separator Token
		assertEquals(tokenize("("), new RoundBracketOpenSeparatorToken(0, 1));
		assertEquals(tokenize(")"), new RoundBracketCloseSeparatorToken(0, 1));
		assertEquals(tokenize("{"), new CurlyBracketOpenSeparatorToken(0, 1));
		assertEquals(tokenize("}"), new CurlyBracketCloseSeparatorToken(0, 1));
		assertEquals(tokenize("["), new SquareBracketOpenSeparatorToken(0, 1));
		assertEquals(tokenize("]"), new SquareBracketCloseSeparatorToken(0, 1));
		assertEquals(tokenize(";"), new SemicolonSeparatorToken(0, 1));
		assertEquals(tokenize(","), new CommaSeparatorToken(0, 1));
		assertEquals(tokenize("."), new PeriodSeparatorToken(0, 1));
		
		// Operator Token
		assertEquals(tokenize("="), new AssignmentOperatorToken(0, 1));
		assertEquals(tokenize("=="), new EqualOperatorToken(0, 2));
		assertEquals(tokenize(">"), new GreaterThanOperatorToken(0, 1));
		assertEquals(tokenize(">="), new GreaterThanEqualOperatorToken(0, 2));
		assertEquals(tokenize(">>"), new RightShiftOperatorToken(0, 2));
		assertEquals(tokenize(">>="), new RightShiftAssignmentOperatorToken(0, 3));
		assertEquals(tokenize(">>>"), new UnsignedRightShiftOperatorToken(0, 3));
		assertEquals(tokenize("<"), new LessThanOperatorToken(0, 1));
		assertEquals(tokenize("<="), new LessThanEqualOperatorToken(0, 2));
		assertEquals(tokenize("<<"), new LeftShiftOperatorToken(0, 2));
		assertEquals(tokenize("<<="), new LeftShiftAssignmentOperatorToken(0, 3));
		assertEquals(tokenize("!"), new LogicalNotOperatorToken(0, 1));
		assertEquals(tokenize("!="), new NotEqualOperatorToken(0, 2));
		assertEquals(tokenize("~"), new BitwiseNotOperatorToken(0, 1));
		assertEquals(tokenize("?"), new ConditionalOperatorToken(0, 1));
		assertEquals(tokenize(":"), new ColonOperatorToken(0, 1));
		assertEquals(tokenize("&"), new BitwiseAndOperatorToken(0, 1));
		assertEquals(tokenize("&="), new BitwiseAndAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("&&"), new LogicalAndOperatorToken(0, 2));
		assertEquals(tokenize("|"), new BitwiseOrOperatorToken(0, 1));
		assertEquals(tokenize("|="), new BitwiseOrAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("||"), new LogicalOrOperatorToken(0, 2));
		assertEquals(tokenize("+"), new AdditionOperatorToken(0, 1));
		assertEquals(tokenize("+="), new AdditionAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("++"), new IncrementOperatorToken(0, 2));
		assertEquals(tokenize("-"), new SubtractionOperatorToken(0, 1));
		assertEquals(tokenize("-="), new SubtractionAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("--"), new DecrementOperatorToken(0, 2));
		assertEquals(tokenize("*"), new MultiplicationOperatorToken(0, 1));
		assertEquals(tokenize("*="), new MultiplicationAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("/"), new DivisionOperatorToken(0, 1));
		assertEquals(tokenize("/="), new DivisionAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("^"), new BitwiseXorOperatorToken(0, 1));
		assertEquals(tokenize("^="), new BitwiseXorAssignmentOperatorToken(0, 2));
		assertEquals(tokenize("%"), new ModuloOperatorToken(0, 1));
		assertEquals(tokenize("%="), new ModuloAssignmentOperatorToken(0, 2));
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
