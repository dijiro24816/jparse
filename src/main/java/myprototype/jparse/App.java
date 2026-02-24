package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.JavaLexer;

/**
 * Hello world!
 */

public class App {
	public static void maina(String[] args) throws IOException, InvalidTokenException, ClassNotFoundException {
		String grammarSource = """
				Statement -> Expression
				Statement -> Assignment
				Expression -> Identifier
				Expression -> Expression + Expression
				Assignment -> Identifier = Expression
				""";

		Grammar grammar = new Grammar("Statement", "$").resource(new ByteArrayInputStream(grammarSource.getBytes()));

		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
		syntaticsTable.createState();
//
//		syntaticsTable.serialize(new ObjectOutputStream(new FileOutputStream("syntatics_table.ser")));
//		syntaticsTable = SyntaticsTable
//				.deserialize(new ObjectInputStream(new FileInputStream("syntatics_table.ser")));

		System.out.println("actions.csv < ```");
		System.out.println(syntaticsTable.getActionsCSV());
		System.out.println("```");

		System.out.println("gotos.csv < ```");
		System.out.println(syntaticsTable.getGotosCSV());
		System.out.println("```");

		String sourceCode = """
				a = b + c + d
				""";

		System.out.println("*** Source ***");
		System.out.println("```");
		System.out.print(sourceCode);
		System.out.println("```");

		System.out.println();
		System.out.println("*** Loaded Token ***");
		Lexer lexerForPrint = new JavaLexer(grammar.getEndSymbol());
		InputStream inStrm = new ByteArrayInputStream(sourceCode.getBytes());
		for (;;) {
			Symbol symbol = lexerForPrint.getSymbol(inStrm);

			if (grammar.isEndSymbol(symbol)) {
				System.out.println(symbol);
				break;
			}

			System.out.println(symbol);
		}

		System.out.println("*** Parser Stack ***");
		Lexer lexer = new JavaLexer(grammar.getEndSymbol());
		Parser parser = new Parser(new BufferedLexer(lexer), syntaticsTable);
		Symbol symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
	}

