package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		String grammarSource = """
				S -> Statement
				Statement -> Expression
				Statement -> Assignment
				Expression -> Identifier
				Expression -> Expression + Expression
				Assignment -> Identifier = Expression
				""";
		
		Grammar grammar = new Grammar("S", "$", new ByteArrayInputStream(grammarSource.getBytes()));
		System.out.println("*** Grammar ***");
		System.out.println(grammar);
		
		System.out.println();
		System.out.println("*** Syntatics Table ***");
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
		
		System.out.println("actions.csv < ```");
		System.out.println(syntaticsTable.getActionsCSV(grammar));
		System.out.println("```");

		System.out.println("gotos.csv < ```");
		System.out.println(syntaticsTable.getGotosCSV(grammar));
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
		Lexer lexerForPrint = new JavaLexer("$");
		InputStream inStrm = new ByteArrayInputStream(sourceCode.getBytes());
		for (;;) {
			Symbol symbol = lexerForPrint.getSymbol(inStrm);

			if (grammar.isEndSymbol(symbol)) {
				System.out.println(symbol);
				break;
			}

			System.out.println(symbol);
		}

		Lexer lexer = new JavaLexer("$");
		Parser parser = new Parser(new BufferedLexer(lexer), grammar, syntaticsTable);
		Symbol symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Grammar grammar = new Grammar("S", "$", new FileInputStream("JavaSyntax21.txt"));
