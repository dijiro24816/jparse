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
				new Rule("QualifiedIdentifier", "IdentifierPeriodRepeat"),
				// QualifiedIdentifier -> IdentifierPeriodRepeat
				
				new Rule("QualifiedIdentifierList", "QualifiedIdentifier"),
				new Rule("QualifiedIdentifierList", "QualifiedIdentifierList", ",", "QualifiedIdentifier"),
				// QualifiedIdentifierList -> QualifiedIdentifier
				// QualifiedIdentifierList -> QualifiedIdentifierList , QualifiedIdentifier

				new Rule("CompilationUnit"), new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";"),
				// CompilationUnit ->
				// CompilationUnit -> package QualifiedIdentifier ;
				// CompilationUnit -> Annotations package QualifiedIdentifier ;

				new Rule("CompilationUnit", "ImportDeclarationRepeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclarationRepeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclarationRepeat"),
				// CompilationUnit -> ImportDeclarationRepeat
				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclarationRepeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ;
				// ImportDeclarations

				new Rule("CompilationUnit", "TypeDeclarationRepeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "TypeDeclarationRepeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "TypeDeclarationRepeat"),
				// CompilationUnit -> TypeDeclarationRepeat
				// CompilationUnit -> package QualifiedIdentifier ; TypeDeclarationRepeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ; TypeDeclarationRepeat

				new Rule("CompilationUnit", "ImportDeclarationRepeat", "TypeDeclarationRepeat"),
				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclarationRepeat",
						"TypeDeclarationRepeat"),
				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclarationRepeat",
						"TypeDeclarationRepeat"),
				// CompilationUnit -> ImportDeclarationRepeat TypeDeclarationRepeat
				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclarationRepeat
				// TypeDeclarationRepeat
				// CompilationUnit -> Annotations package QualifiedIdentifier ;
				// ImportDeclarations TypeDeclarationRepeat


				new Rule("ImportDeclaration", "import", "IdentifierPeriodRepeat", ";"),
				new Rule("ImportDeclaration", "import", "static", "IdentifierPeriodRepeat", ";"),
				new Rule("ImportDeclaration", "import", "IdentifierPeriodRepeat", ".", "*", ";"),
				new Rule("ImportDeclaration", "import", "static", "IdentifierPeriodRepeat", ".", "*", ";"),
				// ImportDeclaration -> import IdentifierPeriodRepeat ;
				// ImportDeclaration -> import static IdentifierPeriodRepeat ;
				// ImportDeclaration -> import IdentifierPeriodRepeat . * ;
				// ImportDeclaration -> import static IdentifierPeriodRepeat . * ;

				new Rule("TypeDeclaration", "ClassOrInterfaceDeclaration"), new Rule("TypeDeclaration", ";"),
				// TypeDeclaration -> ClassOrInterfaceDeclaration
				// TypeDeclaration -> ;
				
				new Rule("ClassOrInterfaceDeclaration", "ClassDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "InterfaceDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "ModifierRepeat", "ClassDeclaration"),
				new Rule("ClassOrInterfaceDeclaration", "ModifierRepeat", "InterfaceDeclaration"),
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
				new Rule("Type", "BasicType", "EmptySquareBrackets"),
				new Rule("Type", "ReferenceType"),
				new Rule("Type", "ReferenceType", "EmptySquareBrackets"),
				// Type -> BasicType
				// Type -> BasicType EmptySquareBrackets
				// Type -> ReferenceType
				// Type -> ReferenceType EmptySquareBrackets
				
				
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
				
				new Rule("ReferenceType", "IdentifierTypeArgumentPeriodRepeat"),
				// ReferenceType -> IdentifierTypeArgumentPeriodRepeat

				new Rule("TypeArguments", "<", "TypeArgument_CommaRepeat", ">"),
				// TypeArguments -> < TypeArgument_CommaRepeat >
				
				
				// Original Rule
				
				new Rule("IdentifierPeriodRepeat", "Identifier"),
				new Rule("IdentifierPeriodRepeat", "IdentifierPeriodRepeat", ".", "Identifier"),
				// QualifiedIdentifier -> Identifier
				// QualifiedIdentifier -> QualifiedIdentifier . Identifier

				new Rule("ImportDeclarationRepeat", "ImportDeclaration"),
				new Rule("ImportDeclarationRepeat", "ImportDeclarationRepeat", "ImportDeclaration"),
				// ImportDeclarations -> ImportDeclaration
				// ImportDeclarations -> ImportDeclarations ImportDeclaration

				new Rule("TypeDeclarationRepeat", "TypeDeclaration"),
				new Rule("TypeDeclarationRepeat", "TypeDeclarationRepeat", "TypeDeclaration"),
				// TypeDeclarations -> TypeDeclaration
				// TypeDeclarations -> TypeDeclarations TypeDeclaration

				new Rule("ModifierRepeat", "Modifier"),
				new Rule("ModifierRepeat", "ModifierRepeat", "Modifier"),
				// Modifiers -> Modifier
				// Modifiers -> Modifiers Modifier
				
				new Rule("IdentifierTypeArgumentPeriodRepeat", "Identifier"),
				new Rule("IdentifierTypeArgumentPeriodRepeat", "Identifier", "TypeArguments"),
				new Rule("IdentifierTypeArgumentPeriodRepeat", "IdentifierTypeArgumentPeriodRepeat", ".", "Identifier"),
				new Rule("IdentifierTypeArgumentPeriodRepeat", "IdentifierTypeArgumentPeriodRepeat", ".", "Identifier", "TypeArguments"),
				// IdentifierTypeArgumentPeriodRepeat -> Identifier 
				// IdentifierTypeArgumentPeriodRepeat -> Identifier TypeArguments
				// IdentifierTypeArgumentPeriodRepeat -> IdentifierTypeArgumentPeriodRepeat . Identifier
				// IdentifierTypeArgumentPeriodRepeat -> IdentifierTypeArgumentPeriodRepeat . Identifier TypeArguments
				
				new Rule("EmptySquareBrackets", "[", "]"),
				new Rule("EmptySquareBrackets", "EmptySquareBrackets", "[", "]"),
				// EmptySquareBrackets -> []
				// EmptySquareBrackets -> EmptySquareBrackets []
				
				new Rule("TypeArgument_CommaRepeat", "TypeArgument"),
				new Rule("TypeArgument_CommaRepeat", "TypeArgument_CommaRepeat", ",", "TypeArgument"),
				// TypeArgument_CommaRepeat -> TypeArgument
				// TypeArgument_CommaRepeat -> TypeArgument_CommaRepeat , TypeArgument
				
				new Rule("ReferenceTypeCommaRepeat", "ReferenceType"),
				new Rule("ReferenceTypeCommaRepeat", "ReferenceTypeCommaRepeat", ",", "ReferenceType"),
				// ReferenceTypeCommaRepeat -> ReferenceType
				// ReferenceTypeCommaRepeat -> ReferenceTypeCommaRepeat , ReferenceType
				
				new Rule("TypeParameterCommaRepeat", "TypeParameter"),
				new Rule("TypeParameterCommaRepeat", "TypeParameterCommaRepeat", ",", "TypeParameter"),
				// TypeParameterCommaRepeat -> TypeParameter
				// TypeParameterCommaRepeat -> TypeParameterCommaRepeat , TypeParameter

				new Rule("ReferenceTypeAndRepeat", "ReferenceType"),
				new Rule("ReferenceTypeAndRepeat", "ReferenceTypeAndRepeat", "&", "ReferenceType"),
				// ReferenceTypeAndRepeat -> ReferenceType
				// ReferenceTypeAndRepeat -> ReferenceTypeAndRepeat & ReferenceType
				
				new Rule("AnnotationRepeat", "Annotation"),
				new Rule("AnnotationRepeat", "AnnotationRepeat", "Annotation"),
				// AnnotationRepeat -> Annotation
				// AnnotationRepeat -> AnnotationRepeat Annotation

				new Rule("ElementValuePairCommaRepeat", "ElementValuePair"),
				new Rule("ElementValuePairCommaRepeat", "ElementValuePairCommaRepeat", ",", "ElementValuePair"),
				// ElementValuePairCommaRepeat -> ElementValuePair
				// ElementValuePairCommaRepeat -> ElementValuePairCommaRepeat , ElementValuePair
				
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
				new Rule("ElementValue_CommaRepeat", "ElementValue_CommaRepeat", ",", "ElementValue")
				// ElementValue_CommaRepeat -> ElementValue
				// ElementValue_CommaRepeat -> ElementValue_CommaRepeat , ElementValue

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