	public static void main(String[] args) throws IOException, InvalidTokenException {
		String grammarSource = """
				CompilationUnit -> OrdinaryCompilationUnit
				# CompilationUnit -> ModularCompilationUnit
				
				OrdinaryCompilationUnit -> 
				OrdinaryCompilationUnit -> PackageDeclaration
				OrdinaryCompilationUnit -> _ImportDeclaration_Repeat
				OrdinaryCompilationUnit -> PackageDeclaration _ImportDeclaration_Repeat
				OrdinaryCompilationUnit -> TopLevelClassOrInterfaceDeclaration
				OrdinaryCompilationUnit -> PackageDeclaration TopLevelClassOrInterfaceDeclaration
				OrdinaryCompilationUnit -> _ImportDeclaration_Repeat TopLevelClassOrInterfaceDeclaration
				OrdinaryCompilationUnit -> PackageDeclaration _ImportDeclaration_Repeat TopLevelClassOrInterfaceDeclaration
				
				PackageDeclaration -> package Identifier ;
				# PackageDeclaration -> _PackageModifier_Repeat package Identifier ;
				PackageDeclaration -> package Identifier _PeriodIdentifier_Repeat ;
				# PackageDeclaration -> _PackageModifier_Repeat package Identifier _PeriodIdentifier_Repeat ;
				
				ImportDeclaration -> SingleTypeImportDeclaration
				ImportDeclaration -> TypeImportOnDemandDeclaration
				# ImportDeclaration -> SingleStaticImportDeclaration
				# ImportDeclaration -> StaticImportOnDemandDeclaration
				
				TypeName -> Identifier
				TypeName -> PackageOrTypeName . Identifier
				
				_ImportDeclaration_Repeat -> ImportDeclaration
				_ImportDeclaration_Repeat -> _ImportDeclaration_Repeat ImportDeclaration
				
				SingleTypeImportDeclaration -> import TypeName ;
				
				TypeImportOnDemandDeclaration -> import PackageOrTypeName . * ;
				
				PackageOrTypeName -> Identifier
				PackageOrTypeName -> PackageOrTypeName . Identifier
				
				TopLevelClassOrInterfaceDeclaration -> ClassDeclaration
				# TopLevelClassOrInterfaceDeclaration -> InterfaceDeclaration
				TopLevelClassOrInterfaceDeclaration -> ;
				
				ClassDeclaration -> NormalClassDeclaration
				# ClassDeclaration -> EnumDeclaration
				# ClassDeclaration -> RecordDeclaration
				
				NormalClassDeclaration -> class Identifier ClassBody
				NormalClassDeclaration -> _ClassModifier_Repeat class Identifier ClassBody
				
				ClassBody -> { }
				ClassBody -> { _ClassBodyDeclaration_Repeat }
				
				_ClassModifier_Repeat -> ClassModifier
				_ClassModifier_Repeat -> _ClassModifier_Repeat ClassModifier
				
				
				# ClassModifier -> Annotation
				ClassModifier -> public
				ClassModifier -> protected
				ClassModifier -> private
				ClassModifier -> abstract
				ClassModifier -> static
				ClassModifier -> final
				ClassModifier -> sealed
				ClassModifier -> non-sealed
				ClassModifier -> strictfp
				
				
				_ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
				_ClassBodyDeclaration_Repeat -> _ClassBodyDeclaration_Repeat ClassBodyDeclaration
				
				
				ClassBodyDeclaration -> ClassMemberDeclaration
				# ClassBodyDeclaration -> InstanceInitializer
				# ClassBodyDeclaration -> StaticInitializer
				# ClassBodyDeclaration -> ConstructorDeclaration
				
				# ClassMemberDeclaration -> FieldDeclaration
				ClassMemberDeclaration -> MethodDeclaration
				# ClassMemberDeclaration -> ClassDeclaration
				# ClassMemberDeclaration -> InterfaceDeclaration
				ClassMemberDeclaration -> ;
				
				
				MethodDeclaration -> MethodHeader MethodBody
				MethodDeclaration -> _MethodModifier_Repeat MethodHeader MethodBody
				
				# MethodModifier -> Annotation
				MethodModifier -> public
				MethodModifier -> protected
				MethodModifier -> private
				MethodModifier -> abstract
				MethodModifier -> static
				MethodModifier -> final
				MethodModifier -> synchronized
				MethodModifier -> native
				MethodModifier -> strictfp
				
				_MethodModifier_Repeat -> MethodModifier
				_MethodModifier_Repeat -> _MethodModifier_Repeat MethodModifier
				
				MethodHeader -> Result MethodDeclarator
				# MethodHeader -> Result MethodDeclarator Throws
				# MethodHeader -> TypeParameters Result MethodDeclarator
				# MethodHeader -> TypeParameters _Annotation_Repeat Result MethodDeclarator
				# MethodHeader -> TypeParameters Result MethodDeclarator Throws
				# MethodHeader -> TypeParameters _Annotation_Repeat Result MethodDeclarator Throws
				
				NumericType -> IntegralType
				NumericType -> FloatingPointType
				
				UnannPrimitiveType -> NumericType
				UnannPrimitiveType -> boolean
				
				UnannReferenceType -> UnannClassOrInterfaceType
				# UnannReferenceType -> UnannTypeVariable
				UnannReferenceType -> UnannArrayType
				
				Dims -> [ ]
				# Dims -> _Annotation_Repeat [ ]
				# Dims -> _Annotation_Repeat [ ] __Annotation_RepeatEmptySquareBrackets_Repeat
				
				UnannArrayType -> UnannPrimitiveType Dims
				UnannArrayType -> UnannClassOrInterfaceType Dims
				# UnannArrayType -> UnannTypeVariable Dims
				
				
				UnannClassOrInterfaceType -> UnannClassType
				# UnannClassOrInterfaceType -> UnannInterfaceType
				
				UnannClassType -> Identifier
				# UnannClassType -> TypeIdentifier TypeArguments
				# UnannClassType -> PackageName . TypeIdentifier
				#UnannClassType -> PackageName . _Annotation_Repeat TypeIdentifier
				# UnannClassType -> PackageName . TypeIdentifier TypeArguments
				# UnannClassType -> PackageName . _Annotation_Repeat TypeIdentifier TypeArguments
				UnannClassType -> UnannClassOrInterfaceType . Identifier
				# UnannClassType -> UnannClassOrInterfaceType . _Annotation_Repeat TypeIdentifier
				# UnannClassType -> UnannClassOrInterfaceType . TypeIdentifier TypeArguments
				# UnannClassType -> UnannClassOrInterfaceType . _Annotation_Repeat TypeIdentifier TypeArguments
				
				UnannType -> UnannPrimitiveType
				UnannType -> UnannReferenceType
				
				Result -> UnannType
				Result -> void
				
				MethodDeclarator -> Identifier ( )
				# MethodDeclarator -> Identifier ( ReceiverParameter , )
				MethodDeclarator -> Identifier ( FormalParameterList )
				# MethodDeclarator -> Identifier ( ReceiverParameter , FormalParameterList )
				# MethodDeclarator -> Identifier ( ) Dims
				# MethodDeclarator -> Identifier ( ReceiverParameter , ) Dims
				# MethodDeclarator -> Identifier ( FormalParameterList ) Dims
				# MethodDeclarator -> Identifier ( ReceiverParameter , FormalParameterList ) Dims
				
				FormalParameterList -> FormalParameter
				FormalParameterList -> FormalParameter _CommaFormalParameter_Repeat
				
				_CommaFormalParameter_Repeat -> , FormalParameter
				_CommaFormalParameter_Repeat -> _CommaFormalParameter_Repeat , FormalParameter
				
				FormalParameter -> UnannType VariableDeclaratorId
				# FormalParameter -> _VariableModifier_Repeat UnannType VariableDeclaratorId
				# FormalParameter -> VariableArityParameter
				
				VariableDeclaratorId -> Identifier
				# VariableDeclaratorId -> Identifier Dims
				
					
				MethodBody -> Block
				MethodBody -> ;
				
				Block -> { }
				Block -> { BlockStatements }
				
				BlockStatements -> BlockStatement
				BlockStatements -> BlockStatement _BlockStatement_Repeat
				
				_BlockStatement_Repeat -> BlockStatement
				_BlockStatement_Repeat -> _BlockStatement_Repeat BlockStatement
				
				# BlockStatement -> LocalClassOrInterfaceDeclaration
				# BlockStatement -> LocalVariableDeclarationStatement
				BlockStatement -> Statement
				
				Statement -> StatementWithoutTrailingSubstatement
				# Statement -> LabeledStatement
				# Statement -> IfThenStatement
				# Statement -> IfThenElseStatement
				# Statement -> WhileStatement
				# Statement -> ForStatement
				
				StatementWithoutTrailingSubstatement -> Block
				# StatementWithoutTrailingSubstatement -> EmptyStatement
				StatementWithoutTrailingSubstatement -> ExpressionStatement
				# StatementWithoutTrailingSubstatement -> AssertStatement
				# StatementWithoutTrailingSubstatement -> SwitchStatement
				# StatementWithoutTrailingSubstatement -> DoStatement
				# StatementWithoutTrailingSubstatement -> BreakStatement
				# StatementWithoutTrailingSubstatement -> ContinueStatement
				# StatementWithoutTrailingSubstatement -> ReturnStatement
				# StatementWithoutTrailingSubstatement -> SynchronizedStatement
				# StatementWithoutTrailingSubstatement -> ThrowStatement
				# StatementWithoutTrailingSubstatement -> TryStatement
				# StatementWithoutTrailingSubstatement -> YieldStatement
				
				ExpressionStatement -> StatementExpression ;
				
				# StatementExpression -> Assignment
				# StatementExpression -> PreIncrementExpression
				# StatementExpression -> PreDecrementExpression
				# StatementExpression -> PostIncrementExpression
				# StatementExpression -> PostDecrementExpression
				StatementExpression -> MethodInvocation
				# StatementExpression -> ClassInstanceCreationExpression
				
				
				# MethodInvocation -> MethodName ( )
				# MethodInvocation -> MethodName ( ArgumentList )
				# MethodInvocation -> TypeName . Identifier ( )
				# MethodInvocation -> TypeName . TypeArguments Identifier ( )
				# MethodInvocation -> TypeName . Identifier ( ArgumentList )
				# MethodInvocation -> TypeName . TypeArguments Identifier ( ArgumentList )
				MethodInvocation -> ExpressionName . Identifier ( )
				# MethodInvocation -> ExpressionName . TypeArguments Identifier ( )
				# MethodInvocation -> ExpressionName . Identifier ( ArgumentList )
				# MethodInvocation -> ExpressionName . TypeArguments Identifier ( ArgumentList )
				# MethodInvocation -> Primary . Identifier ( )
				# MethodInvocation -> Primary . TypeArguments Identifier ( )
				# MethodInvocation -> Primary . Identifier ( ArgumentList )
				# MethodInvocation -> Primary . TypeArguments Identifier ( ArgumentList )
				# MethodInvocation -> super . Identifier ( )
				# MethodInvocation -> super . TypeArguments Identifier ( )
				# MethodInvocation -> super . Identifier ( ArgumentList )
				# MethodInvocation -> super . TypeArguments Identifier ( ArgumentList )
				# MethodInvocation -> TypeName . super . Identifier ( )
				# MethodInvocation -> TypeName . super . TypeArguments Identifier ( )
				# MethodInvocation -> TypeName . super . Identifier ( ArgumentList )
				# MethodInvocation -> TypeName . super . TypeArguments Identifier ( ArgumentList )
				
				
				ExpressionName -> Identifier
				ExpressionName -> ExpressionName . Identifier
				""";

		Grammar grammar = new Grammar("CompilationUnit", "$").resource(new ByteArrayInputStream(grammarSource.getBytes()));

		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
		syntaticsTable.createState();

		System.out.println("actions.csv < ```");
		System.out.println(syntaticsTable.getActionsCSV());
		System.out.println("```");

		System.out.println("gotos.csv < ```");
		System.out.println(syntaticsTable.getGotosCSV());
		System.out.println("```");

		String sourceCode = """
				import hello.world;
				
				public class Hello {
				    public static void main(String[] args) {
				        System.out.println();
				    }
				}
				""";
//		class TypeIdentifier {
//			   public static void Identifier ( TypeIdentifier [ ] Identifier ) {
//			       Identifier . Identifier ( ) ;
//			   }
//			}
		String sourceToken = """
				import Identifier . Identifier . * ;

				class Identifier {
				    public static void Identifier ( Identifier [ ] Identifier ) { 
				        Identifier . Identifier . Identifier ( ) ;
				    }
				}

				""" + "$" + System.lineSeparator();

		System.out.println("*** Source ***");
		System.out.println("```");
		System.out.print(sourceCode);
		System.out.println("```");

		System.out.println();
		System.out.println("*** Loaded Token ***");
		Lexer lexerForPrint = new JavaLexer(grammar.getEndSymbol());
		InputStream inStrm = new ByteArrayInputStream(sourceCode.getBytes());
		for (;;) {
			Symbol symbol = lexerForPrint.getSymbol(inStrm);

			if (grammar.isEndSymbol(symbol)) {
				System.out.println(symbol);
				break;
			}

			System.out.println(symbol);
		}

		System.out.println("*** Parser Stack ***");
		Lexer lexer = new JavaLexer(grammar.getEndSymbol());
		Parser parser = new Parser(new BufferedLexer(lexer), syntaticsTable);
		Symbol symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
	}

