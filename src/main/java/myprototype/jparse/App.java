package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.JavaLexer;

/**
 * Hello world!
 */

public class App {
	public static void maina(String[] args) throws IOException, InvalidTokenException {
		Grammar grammar = new Grammar("S", "$", new Rule("S", "Statement"), new Rule("Statement", "Expression"),
				new Rule("Statement", "Assignment"), new Rule("Expression", "Identifier"),
				new Rule("Expression", "Expression", "+", "Expression"),
				new Rule("Assignment", "Identifier", "=", "Expression"));

		String sourceCode = """
				a = b + c + d
				""";
		Lexer lexer = new JavaLexer("$");
		Parser parser = new Parser(new BufferedLexer(lexer), grammar, new SyntaticsTable(grammar));
		Symbol symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
	}

	public static void main(String[] args) {
		Grammar grammar = new Grammar("S", "$", new Rule("S", "CompilationUnit"),
				new Rule("QualifiedIdentifier", "Identifier", "PeriodIdentifier_Repeat"),
				// QualifiedIdentifier -> Identifier PeriodIdentifier_Repeat
				
				new Rule("QualifiedIdentifierList", "QualifiedIdentifier"),
				new Rule("QualifiedIdentifierList", "QualifiedIdentifierList", ",", "QualifiedIdentifier"),
				// QualifiedIdentifierList -> QualifiedIdentifier
				// QualifiedIdentifierList -> QualifiedIdentifierList , QualifiedIdentifier

				new Rule("CompilationUnit"), new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";"),
				// CompilationUnit ->
				// CompilationUnit -> package QualifiedIdentifier ;
				// CompilationUnit -> Annotations package QualifiedIdentifier ;

				new Rule("CompilationUnit", "ImportDeclaration_Repeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat"),
				// CompilationUnit -> ImportDeclaration_Repeat
				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ;
				// ImportDeclarations

				new Rule("CompilationUnit", "TypeDeclaration_Repeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "TypeDeclaration_Repeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "TypeDeclaration_Repeat"),
				// CompilationUnit -> TypeDeclaration_Repeat
				// CompilationUnit -> package QualifiedIdentifier ; TypeDeclaration_Repeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ; TypeDeclaration_Repeat

				new Rule("CompilationUnit", "ImportDeclaration_Repeat", "TypeDeclaration_Repeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat",
						"TypeDeclaration_Repeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat",
						"TypeDeclaration_Repeat"),
				// CompilationUnit -> ImportDeclaration_Repeat TypeDeclaration_Repeat
				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat
				// TypeDeclaration_Repeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ;
				// ImportDeclarations TypeDeclaration_Repeat


				new Rule("ImportDeclaration", "import", "Identifier", "PeriodIdentifier_Repeat", ";"),
				new Rule("ImportDeclaration", "import", "static", "Identifier", "PeriodIdentifier_Repeat", ";"),
				new Rule("ImportDeclaration", "import", "Identifier", "PeriodIdentifier_Repeat", ".", "*", ";"),
				new Rule("ImportDeclaration", "import", "static", "Identifier", "PeriodIdentifier_Repeat", ".", "*", ";"),
				// ImportDeclaration -> import Identifier PeriodIdentifier_Repeat ;
				// ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat ;
				// ImportDeclaration -> import Identifier PeriodIdentifier_Repeat . * ;
				// ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat . * ;

				new Rule("TypeDeclaration", "ClassOrInterfaceDeclaration"), new Rule("TypeDeclaration", ";"),
				// TypeDeclaration -> ClassOrInterfaceDeclaration
				// TypeDeclaration -> ;
				
				new Rule("ClassOrInterfaceDeclaration", "ClassDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "InterfaceDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "Modifier_Repeat", "ClassDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "Modifier_Repeat", "InterfaceDeclaration"),
				// ClassOrInterfaceDeclaration -> ClassDeclaration
				// ClassOrInterfaceDeclaration -> InterfaceDeclaration
				// ClassOrInterfaceDeclaration -> Modifiers ClassDeclaration
				// ClassOrInterfaceDeclaration -> Modifiers InterfaceDeclaration
				
				new Rule("ClassDeclaration", "NormalClassDeclaration"),
				new Rule("ClassDeclaration", "EnumDeclaration"),
				// ClassDeclaration -> NormalClassDeclaration
				// ClassDeclaration -> EnumDeclaration
				
				new Rule("InterfaceDeclaration", "NormalInterfaceDeclaration"),
				new Rule("InterfaceDeclaration", "AnnotationTypeDeclaration"),
				// InterfaceDeclaration -> NormalInterfaceDeclaration
				// InterfaceDeclaration -> AnnotationTypeDeclaration
				
				new Rule("NormalClassDeclaration", "class", "Identifier", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "extends", "Type", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "extends", "Type", "ClassBody"),
				// NormalClassDeclaration -> class Identifier ClassBody
				// NormalClassDeclaration -> class Identifier TypeParameters ClassBody
				// NormalClassDeclaration -> class Identifier extends Type ClassBody
				// NormalClassDeclaration -> class Identifier TypeParameters extends Type ClassBody

				new Rule("NormalClassDeclaration", "class", "Identifier", "implements", "TypeList", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "implements", "TypeList", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "extends", "Type", "implements", "TypeList", "ClassBody"),
				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "extends", "Type", "implements", "TypeList", "ClassBody"),
				// NormalClassDeclaration -> class Identifier implements TypeList ClassBody
				// NormalClassDeclaration -> class Identifier TypeParameters implements TypeList ClassBody
				// NormalClassDeclaration -> class Identifier extends Type implements TypeList ClassBody
				// NormalClassDeclaration -> class Identifier TypeParameters extends Type implements TypeList ClassBody
				
				new Rule("EnumDeclaration", "enum", "Identifier", "EnumBody"),
				new Rule("EnumDeclaration", "enum", "implements", "TypeList", "Identifier", "EnumBody"),
				// EnumDeclaration -> enum Identifier EnumBody
				// EnumDeclaration -> enum Identifier implements TypeList EnumBody
				
				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "InterfaceBody"),
				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "TypeParameters", "InterfaceBody"),
				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "extends", "TypeList", "InterfaceBody"),
				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "TypeParameters", "extends", "TypeList", "InterfaceBody"),
				// NormalInterfaceDeclaration -> interface Identifier InterfaceBody
				// NormalInterfaceDeclaration -> interface Identifier TypeParameters InterfaceBody
				// NormalInterfaceDeclaration -> interface Identifier extends TypeList InterfaceBody
				// NormalInterfaceDeclaration -> interface Identifier TypeParameters extends TypeList InterfaceBody
				
				new Rule("AnnotationTypeDeclaration", "@", "interface", "Identifier", "AnnotationTypeBody"),
				// AnnotationTypeDeclaration -> @ interface Identifier AnnotationTypeBody
				
				new Rule("Type", "BasicType"),
				new Rule("Type", "BasicType", "_EmptySquareBrackets_Repeat"),
				new Rule("Type", "ReferenceType"),
				new Rule("Type", "ReferenceType", "_EmptySquareBrackets_Repeat"),
				// Type -> BasicType
				// Type -> BasicType _EmptySquareBrackets_Repeat
				// Type -> ReferenceType
				// Type -> ReferenceType _EmptySquareBrackets_Repeat
				
				
				new Rule("BasicType", "byte"),
				new Rule("BasicType", "short"),
				new Rule("BasicType", "char"),
				new Rule("BasicType", "int"),
				new Rule("BasicType", "long"),
				new Rule("BasicType", "float"),
				new Rule("BasicType", "double"),
				new Rule("BasicType", "boolean"),
				// BasicType -> byte
				// BasicType -> short
				// BasicType -> char
				// BasicType -> int
				// BasicType -> long
				// BasicType -> float
				// BasicType -> double
				// BasicType -> boolean
				
				new Rule("ReferenceType", "Identifier", "_PeriodIdentifierTypeArgument_Repeat"),
				new Rule("ReferenceType", "Identifier", "TypeArguments", "_PeriodIdentifierTypeArgument_Repeat"),
				// ReferenceType -> Identifier _PeriodIdentifierTypeArgument_Repeat
				// ReferenceType -> Identifier TypeArguments _PeriodIdentifierTypeArgument_Repeat

				new Rule("TypeArguments", "<", "TypeArgument", "_CommaTypeArgument_Repeat", ">"),
				// TypeArguments -> < _CommaTypeArgument_Repeat >
				
				
				new Rule("TypeArgument", "ReferenceType"),
				new Rule("TypeArgument", "? extends ReferenceType"),
				new Rule("TypeArgument", "? super ReferenceType"),
				// TypeArgument -> ReferenceType
				// TypeArgument -> ?
				// TypeArgument -> ? extends ReferenceType
				// TypeArgument -> ? super ReferenceType
				
				
				
				new Rule("NonWildcardTypeArguments", "<", "TypeList", ">"),
				// NonWildcardTypeArguments -> < TypeList >
				
				
				new Rule("TypeList", "ReferenceType", "_CommaReferenceType_Repeat"),
				// TypeList -> _CommaReferenceType_Repeat
				
				
				new Rule("TypeArgumentsOrDiamond", "<", ">"),
				new Rule("TypeArgumentsOrDiamond", "TypeArguments"),
				// TypeArgumentsOrDiamond -> < >
				// TypeArgumentsOrDiamond -> TypeArguments
				
				
				
				new Rule("NonWildcardTypeArgumentsOrDiamond", "<", ">"),
				new Rule("NonWildcardTypeArgumentsOrDiamond", "NonWildcardTypeArguments"),
				// NonWildcardTypeArgumentsOrDiamond -> < >
				// NonWildcardTypeArgumentsOrDiamond -> NonWildcardTypeArguments
				
				
				new Rule("TypeParameters", "<", "TypeParameter", "_CommaTypeParameter_Repeat", ">"),
				// TypeParameters -> < TypeParameter _CommaTypeParameter_Repeat >
				
				
				
				new Rule("TypeParameter", "Identifier"),
				new Rule("TypeParameter", "Identifier", "extends", "Bound"),
				// TypeParameter -> Identifier
				// TypeParameter -> Identifier extends Bound
				
				
				new Rule("Bound", "AndReferenceType_Repeat"),
				// Bound -> AndReferenceType_Repeat
				
				new Rule("Modifier", "Annotation"),
				new Rule("Modifier", "public"),
				new Rule("Modifier", "protected"),
				new Rule("Modifier", "private"),
				new Rule("Modifier", "static"),
				new Rule("Modifier", "abstract"),
				new Rule("Modifier", "final"),
				new Rule("Modifier", "native"),
				new Rule("Modifier", "synchronized"),
				new Rule("Modifier", "transient"),
				new Rule("Modifier", "volatile"),
				new Rule("Modifier", "strictfp"),
				// Modifier -> Annotation
				// Modifier -> public
				// Modifier -> protected
				// Modifier -> private
				// Modifier -> static
				// Modifier -> abstract
				// Modifier -> final
				// Modifier -> native
				// Modifier -> synchronized
				// Modifier -> transient
				// Modifier -> volatile
				// Modifier -> strictfp
				
				
				new Rule("Annotations", "Annotaion"),
				// Annotations -> Annotation_Repeat
				
				
				new Rule("Annotation", "@", "QualifiedIdentifier"),
				new Rule("Annotation", "@", "QualifiedIdentifier", "(", ")"),
				new Rule("Annotation", "@", "QualifiedIdentifier", "(", "AnnotationElement", ")"),
				// Annotation -> @ QualifiedIdentifier
				// Annotation -> @ QualifiedIdentifier ( )
				// Annotation -> @ QualifiedIdentifier ( AnnotationElement )
				
				
				new Rule("AnnotationElement ", "ElementValuePairs"),
				new Rule("AnnotationElement ", "ElementValue"),
				// AnnotationElement -> ElementValuePairs
				// AnnotationElement -> ElementValue
				
				new Rule("ElementValuePairz", "ElementValuePair_CommaRepeat"),
				// ElementValuePairz -> ElementValuePair_CommaRepeat
				
				new Rule("ElementValuePair", "Identifier", "=", "ElementValue"),
				// ElementValuePair -> Identifier = ElementValue
				
				
				new Rule("ElementValue", "Annotation"),
				new Rule("ElementValue", "Expression1"),
				new Rule("ElementValue", "ElementValueArrayInitializer"),
				// ElementValue -> Annotation
				// ElementValue -> Expression1
				// ElementValue -> ElementValueArrayInitializer
				
				new Rule("ElementValueArrayInitializer", "ElementValuesComma_Repeat"),
				// ElementValueArrayInitializer -> ElementValuesComma_Repeat
				
				new Rule("ElementValues", "ElementValue_CommaRepeat"),
				// ElementValues -> ElementValue_CommaRepeat
				
				
				// Original Rule
				
				new Rule("PeriodIdentifier_Repeat", ".", "Identifier"),
				new Rule("PeriodIdentifier_Repeat", "PeriodIdentifier_Repeat", ".", "Identifier"),
				// QualifiedIdentifier -> Identifier
				// QualifiedIdentifier -> QualifiedIdentifier . Identifier

				new Rule("ImportDeclaration_Repeat", "ImportDeclaration"),
				new Rule("ImportDeclaration_Repeat", "ImportDeclaration_Repeat", "ImportDeclaration"),
				// ImportDeclarations -> ImportDeclaration
				// ImportDeclarations -> ImportDeclarations ImportDeclaration

				new Rule("TypeDeclaration_Repeat", "TypeDeclaration"),
				new Rule("TypeDeclaration_Repeat", "TypeDeclaration_Repeat", "TypeDeclaration"),
				// TypeDeclarations -> TypeDeclaration
				// TypeDeclarations -> TypeDeclarations TypeDeclaration

				new Rule("Modifier_Repeat", "Modifier"),
				new Rule("Modifier_Repeat", "Modifier_Repeat", "Modifier"),
				// Modifiers -> Modifier
				// Modifiers -> Modifiers Modifier
				
				new Rule("_PeriodIdentifierTypeArgument_Repeat", ".", "Identifier"),
				new Rule("_PeriodIdentifierTypeArgument_Repeat", ".", "Identifier", "TypeArguments"),
				// _PeriodIdentifierTypeArgument_Repeat -> . Identifier 
				// _PeriodIdentifierTypeArgument_Repeat -> . Identifier TypeArguments
				
				new Rule("_EmptySquareBrackets_Repeat", "[", "]"),
				new Rule("_EmptySquareBrackets_Repeat", "_EmptySquareBrackets_Repeat", "[", "]"),
				// _EmptySquareBrackets_Repeat -> []
				// _EmptySquareBrackets_Repeat -> _EmptySquareBrackets_Repeat []
				
				new Rule("_CommaTypeArgument_Repeat", ",", "TypeArgument"),
				new Rule("_CommaTypeArgument_Repeat", "_CommaTypeArgument_Repeat", ",", "TypeArgument"),
				// _CommaTypeArgument_Repeat -> , TypeArgument
				// _CommaTypeArgument_Repeat -> _CommaTypeArgument_Repeat , TypeArgument
				
				new Rule("_CommaReferenceType_Repeat", "ReferenceType"),
				new Rule("_CommaReferenceType_Repeat", "_CommaReferenceType_Repeat", ",", "ReferenceType"),
				// _CommaReferenceType_Repeat -> , ReferenceType
				// _CommaReferenceType_Repeat -> _CommaReferenceType_Repeat , ReferenceType
				
				new Rule("_CommaTypeParameter_Repeat", ",", "TypeParameter"),
				new Rule("_CommaTypeParameter_Repeat", "_CommaTypeParameter_Repeat", ",", "TypeParameter"),
				// _CommaTypeParameter_Repeat -> , TypeParameter
				// _CommaTypeParameter_Repeat -> _CommaTypeParameter_Repeat , TypeParameter

				new Rule("AndReferenceType_Repeat", "ReferenceType"),
				new Rule("AndReferenceType_Repeat", "AndReferenceType_Repeat", "&", "ReferenceType"),
				// AndReferenceType_Repeat -> ReferenceType
				// AndReferenceType_Repeat -> AndReferenceType_Repeat & ReferenceType
				
				new Rule("Annotation_Repeat", "Annotation"),
				new Rule("Annotation_Repeat", "Annotation_Repeat", "Annotation"),
				// Annotation_Repeat -> Annotation
				// Annotation_Repeat -> Annotation_Repeat Annotation

				new Rule("ElementValuePair_CommaRepeat", "ElementValuePair"),
				new Rule("ElementValuePair_CommaRepeat", "ElementValuePair_CommaRepeat", ",", "ElementValuePair"),
				// ElementValuePair_CommaRepeat -> ElementValuePair
				// ElementValuePair_CommaRepeat -> ElementValuePair_CommaRepeat , ElementValuePair
				
				new Rule("ElementValuesComma_Repeat"),
				new Rule("ElementValuesComma_Repeat", "ElementValues"),
				new Rule("ElementValuesComma_Repeat", ","),
				new Rule("ElementValuesComma_Repeat", "ElementValues", ","),
				new Rule("ElementValuesComma_Repeat", "ElementValuesComma_Repeat", "ElementValues"),
				new Rule("ElementValuesComma_Repeat", "ElementValuesComma_Repeat", ","),
				new Rule("ElementValuesComma_Repeat", "ElementValuesComma_Repeat", "ElementValues", ","),
				// ElementValuesComma_Repeat -> 
				// ElementValuesComma_Repeat -> ElementValues
				// ElementValuesComma_Repeat -> ,
				// ElementValuesComma_Repeat -> ElementValues ,
				// ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues
				// ElementValuesComma_Repeat -> ElementValuesComma_Repeat ,
				// ElementValuesComma_Repeat -> ElementValuesComma_Repeat ElementValues ,

				new Rule("ElementValue_CommaRepeat", "ElementValue"),
				new Rule("ElementValue_CommaRepeat", "ElementValue_CommaRepeat", ",", "ElementValue"),
				// ElementValue_CommaRepeat -> ElementValue
				// ElementValue_CommaRepeat -> ElementValue_CommaRepeat , ElementValue

				
				new Rule("ClassBodyDeclaration_Repeat", "ClassBodyDeclaration"),
				new Rule("ClassBodyDeclaration_Repeat", "ClassBodyDeclaration_Repeat", "ClassBodyDeclaration"),
				// ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
				// ClassBodyDeclaration_Repeat -> ClassBodyDeclaration_Repeat ClassBodyDeclaration
				
				
				new Rule("CommaVariableDeclarator_Repeat", ",", "VariableDeclarator"),
				new Rule("CommaVariableDeclarator_Repeat", "CommaVariableDeclarator_Repeat", ",", "VariableDeclarator")
				// CommaVariableDeclarator_Repeat -> , VariableDeclarator
				// CommaVariableDeclarator_Repeat -> CommaVariableDeclarator_Repeat , VariableDeclarator
		);

		System.out.println("*** Grammar ***");
		System.out.println(grammar);
		
		System.out.println();
		System.out.println("*** Syntatics Table ***");
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);

		try {
			FileWriter writer = new FileWriter("actions.csv");
			writer.write(syntaticsTable.getActionsCSV(grammar));
			writer.flush();
			writer.close();
			System.out.println("actions.csv < ```");
			System.out.println(syntaticsTable.getActionsCSV(grammar));
			System.out.println("```");
			
			
			System.out.println("hello");

			writer = new FileWriter("gotos.csv");
			writer.write(syntaticsTable.getGotosCSV(grammar));
			writer.flush();
			writer.close();
			System.out.println("gotos.csv < ```");
			System.out.println(syntaticsTable.getGotosCSV(grammar));
			System.out.println("```");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String src = """
				Annotations package Identifier . Identifier ;

				import Identifier . Identifier . * ;
						
				class Identifier ClassBody
				
				enum Identifier EnumBody
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
			Parser parser = new Parser(new BufferedLexer(lexer), grammar, syntaticsTable);
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