//		Grammar grammar = new Grammar("S", "$", new Rule("S", "CompilationUnit"),
//				new Rule("QualifiedIdentifier", "Identifier", "PeriodIdentifier_Repeat"),
//				// QualifiedIdentifier -> Identifier PeriodIdentifier_Repeat
//				
//				new Rule("QualifiedIdentifierList", "QualifiedIdentifier"),
//				new Rule("QualifiedIdentifierList", "QualifiedIdentifierList", ",", "QualifiedIdentifier"),
//				// QualifiedIdentifierList -> QualifiedIdentifier
//				// QualifiedIdentifierList -> QualifiedIdentifierList , QualifiedIdentifier
//
//				new Rule("CompilationUnit"), new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";"),
//				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";"),
//				// CompilationUnit ->
//				// CompilationUnit -> package QualifiedIdentifier ;
//				// CompilationUnit -> Annotations package QualifiedIdentifier ;
//
//				new Rule("CompilationUnit", "ImportDeclaration_Repeat"),
//				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat"),
//				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat"),
//				// CompilationUnit -> ImportDeclaration_Repeat
//				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat
//				// CompilationUnit -> Annotations package QualifiedIdentifier ;
//				// ImportDeclarations
//
//				new Rule("CompilationUnit", "TypeDeclaration_Repeat"),
//				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "TypeDeclaration_Repeat"),
//				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "TypeDeclaration_Repeat"),
//				// CompilationUnit -> TypeDeclaration_Repeat
//				// CompilationUnit -> package QualifiedIdentifier ; TypeDeclaration_Repeat
//				// CompilationUnit -> Annotations package QualifiedIdentifier ; TypeDeclaration_Repeat
//
//				new Rule("CompilationUnit", "ImportDeclaration_Repeat", "TypeDeclaration_Repeat"),
//				new Rule("CompilationUnit", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat",
//						"TypeDeclaration_Repeat"),
//				new Rule("CompilationUnit", "Annotations", "package", "QualifiedIdentifier", ";", "ImportDeclaration_Repeat",
//						"TypeDeclaration_Repeat"),
//				// CompilationUnit -> ImportDeclaration_Repeat TypeDeclaration_Repeat
//				// CompilationUnit -> package QualifiedIdentifier ; ImportDeclaration_Repeat
//				// TypeDeclaration_Repeat
//				// CompilationUnit -> Annotations package QualifiedIdentifier ;
//				// ImportDeclarations TypeDeclaration_Repeat
//
//
//				new Rule("ImportDeclaration", "import", "Identifier", ";"),
//				new Rule("ImportDeclaration", "import", "static", "Identifier", ";"),
//				new Rule("ImportDeclaration", "import", "Identifier", ".", "*", ";"),
//				new Rule("ImportDeclaration", "import", "static", "Identifier", ".", "*", ";"),
//				new Rule("ImportDeclaration", "import", "Identifier", "PeriodIdentifier_Repeat", ";"),
//				new Rule("ImportDeclaration", "import", "static", "Identifier", "PeriodIdentifier_Repeat", ";"),
//				new Rule("ImportDeclaration", "import", "Identifier", "PeriodIdentifier_Repeat", ".", "*", ";"),
//				new Rule("ImportDeclaration", "import", "static", "Identifier", "PeriodIdentifier_Repeat", ".", "*", ";"),
//				// ImportDeclaration -> import Identifier ;
//				// ImportDeclaration -> import static Identifier ;
//				// ImportDeclaration -> import Identifier . * ;
//				// ImportDeclaration -> import static Identifier . * ;
//				// ImportDeclaration -> import Identifier PeriodIdentifier_Repeat ;
//				// ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat ;
//				// ImportDeclaration -> import Identifier PeriodIdentifier_Repeat . * ;
//				// ImportDeclaration -> import static Identifier PeriodIdentifier_Repeat . * ;
//
//				new Rule("TypeDeclaration", "ClassOrInterfaceDeclaration"), new Rule("TypeDeclaration", ";"),
//				// TypeDeclaration -> ClassOrInterfaceDeclaration
//				// TypeDeclaration -> ;
//				
//				new Rule("ClassOrInterfaceDeclaration", "ClassDeclaration"),
//				new Rule("ClassOrInterfaceDeclaration", "InterfaceDeclaration"),
//				new Rule("ClassOrInterfaceDeclaration", "Modifier_Repeat", "ClassDeclaration"),
//				new Rule("ClassOrInterfaceDeclaration", "Modifier_Repeat", "InterfaceDeclaration"),
//				// ClassOrInterfaceDeclaration -> ClassDeclaration
//				// ClassOrInterfaceDeclaration -> InterfaceDeclaration
//				// ClassOrInterfaceDeclaration -> Modifiers ClassDeclaration
//				// ClassOrInterfaceDeclaration -> Modifiers InterfaceDeclaration
//				
//				new Rule("ClassDeclaration", "NormalClassDeclaration"),
//				new Rule("ClassDeclaration", "EnumDeclaration"),
//				// ClassDeclaration -> NormalClassDeclaration
//				// ClassDeclaration -> EnumDeclaration
//				
//				new Rule("InterfaceDeclaration", "NormalInterfaceDeclaration"),
//				new Rule("InterfaceDeclaration", "AnnotationTypeDeclaration"),
//				// InterfaceDeclaration -> NormalInterfaceDeclaration
//				// InterfaceDeclaration -> AnnotationTypeDeclaration
//				
//				new Rule("NormalClassDeclaration", "class", "Identifier", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "extends", "Type", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "extends", "Type", "ClassBody"),
//				// NormalClassDeclaration -> class Identifier ClassBody
//				// NormalClassDeclaration -> class Identifier TypeParameters ClassBody
//				// NormalClassDeclaration -> class Identifier extends Type ClassBody
//				// NormalClassDeclaration -> class Identifier TypeParameters extends Type ClassBody
//
//				new Rule("NormalClassDeclaration", "class", "Identifier", "implements", "TypeList", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "implements", "TypeList", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "extends", "Type", "implements", "TypeList", "ClassBody"),
//				new Rule("NormalClassDeclaration", "class", "Identifier", "TypeParameters", "extends", "Type", "implements", "TypeList", "ClassBody"),
//				// NormalClassDeclaration -> class Identifier implements TypeList ClassBody
//				// NormalClassDeclaration -> class Identifier TypeParameters implements TypeList ClassBody
//				// NormalClassDeclaration -> class Identifier extends Type implements TypeList ClassBody
//				// NormalClassDeclaration -> class Identifier TypeParameters extends Type implements TypeList ClassBody
//				
//				new Rule("EnumDeclaration", "enum", "Identifier", "EnumBody"),
//				new Rule("EnumDeclaration", "enum", "implements", "TypeList", "Identifier", "EnumBody"),
//				// EnumDeclaration -> enum Identifier EnumBody
//				// EnumDeclaration -> enum Identifier implements TypeList EnumBody
//				
//				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "InterfaceBody"),
//				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "TypeParameters", "InterfaceBody"),
//				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "extends", "TypeList", "InterfaceBody"),
//				new Rule("NormalInterfaceDeclaration", "interface", "Identifier", "TypeParameters", "extends", "TypeList", "InterfaceBody"),
//				// NormalInterfaceDeclaration -> interface Identifier InterfaceBody
//				// NormalInterfaceDeclaration -> interface Identifier TypeParameters InterfaceBody
//				// NormalInterfaceDeclaration -> interface Identifier extends TypeList InterfaceBody
//				// NormalInterfaceDeclaration -> interface Identifier TypeParameters extends TypeList InterfaceBody
//				
//				new Rule("AnnotationTypeDeclaration", "@", "interface", "Identifier", "AnnotationTypeBody"),
//				// AnnotationTypeDeclaration -> @ interface Identifier AnnotationTypeBody
//				
//				new Rule("Type", "BasicType"),
//				new Rule("Type", "BasicType", "_EmptySquareBrackets_Repeat"),
//				new Rule("Type", "ReferenceType"),
//				new Rule("Type", "ReferenceType", "_EmptySquareBrackets_Repeat"),
//				// Type -> BasicType
//				// Type -> BasicType _EmptySquareBrackets_Repeat
//				// Type -> ReferenceType
//				// Type -> ReferenceType _EmptySquareBrackets_Repeat
//				
//				
//				new Rule("BasicType", "byte"),
//				new Rule("BasicType", "short"),
//				new Rule("BasicType", "char"),
//				new Rule("BasicType", "int"),
//				new Rule("BasicType", "long"),
//				new Rule("BasicType", "float"),
//				new Rule("BasicType", "double"),
//				new Rule("BasicType", "boolean"),
//				// BasicType -> byte
//				// BasicType -> short
//				// BasicType -> char
//				// BasicType -> int
//				// BasicType -> long
//				// BasicType -> float
//				// BasicType -> double
//				// BasicType -> boolean
//				
//				new Rule("ReferenceType", "Identifier"),
//				new Rule("ReferenceType", "Identifier", "TypeArguments"),
//				new Rule("ReferenceType", "Identifier", "_PeriodIdentifierTypeArgument_Repeat"),
//				new Rule("ReferenceType", "Identifier", "TypeArguments", "_PeriodIdentifierTypeArgument_Repeat"),
//				// ReferenceType -> Identifier
//				// ReferenceType -> Identifier TypeArguments
//				// ReferenceType -> Identifier _PeriodIdentifierTypeArgument_Repeat
//				// ReferenceType -> Identifier TypeArguments _PeriodIdentifierTypeArgument_Repeat
//
//				new Rule("TypeArguments", "<", "TypeArgument", ">"),
//				new Rule("TypeArguments", "<", "TypeArgument", "_CommaTypeArgument_Repeat", ">"),
//				// TypeArguments -> < TypeArgument >
//				// TypeArguments -> < TypeArgument _CommaTypeArgument_Repeat >
//				
//				
//				new Rule("TypeArgument", "ReferenceType"),
//				new Rule("TypeArgument", "? extends ReferenceType"),
//				new Rule("TypeArgument", "? super ReferenceType"),
//				// TypeArgument -> ReferenceType
//				// TypeArgument -> ?
//				// TypeArgument -> ? extends ReferenceType
//				// TypeArgument -> ? super ReferenceType
//				
//				
//				
//				new Rule("NonWildcardTypeArguments", "<", "TypeList", ">"),
//				// NonWildcardTypeArguments -> < TypeList >
//				
//				
//				new Rule("TypeList", "ReferenceType"),
//				new Rule("TypeList", "ReferenceType", "_CommaReferenceType_Repeat"),
//				// TypeList -> ReferenceType
//				// TypeList -> ReferenceType _CommaReferenceType_Repeat
//				
//				
//				new Rule("TypeArgumentsOrDiamond", "<", ">"),
//				new Rule("TypeArgumentsOrDiamond", "TypeArguments"),
//				// TypeArgumentsOrDiamond -> < >
//				// TypeArgumentsOrDiamond -> TypeArguments
//				
//				
//				
//				new Rule("NonWildcardTypeArgumentsOrDiamond", "<", ">"),
//				new Rule("NonWildcardTypeArgumentsOrDiamond", "NonWildcardTypeArguments"),
//				// NonWildcardTypeArgumentsOrDiamond -> < >
//				// NonWildcardTypeArgumentsOrDiamond -> NonWildcardTypeArguments
//				
//				
//				new Rule("TypeParameters", "<", "TypeParameter", ">"),
//				new Rule("TypeParameters", "<", "TypeParameter", "_CommaTypeParameter_Repeat", ">"),
//				// TypeParameters -> < TypeParameter >
//				// TypeParameters -> < TypeParameter _CommaTypeParameter_Repeat >
//				
//				
//				
//				new Rule("TypeParameter", "Identifier"),
//				new Rule("TypeParameter", "Identifier", "extends", "Bound"),
//				// TypeParameter -> Identifier
//				// TypeParameter -> Identifier extends Bound
//				
//				
//				new Rule("Bound", "ReferenceType"),
//				new Rule("Bound", "ReferenceType", "_AndReferenceType_Repeat"),
//				// Bound -> ReferenceType
//				// Bound -> ReferenceType _AndReferenceType_Repeat
//				
//				new Rule("Modifier", "Annotation"),
//				new Rule("Modifier", "public"),
//				new Rule("Modifier", "protected"),
//				new Rule("Modifier", "private"),
//				new Rule("Modifier", "static"),
//				new Rule("Modifier", "abstract"),
//				new Rule("Modifier", "final"),
//				new Rule("Modifier", "native"),
//				new Rule("Modifier", "synchronized"),
//				new Rule("Modifier", "transient"),
//				new Rule("Modifier", "volatile"),
//				new Rule("Modifier", "strictfp"),
//				// Modifier -> Annotation
//				// Modifier -> public
//				// Modifier -> protected
//				// Modifier -> private
//				// Modifier -> static
//				// Modifier -> abstract
//				// Modifier -> final
//				// Modifier -> native
//				// Modifier -> synchronized
//				// Modifier -> transient
//				// Modifier -> volatile
//				// Modifier -> strictfp
//				
//				
//				new Rule("Annotations", "Annotaion"),
//				new Rule("Annotations", "Annotaion", "_Annotation_Repeat"),
//				// Annotations -> Annotation _Annotation_Repeat
//				// Annotations -> _Annotation_Repeat
//				
//				
//				new Rule("Annotation", "@", "QualifiedIdentifier"),
//				new Rule("Annotation", "@", "QualifiedIdentifier", "(", ")"),
//				new Rule("Annotation", "@", "QualifiedIdentifier", "(", "AnnotationElement", ")"),
//				// Annotation -> @ QualifiedIdentifier
//				// Annotation -> @ QualifiedIdentifier ( )
//				// Annotation -> @ QualifiedIdentifier ( AnnotationElement )
//				
//				
//				new Rule("AnnotationElement ", "ElementValuePairs"),
//				new Rule("AnnotationElement ", "ElementValue"),
//				// AnnotationElement -> ElementValuePairs
//				// AnnotationElement -> ElementValue
//				
//				new Rule("ElementValuePairs", "ElementValuePair"),
//				new Rule("ElementValuePairs", "ElementValuePair", "_CommaElementValuePair_Repeat"),
//				// ElementValuePairs -> ElementValuePair
//				// ElementValuePairs -> ElementValuePair _CommaElementValuePair_Repeat
//				
//				new Rule("ElementValuePair", "Identifier", "=", "ElementValue"),
//				// ElementValuePair -> Identifier = ElementValue
//				
//				
//				new Rule("ElementValue", "Annotation"),
//				new Rule("ElementValue", "Expression1"),
//				new Rule("ElementValue", "ElementValueArrayInitializer"),
//				// ElementValue -> Annotation
//				// ElementValue -> Expression1
//				// ElementValue -> ElementValueArrayInitializer
//				
//				new Rule("ElementValueArrayInitializer"),
//				new Rule("ElementValueArrayInitializer", "_ElementValuesComma_Repeat"),
//				// ElementValueArrayInitializer ->
//				// ElementValueArrayInitializer -> _ElementValuesComma_Repeat
//				
//				new Rule("ElementValues", "ElementValue"),
//				new Rule("ElementValues", "ElementValue", "_CommaElementValue_Repeat"),
//				// ElementValues -> ElementValue
//				// ElementValues -> ElementValue _CommaElementValue_Repeat
//				
//				new Rule("ClassBody", "{", "}"),
//				new Rule("ClassBody", "{", "ClassBodyDeclaration_Repeat", "}"),
//				// ClassBody -> { }
//				// ClassBody -> { ClassBodyDeclaration_Repeat }
//				
//				new Rule("ClassBodyDeclaration", ";"),
//				new Rule("ClassBodyDeclaration", "MemberDecl"),
//				new Rule("ClassBodyDeclaration", "Modifier_Repeat", "MemberDecl"),
//				new Rule("ClassBodyDeclaration", "Block"),
//				new Rule("ClassBodyDeclaration", "static", "Block"),
//				// ClassBodyDeclaration -> ;
//				// ClassBodyDeclaration -> MemberDecl
//				// ClassBodyDeclaration -> Modifier_Repeat MemberDecl
//				// ClassBodyDeclaration -> Block
//				// ClassBodyDeclaration -> static Block
//				
//				new Rule("MemberDecl", "MethodOrFieldDecl"),
//				new Rule("MemberDecl", "void", "Identifier", "VoidMethodDeclaratorRest"),
//				new Rule("MemberDecl", "Identifier", "ConstructorDeclaratorRest"),
//				new Rule("MemberDecl", "GenericMethodOrConstructorDecl"),
//				new Rule("MemberDecl", "ClassDeclaration"),
//				new Rule("MemberDecl", "InterfaceDeclaration"),
//				// MemberDecl -> MethodOrFieldDecl
//				// MemberDecl -> void Identifier VoidMethodDeclaratorRest
//				// MemberDecl -> Identifier ConstructorDeclaratorRest
//				// MemberDecl -> GenericMethodOrConstructorDecl
//				// MemberDecl -> ClassDeclaration
//				// MemberDecl -> InterfaceDeclaration
//				
//				new Rule("MethodOrFieldDecl", "Type", "Identifier", "MethodOrFieldRest"),
//				// MethodOrFieldDecl -> Type Identifier MethodOrFieldRest
//				
//				new Rule("MethodOrFieldRest", "FIeldDeclaratorsRest", ";"),
//				new Rule("MethodOrFieldRest", "MethodDeclaratorsRest"),
//				// MethodOrFieldRest -> FieldDeclaratorsRest ;
//				// MethodOrFieldRest -> MethodDeclaratorRest
//				
//				new Rule("FieldDeclaratorsRest", "VariableDeclaratorRest"),
//				new Rule("FieldDeclaratorsRest", "VariableDeclaratorRest", "CommaVariableDeclarator_Repeat"),
//				// FieldDeclaratorsRest -> VariableDeclaratorRest
//				// FieldDeclaratorsRest -> VariableDeclaratorRest CommaVariableDeclarator_Repeat
//				
//				new Rule("MethodDeclaratorRest", "FormalParameters", "_EmptySquareBrackets_Repeat", "Block"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "_EmptySquareBrackets_Repeat", ";"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierList", "_EmptySquareBrackets_Repeat", "Block"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierList", "_EmptySquareBrackets_Repeat", ";"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "Block"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", ";"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierList", "Block"),
//				new Rule("MethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierList", ";"),
//				// MethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat Block
//				// MethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat ;
//				// MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList _EmptySquareBrackets_Repeat Block
//				// MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList _EmptySquareBrackets_Repeat ;
//				// MethodDeclaratorRest -> FormalParameters Block
//				// MethodDeclaratorRest -> FormalParameters ;
//				// MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList Block
//				// MethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;
//				
//				new Rule("VoidMethodDeclaratorRest", "FormalParameters", "Block"),
//				new Rule("VoidMethodDeclaratorRest", "FormalParameters", ";"),
//				new Rule("VoidMethodDeclaratorRest", "throws", "QualifiedIdentifierList", "FormalParameters", "Block"),
//				new Rule("VoidMethodDeclaratorRest", "throws", "QualifiedIdentifierList", "FormalParameters", ";"),
//				// VoidMethodDeclaratorRest -> FormalParameters Block
//				// VoidMethodDeclaratorRest -> FormalParameters ;
//				// VoidMethodDeclaratorRest -> throws QualifiedIdentifierList FormalParameters Block
//				// VoidMethodDeclaratorRest -> throws QualifiedIdentifierList FormalParameters ;
//				
//				new Rule("ConstructorDeclaratorRest", "FormalParameters", "Block"),
//				new Rule("ConstructorDeclaratorRest", "throws", "QualifiedIdentifierList", "FormalParameters", "Block"),
//				// ConstructorDeclaratorRest -> FormalParameters Block
//				// ConstructorDeclaratorRest -> FormalParameters throws QualifiedIdentifierList Block
//				
//				new Rule("GenericMethodOrConstructorDecl", "TypeParameters", "GenericMethodOrConstructorRest"),
//				// GenericMethodOrConstructorDecl -> TypeParameters GenericMethodOrConstructorRest
//				
//				new Rule("GenericMethodOrConstructorRest", "Type", "Identifier", "MethodDeclaratorRest"),
//				new Rule("GenericMethodOrConstructorRest", "void", "Identifier", "MethodDeclaratorRest"),
//				new Rule("GenericMethodOrConstructorRest", "Identifier", "ConstructorDeclaratorRest"),
//				// GenericMethodOrConstructorRest -> Type Identifier MethodDeclaratorRest
//				// GenericMethodOrConstructorRest -> void Identifier MethodDeclaratorRest
//				// GenericMethodOrConstructorRest -> Identifier ConstructorDeclaratorRest
//				
//				new Rule("InterfaceBody", "{", "}"),
//				new Rule("InterfaceBody", "{", "_InterfaceBodyDeclaration_Repeat", "}"),
//				// InterfaceBody -> { }
//				// InterfaceBody -> { _InterfaceBodyDeclaration_Repeat }
//				
//				new Rule("InterfaceBodyDeclaration", ";"),
//				new Rule("InterfaceBodyDeclaration", "InterfaceMemberDecl"),
//				new Rule("InterfaceBodyDeclaration", "Modifier_Repeat", "InterfaceMemberDecl"),
//				// InterfaceBodyDeclaration -> ;
//				// InterfaceBodyDeclaration -> InterfaceMemberDecl
//				// InterfaceBodyDeclaration -> Modifier_Repeat InterfaceMemberDecl
//				
//				new Rule("InterfaceMemberDecl", "InterfaceMethodOrFieldDecl"),
//				new Rule("InterfaceMemberDecl", "void", "Identifier", "VoidInterfaceMethodDeclaratorRest"),
//				new Rule("InterfaceMemberDecl", "InterfaceGenericMethodDecl"),
//				new Rule("InterfaceMemberDecl", "InterfaceDeclaration"),
//				// InterfaceMemberDecl -> InterfaceMethodOrFieldDecl
//				// InterfaceMemberDecl -> void Identifier VoidInterfaceMethodDeclaratorRest
//				// InterfaceMemberDecl -> InterfaceGenericMethodDecl
//				// InterfaceMemberDecl -> ClassDeclaration
//				// InterfaceMemberDecl -> InterfaceDeclaration
//				
//				new Rule("InterfaceMethodOrFieldDecl", "Type", "Identifier", "InterfaceMethodOrFieldRest"),
//				// InterfaceMethodOrFieldDecl -> Type Identifier InterfaceMethodOrFieldRest
//				
//				new Rule("InterfaceMethodOrFieldRest", "ConstantDeclaratorsRest", ";"),
//				new Rule("InterfaceMethodOrFieldRest", "InterfaceMethodDeclaratorRest"),
//				// InterfaceMethodOrFieldRest -> ConstantDeclaratorsRest ;
//				// InterfaceMethodOrFieldRest -> InterfaceMethodDeclaratorRest
//				
//				new Rule("ConstantDeclaratorsRest", "ConstantDeclaratorRest"),
//				new Rule("ConstantDeclaratorsRest", "ConstantDeclaratorRest", "_CommaConstantDeclarator_Repeat"),
//				// ConstantDeclaratorsRest -> ConstantDeclaratorRest
//				// ConstantDeclaratorsRest -> ConstantDeclaratorRest _CommaConstantDeclarator_Repeat
//				
//				new Rule("ConstantDeclaratorRest", "=", "VariableInitializer"),
//				new Rule("ConstantDeclaratorRest", "_EmptySquareBrackets_Repeat", "=", "VariableInitializer"),
//				// ConstantDeclaratorRest -> = VariableInitializer
//				// ConstantDeclaratorRest -> _EmptySquareBrackets_Repeat = VariableInitializer
//				
//				new Rule("ConstantDeclarator", "Identifier", "ConstantDeclaratorRest"), 
//				// ConstantDeclarator -> Identifier ConstantDeclaratorRest
//				
//				new Rule("InterfaceMethodDeclaratorRest", "FormalParameters", ";"),
//				new Rule("InterfaceMethodDeclaratorRest", "FormalParameters", "_EmptySquareBrackets_Repeat", ";"),
//				new Rule("InterfaceMethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierListt", ";"),
//				new Rule("InterfaceMethodDeclaratorRest", "FormalParameters", "_EmptySquareBrackets_Repeat", "throws", "QualifiedIdentifierListt", ";"),
//				// InterfaceMethodDeclaratorRest -> FormalParameters ;	
//				// InterfaceMethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat ;
//				// InterfaceMethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;
//				// InterfaceMethodDeclaratorRest -> FormalParameters _EmptySquareBrackets_Repeat throws QualifiedIdentifierListt ;
//				
//				new Rule("VoidInterfaceMethodDeclaratorRest", "FormalParameters", ";"),
//				new Rule("VoidInterfaceMethodDeclaratorRest", "FormalParameters", "throws", "QualifiedIdentifierList", ";"),
//				// VoidInterfaceMethodDeclaratorRest -> FormalParameters ;  
//				// VoidInterfaceMethodDeclaratorRest -> FormalParameters throws QualifiedIdentifierList ;  
//				
//				new Rule("InterfaceGenericMethodDecl", "TypeParameters", "Type", "Identifier", "InterfaceMethodDeclaratorRest"),
//				// InterfaceGenericMethodDecl -> TypeParameters Type Identifier InterfaceMethodDeclaratorRest
//				// InterfaceGenericMethodDecl -> TypeParameters void Identifier InterfaceMethodDeclaratorRest
//				
//				new Rule("FormalParameters", "(", ")"),
//				new Rule("FormalParameters", "(", "FormalParameterDecls", ")"),
//				// FormalParameters -> ( )
//				// FormalParameters -> ( FormalParameterDecls )
//				
//				new Rule("FormalParameterDecls", "Type", "FormalParameterDeclsRest"),
//				new Rule("FormalParameterDecls", "_VariableModifier_Repeat", "Type", "FormalParameterDeclsRest"),
//				// FormalParameterDecls -> Type FormalParameterDeclsRest
//				// FormalParameterDecls -> _VariableModifier_Repeat Type FormalParameterDeclsRest
//				
//				new Rule("VariableModifier", "final"),
//				new Rule("VariableModifier", "Annotation"),
//				// VariableModifier -> final
//				// VariableModifier -> Annotation
//				
//				new Rule("FormalParameterDeclsRest", "VariableDeclaratorId"),
//				new Rule("FormalParameterDeclsRest", "VariableDeclaratorId", ",", "FormalParameterDecls"),
//				new Rule("FormalParameterDeclsRest", "...", "VariableDeclaratorId"),
//				// FormalParameterDeclsRest -> VariableDeclaratorId
//				// FormalParameterDeclsRest -> VariableDeclaratorId , FormalParameterDecls
//				// FormalParameterDeclsRest -> ... VariableDeclaratorId
//				
//				new Rule("VariableDeclaratorId", "Identifier"),
//				new Rule("VariableDeclaratorId", "_EmptySquareBrackets_Repeat", "Identifier"),
//				// VariableDeclaratorId -> Identifier
//				// VariableDeclaratorId -> _EmptySquareBrackets_Repeat Identifier
//				
//				new Rule("VariableDeclarators", "VariableDeclarator"),
//				new Rule("VariableDeclarators", "VariableDeclarator", "_CommaVariableDeclarator_Repeat"),
//				// VariableDeclarators -> VariableDeclarator
//				// VariableDeclarators -> VariableDeclarator _CommaVariableDeclarator_Repeat
//				
//				new Rule("VariableDeclarator", "Identifier", "VariableDeclaratorRest"),
//				// VariableDeclarator -> Identifier VariableDeclaratorRest
//				
//				new Rule("VariableDeclaratorRest"),
//				new Rule("VariableDeclaratorRest", "_EmptySquareBrackets_Repeat"),
//				new Rule("VariableDeclaratorRest", "=", "VariableInitializer"),
//				new Rule("VariableDeclaratorRest", "_EmptySquareBrackets_Repeat", "=", "VariableInitializer"),
//				// VariableDeclaratorRest -> 
//				// VariableDeclaratorRest -> _EmptySquareBrackets_Repeat
//				// VariableDeclaratorRest -> = VariableInitializer
//				// VariableDeclaratorRest -> _EmptySquareBrackets_Repeat = VariableInitializer
//				
//				new Rule("VariableInitializer", "ArrayInitializer"),
//				new Rule("VariableInitializer", "Expression"),
//				// VariableInitializer -> ArrayInitializer
//				// VariableInitializer -> Expression
//				
//				new Rule("ArrayInitializer"),
//				new Rule("ArrayInitializer", "_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat"),
//				// ArrayInitializer -> 
//				// ArrayInitializer -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat
//				
//				new Rule("Block", "{", "BlockStatements", "}"),
//				// Block -> { BlockStatements }
//				
//				new Rule("BlockStatements"),
//				new Rule("BlockStatements", "_BlockStatement_Repeat"),
//				// BlockStatements ->
//				// BlockStatements -> _BlockStatement_Repeat
//				
//				new Rule("BlockStatement", "LocalVariableDeclarationStatement"),
//				new Rule("BlockStatement", "ClassOrInterfaceDeclaration"),
//				new Rule("BlockStatement", "Statement"),
//				new Rule("BlockStatement", "Identifier", ":", "Statement"),
//				// BlockStatement -> LocalVariableDeclarationStatement
//				// BlockStatement -> ClassOrInterfaceDeclaration
//				// BlockStatement -> Statement
//				// BlockStatement -> Identifier : Statement
//				
//				new Rule("LocalVariableDeclarationStatement", "Type", "VariableDeclarators", ";"),
//				new Rule("LocalVariableDeclarationStatement", "_VariableModifier_Repeat", "Type", "VariableDeclarators", ";"),
//				// LocalVariableDeclarationStatement -> Type VariableDeclarators ;
//				// LocalVariableDeclarationStatement -> _VariableModifier_Repeat Type VariableDeclarators ;
//				
//				new Rule("Statement", "Block"),
//				new Rule("Statement", "Identifier", ":", "Statement"),
//				new Rule("Statement", "StatementExpression", ";"),
//				new Rule("Statement", "if", "ParExpression", "Statement"),
//				new Rule("Statement", "if", "ParExpression", "Statement", "else", "Statement"),
//				new Rule("Statement", "assert", "Expression", ";"),
//				new Rule("Statement", "assert", "Expression", ":", "Expression", ";"),
//				new Rule("Statement", "switch", "ParExpression", "{", "SwitchBlockStatementGroups", "}"),
//				new Rule("Statement", "while", "ParExpression", "Statement"),
//				new Rule("Statement", "do", "Statement", "while", "ParExpression", ";"),
//				new Rule("Statement", "for", "(", "ForControl", ")", "Statement"),
//				new Rule("Statement", "break", ";"),
//				new Rule("Statement", "break", "Identifier", ";"),
//				new Rule("Statement", "continue", ";"),
//				new Rule("Statement", "continue", "Identifier", ";"),
//				new Rule("Statement", "return", ";"),
//				new Rule("Statement", "return", "Expression", ";"),
//				new Rule("Statement", "throw", "Expression", ";"),
//				new Rule("Statement", "synchronized", "ParExpression", "Block"),
//				new Rule("Statement", "try", "Block", "Catches"),
//				new Rule("Statement", "try", "Block", "Finally"),
//				new Rule("Statement", "try", "Block", "Catches", "Finally"),
//				new Rule("Statement", "try", "ResourceSpecification", "Block"),
//				new Rule("Statement", "try", "ResourceSpecification", "Block", "Catches"),
//				new Rule("Statement", "try", "ResourceSpecification", "Block", "Finally"),
//				new Rule("Statement", "try", "ResourceSpecification", "Block", "Catches", "Finally"),
//				// Statement -> Block
//				// Statement -> Identifier : Statement
//				// Statement -> StatementExpression ;
//				// Statement -> if ParExpression Statement
//				// Statement -> if ParExpression Statement else Statement
//				// Statement -> assert Expression ;
//				// Statement -> assert Expression : Expression ;
//				// Statement -> switch ParExpression { SwitchBlockStatementGroups } 
//				// Statement -> while ParExpression Statement
//				// Statement -> do Statement while ParExpression ;
//				// Statement -> for ( ForControl ) Statement
//				// Statement -> break ;
//				// Statement -> break Identifier ;
//				// Statement -> continue ;
//				// Statement -> continue Identifier ;
//				// Statement -> return ;
//				// Statement -> return Expression ;
//				// Statement -> throw Expression ;
//				// Statement -> synchronized ParExpression Block
//				// Statement -> try Block Catches
//				// Statement -> try Block Finally
//				// Statement -> try Block Catches Finally
//				// Statement -> try ResourceSpecification Block
//				// Statement -> try ResourceSpecification Block Catches
//				// Statement -> try ResourceSpecification Block Finally
//				// Statement -> try ResourceSpecification Block Catches Finally
//				
//				new Rule("StatementExpression", "Expression"),
//				// StatementExpression -> Expression
//				
//				// Original Rule
//				
//				new Rule("PeriodIdentifier_Repeat", ".", "Identifier"),
//				new Rule("PeriodIdentifier_Repeat", "PeriodIdentifier_Repeat", ".", "Identifier"),
//				// QualifiedIdentifier -> Identifier
//				// QualifiedIdentifier -> QualifiedIdentifier . Identifier
//
//				new Rule("ImportDeclaration_Repeat", "ImportDeclaration"),
//				new Rule("ImportDeclaration_Repeat", "ImportDeclaration_Repeat", "ImportDeclaration"),
//				// ImportDeclarations -> ImportDeclaration
//				// ImportDeclarations -> ImportDeclarations ImportDeclaration
//
//				new Rule("TypeDeclaration_Repeat", "TypeDeclaration"),
//				new Rule("TypeDeclaration_Repeat", "TypeDeclaration_Repeat", "TypeDeclaration"),
//				// TypeDeclarations -> TypeDeclaration
//				// TypeDeclarations -> TypeDeclarations TypeDeclaration
//
//				new Rule("Modifier_Repeat", "Modifier"),
//				new Rule("Modifier_Repeat", "Modifier_Repeat", "Modifier"),
//				// Modifiers -> Modifier
//				// Modifiers -> Modifiers Modifier
//				
//				new Rule("_PeriodIdentifierTypeArgument_Repeat", ".", "Identifier"),
//				new Rule("_PeriodIdentifierTypeArgument_Repeat", ".", "Identifier", "TypeArguments"),
//				// _PeriodIdentifierTypeArgument_Repeat -> . Identifier 
//				// _PeriodIdentifierTypeArgument_Repeat -> . Identifier TypeArguments
//				
//				new Rule("_EmptySquareBrackets_Repeat", "[", "]"),
//				new Rule("_EmptySquareBrackets_Repeat", "_EmptySquareBrackets_Repeat", "[", "]"),
//				// _EmptySquareBrackets_Repeat -> []
//				// _EmptySquareBrackets_Repeat -> _EmptySquareBrackets_Repeat []
//				
//				new Rule("_CommaTypeArgument_Repeat", ",", "TypeArgument"),
//				new Rule("_CommaTypeArgument_Repeat", "_CommaTypeArgument_Repeat", ",", "TypeArgument"),
//				// _CommaTypeArgument_Repeat -> , TypeArgument
//				// _CommaTypeArgument_Repeat -> _CommaTypeArgument_Repeat , TypeArgument
//				
//				new Rule("_CommaReferenceType_Repeat", "ReferenceType"),
//				new Rule("_CommaReferenceType_Repeat", "_CommaReferenceType_Repeat", ",", "ReferenceType"),
//				// _CommaReferenceType_Repeat -> , ReferenceType
//				// _CommaReferenceType_Repeat -> _CommaReferenceType_Repeat , ReferenceType
//				
//				new Rule("_CommaTypeParameter_Repeat", ",", "TypeParameter"),
//				new Rule("_CommaTypeParameter_Repeat", "_CommaTypeParameter_Repeat", ",", "TypeParameter"),
//				// _CommaTypeParameter_Repeat -> , TypeParameter
//				// _CommaTypeParameter_Repeat -> _CommaTypeParameter_Repeat , TypeParameter
//
//				new Rule("_AndReferenceType_Repeat", "&", "ReferenceType"),
//				new Rule("_AndReferenceType_Repeat", "_AndReferenceType_Repeat", "&", "ReferenceType"),
//				// _AndReferenceType_Repeat -> & ReferenceType
//				// _AndReferenceType_Repeat -> _AndReferenceType_Repeat & ReferenceType
//				
//				new Rule("_Annotation_Repeat", "Annotation"),
//				new Rule("_Annotation_Repeat", "_Annotation_Repeat", "Annotation"),
//				// _Annotation_Repeat -> Annotation
//				// _Annotation_Repeat -> _Annotation_Repeat Annotation
//
//				new Rule("_CommaElementValuePair_Repeat", ",", "ElementValuePair"),
//				new Rule("_CommaElementValuePair_Repeat", "_CommaElementValuePair_Repeat", ",", "ElementValuePair"),
//				// _CommaElementValuePair_Repeat -> , ElementValuePair
//				// _CommaElementValuePair_Repeat -> _CommaElementValuePair_Repeat , ElementValuePair
//				
//				new Rule("_ElementValuesComma_Repeat"),
//				new Rule("_ElementValuesComma_Repeat", "ElementValues"),
//				new Rule("_ElementValuesComma_Repeat", ","),
//				new Rule("_ElementValuesComma_Repeat", "ElementValues", ","),
//				new Rule("_ElementValuesComma_Repeat", "_ElementValuesComma_Repeat", "ElementValues"),
//				new Rule("_ElementValuesComma_Repeat", "_ElementValuesComma_Repeat", ","),
//				new Rule("_ElementValuesComma_Repeat", "_ElementValuesComma_Repeat", "ElementValues", ","),
//				// _ElementValuesComma_Repeat -> 
//				// _ElementValuesComma_Repeat -> ElementValues
//				// _ElementValuesComma_Repeat -> ,
//				// _ElementValuesComma_Repeat -> ElementValues ,
//				// _ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ElementValues
//				// _ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ,
//				// _ElementValuesComma_Repeat -> _ElementValuesComma_Repeat ElementValues ,
//
//				new Rule("_CommaElementValue_Repeat", "ElementValue"),
//				new Rule("_CommaElementValue_Repeat", "_CommaElementValue_Repeat", ",", "ElementValue"),
//				// _CommaElementValue_Repeat -> , ElementValue
//				// _CommaElementValue_Repeat -> _CommaElementValue_Repeat , ElementValue
//
//				
//				new Rule("ClassBodyDeclaration_Repeat", "ClassBodyDeclaration"),
//				new Rule("ClassBodyDeclaration_Repeat", "ClassBodyDeclaration_Repeat", "ClassBodyDeclaration"),
//				// ClassBodyDeclaration_Repeat -> ClassBodyDeclaration
//				// ClassBodyDeclaration_Repeat -> ClassBodyDeclaration_Repeat ClassBodyDeclaration
//				
//				
//				new Rule("CommaVariableDeclarator_Repeat", ",", "VariableDeclarator"),
//				new Rule("CommaVariableDeclarator_Repeat", "CommaVariableDeclarator_Repeat", ",", "VariableDeclarator"),
//				// CommaVariableDeclarator_Repeat -> , VariableDeclarator
//				// CommaVariableDeclarator_Repeat -> CommaVariableDeclarator_Repeat , VariableDeclarator
//				
//				
//				new Rule("_InterfaceBodyDeclaration_Repeat", "InterfaceBodyDeclaration"),
//				new Rule("_InterfaceBodyDeclaration_Repeat", "_InterfaceBodyDeclaration_Repeat", "InterfaceBodyDeclaration"),
//				// _InterfaceBodyDeclaration_Repeat -> InterfaceBodyDeclaration
//				// _InterfaceBodyDeclaration_Repeat -> _InterfaceBodyDeclaration_Repeat InterfaceBodyDeclaration
//				
//				
//				new Rule("_CommaConstantDeclarator_Repeat", ",", "ConstantDeclarator"),
//				new Rule("_CommaConstantDeclarator_Repeat", "_CommaConstantDeclarator_Repeat", ",", "ConstantDeclarator"),
//				// _CommaConstantDeclarator_Repeat -> , ConstantDeclarator
//				// _CommaConstantDeclarator_Repeat -> _CommaConstantDeclarator_Repeat , ConstantDeclarator
//				
//				new Rule("_VariableModifier_Repeat", "VariableModifier"),
//				new Rule("_VariableModifier_Repeat", "_VariableModifier_Repeat", "VariableModifier"),
//				// _VariableModifier_Repeat -> VariableModifier
//                // _VariableModifier_Repeat -> _VariableModifier_Repeat VariableModifier
//				
//				new Rule("_CommaVariableDeclarator_Repeat", ",", "VariableDeclarator"),
//				new Rule("_CommaVariableDeclarator_Repeat", "_CommaVariableDeclarator_Repeat", ",", "VariableDeclarator"),
//				// _CommaVariableDeclarator_Repeat -> , VariableDeclarator
//				// _CommaVariableDeclarator_Repeat -> _CommaVariableDeclarator_Repeat VariableDeclarator
//				
//				new Rule("_CommaVariableInitializer_Repeat", ",", "VariableInitializer"),
//				new Rule("_CommaVariableInitializer_Repeat", "_CommaVariableInitializer_Repeat", ",", "VariableInitializer"),
//				// _CommaVariableInitializer_Repeat -> , VariableInitializer
//				// _CommaVariableInitializer_Repeat -> _CommaVariableInitializer_Repeat , VariableInitializer
//				
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat"),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "VariableInitializer _CommaVariableInitializer_Repeat"),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", ","),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "VariableInitializer _CommaVariableInitializer_Repeat", ","),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "VariableInitializer _CommaVariableInitializer_Repeat"),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", ","),
//				new Rule("_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "_VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat", "VariableInitializer _CommaVariableInitializer_Repeat", ","),
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> 
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> VariableInitializer _CommaVariableInitializer_Repeat
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> ,
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> VariableInitializer _CommaVariableInitializer_Repeat ,
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat VariableInitializer _CommaVariableInitializer_Repeat
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat ,
//				// _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat -> _VariableInitializer_CommaVariableInitializer_Repeat_Comma_Repeat VariableInitializer _CommaVariableInitializer_Repeat ,
//				
//				
//
//                new Rule("_BlockStatement_Repeat", "BlockStatement"),
//                new Rule("_BlockStatement_Repeat", "_BlockStatement_Repeat", "BlockStatement"),
//                // _BlockStatement_Repeat -> BlockStatement
//                // _BlockStatement_Repeat -> _BlockStatement_Repeat BlockStatement
//                
//                
//
//                new Rule("_CatchClause_Repeat", "CatchClause"),
//                new Rule("_CatchClause_Repeat", "_CatchClause_Repeat", "CatchClause"),
//                // _CatchClause_Repeat -> CatchClause
//                // _CatchClause_Repeat -> _CatchClause_Repeat CatchClause
//                
//                
//                new Rule("_OrQualifiedIdentifier_Repeat", "|", "QualifiedIdentifier"),
//                new Rule("_OrQualifiedIdentifier_Repeat", "_OrQualifiedIdentifier_Repeat", "|", "QualifiedIdentifier"),
//                // _OrQualifiedIdentifier_Repeat -> | QualifiedIdentifier
//                // _OrQualifiedIdentifier_Repeat -> _OrQualifiedIdentifier_Repeat | QualifiedIdentifier
//				
//                new Rule("_SemicolonResource_Repeat", ";", "Resource"),
//                new Rule("_SemicolonResource_Repeat", "_SemicolonResource_Repeat", ";", "Resource"),
//                // _SemicolonResource_Repeat -> ; Resource
//                // _SemicolonResource_Repeat -> _SemicolonResource_Repeat ; Resource
//                
//                
//                new Rule("_SwitchBlockStatementGroup_Repeat", "SwitchBlockStatementGroup"),
//                new Rule("_SwitchBlockStatementGroup_Repeat", "_SwitchBlockStatementGroup_Repeat", "SwitchBlockStatementGroup"),
//                // _SwitchBlockStatementGroup_Repeat -> SwitchBlockStatementGroup
//                // _SwitchBlockStatementGroup_Repeat -> _SwitchBlockStatementGroup_Repeat SwitchBlockStatementGroup
//                
//                
//                new Rule("_SwitchLabel_Repeat", "SwitchLabel"),
//                new Rule("_SwitchLabel_Repeat", "_SwitchLabel_Repeat", "SwitchLabel")
//                // _SwitchLabel_Repeat -> SwitchLabel
//                // _SwitchLabel_Repeat -> _SwitchLabel_Repeat SwitchLabel
//
//		);
		
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
			   
				class Identifier { 
				   public static void Identifier ( Identifier [ ] Identifier ) {
				       Identifier . Identifier . Identifier ( ) ;
				   }
				}
				 
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