	public static void mainaa(String[] args) throws ClassNotFoundException, IOException {
		Runtime r = Runtime.getRuntime();
		System.out.println("version    : " + r.version());
		System.out.println("maxMemory  : " + r.maxMemory());
		System.out.println("totalMemory: " + r.totalMemory());
//		System.exit(0);

		Grammar grammar;
		SyntaticsTable syntaticsTable;

//		String fname = "syntaticsTable.ser";
//		if (!new File(fname).exists()) {
		grammar = new Grammar("CompilationUnit", "$").resource(new FileInputStream("JavaSyntax21.txt"));
		syntaticsTable = new SyntaticsTable(grammar);
		syntaticsTable.createState();

//			syntaticsTable.serialize(new ObjectOutputStream(new FileOutputStream(fname)));
//		} else {
//			syntaticsTable = SyntaticsTable.deserialize(new ObjectInputStream(new FileInputStream(fname)));
//			grammar = syntaticsTable.getGrammar();
//		}

		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");

		try {
			FileWriter writer = new FileWriter("actions.csv");
			writer.write(syntaticsTable.getActionsCSV());
			writer.flush();
			writer.close();
			System.out.println("actions.csv < ```");
			System.out.println(syntaticsTable.getActionsCSV());
			System.out.println("```");

			writer = new FileWriter("gotos.csv");
			writer.write(syntaticsTable.getGotosCSV());
			writer.flush();
			writer.close();
			System.out.println("gotos.csv < ```");
			System.out.println(syntaticsTable.getGotosCSV());
			System.out.println("```");
		} catch (IOException e) {
			e.printStackTrace();
		}
//	       Identifier . Identifier . Identifier ( ) ;
//		Annotations package Identifier . Identifier ;
//	    enum Identifier EnumBody
		String src = """
				import Identifier . Identifier . * ;

				class TypeIdentifier {
				   public static void Identifier ( TypeIdentifier [ ] Identifier ) {
				       Identifier . Identifier ( ) ;
				   }
				}

				""" + "$" + System.lineSeparator();
		System.out.println();
		System.out.println("*** Source ***");
		System.out.println("```");
		System.out.print(src);
		System.out.println("```");

		try {
			System.out.println();
			System.out.println("*** Loaded Token ***");
			Lexer lexer = new TokenBasedLexer(src);
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			for (;;) {
				Symbol symbol = lexer.getSymbol(inStrm);
				if (symbol == null)
					throw new UnknownTokenException();

				if (grammar.isEndSymbol(symbol)) {
					System.out.println(symbol);
					break;
				}

				System.out.println(symbol);
			}
		} catch (IOException | InvalidTokenException | UnknownTokenException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.exit(0);
		}

		try {
			Lexer lexer = new TokenBasedLexer(src);
			Parser parser = new Parser(new BufferedLexer(lexer), syntaticsTable);
			System.out.println();
			System.out.println("*** Parser Stack ***");
			System.out.println("Accept " + parser.parse(new ByteArrayInputStream(src.getBytes())));
		} catch (IOException | InvalidTokenException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("MSG: Finished!");
	}

	public static void mainb(String[] args) throws CloneNotSupportedException {

		// ArrayList<Sample> samples = new ArrayList<Sample>();
		// samples.add(new Sample(1, 2));
		// samples.add(new Sample(1, 2));
		//
		// System.out.println(samples.stream().mapToInt(s -> { return s.b; }).sum());
		//
		// TextBuffer tb = new TextBuffer();
		// String s5 = """
		// h\\u0023\\u0023ello world
		// """;
		// InputStream is = new ByteArrayInputStream(s5.getBytes());
		// try {
		// for (int i = 0; i < 13; i++)
		// tb.getCharacter(is);
		//
		// } catch(IOException e) {
		// System.out.println(e.getMessage());
		// System.exit(0);
		// } catch (InvalidTokenException e) {
		// e.printStackTrace();
		// System.exit(0);
		// }
		// System.out.println(tb.stringBuilder.toString());
		// System.out.println(tb.getRawLength());
		// tb.erase(1, 3);
		// System.out.println(tb.stringBuilder.toString());
		// System.out.println(tb.erasedStrings.get(0));
		// System.out.println(tb.getRawLength());
		// System.out.println(tb.getByteCount(0));
		//
		//
		// System.exit(0);

		// int a = 123456789;
		//
		//
		// InputStream in = System.in;
		// Function<Void, Integer> read = (Void) -> {
		// try {
		// return in.read();
		// } catch (IOException e) {
		// // TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }
		// return 0;
		// };
		//
		//
		// while (read.apply(null) >= 0) {
		// System.out.println("ok");
		// }
		//
		//
		// System.out.println("\u0022 + "hello" + \u0022");
		//
		// System.out.println(a
		// +
		// + - +
		// + - - - +
		// + - - - - - +
		// + - - - - - - - +
		// + - - - - - +
		// + - - - +
		// + - +
		// +
		// a);
		// System.exit(a);

		// String s = """
		// a017
		// """;
		//
		// String s2 = "";
		// Tokenizer tokenizer = new Tokenizer();
		// InputStream ins = new ByteArrayInputStream(s.getBytes());
		// StringBuilder sb = new StringBuilder();
		// sb.append("hello");
		//
		// try {
		// tokenizer.textBuffer.jumpNext(ins);
		// System.out.println(tokenizer.extractOctalDigits(ins));
		// } catch (IOException e) {
		//
		// }
		//
		// System.exit(0);

		// String src = """
		// /*hello world private abstract asdf asdf asdf asd*/ hello private
		// hello
		// private
		// abstract
		// abs
		//
		// null
		// public class Main
		// 123123
		// 0x1
		// """;

		// String src = """
		// 123124
		// 0xFFFF
		// 0
		// """;

		// String src = """
		// public class Main
		// boolean _sample = true;
		// 0b011
		// (011)
		// """;

		// String src = """
		// = > < ! ~ ? :
		// == <= >= != && || ++ --
		// + - * / & | ^ % << >> >>>
		// += -= *= /= &= |= ^= %= <<= >>= >>>=
		// """;

		// String src = """
		// .1
		// 0.1
		// 0x2p+3
		// """;
		// String src = """
		// '\\u005C141'
		// '\\141'
		// """;

		// String src = """
		// "hello"
		// """;
		// TextBuffer tb = new TextBuffer();
		// InputStream is = new ByteArrayInputStream(src.getBytes());
		// try {
		// System.out.println(tb.getCharacter(is));
		// System.out.println(tb.getCharacter(is));
		// } catch (IOException e) {
		// // TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// } catch (InvalidTokenException e) {
		// // TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }

		// System.exit(0);

		// '\\u005C\\u005C'

		// System.out.println(Long.valueOf("0012"));
		// System.exit(0);

		// try {
		// InputStream inStrm = new ByteArrayInputStream(src.getBytes());
		// int ch;
		// while ((ch = inStrm.read()) != -1) {
		// System.out.print((char) ch);
		// }
		// } catch (IOException e) {
		// System.out.println(e.getMessage());
		// System.exit(0);
		// }

		// ParserData parserData = new ScenarioWriter().getParserData(s,
		// SymbolEnum.class);
		//
		// System.out.println(parserData.getRuleTableString());
		// System.out.println(parserData.getSyntaticsTableString());
		// System.exit(0);
		//
		// String src = "+ jkalsdfj klasdfj klasdfj kl 1 2";
		//
		// try {
		// Parser parser = new Parser(new Lexer(), parserData);
		// InputStream inStrm = new ByteArrayInputStream(src.getBytes());
		// parser.parse(inStrm);
		// // System.out.println("MSG: Finished!");
		// } catch (IOException e) {
		// System.out.println(e.getMessage());
		// System.exit(0);
		// }
	}
}
