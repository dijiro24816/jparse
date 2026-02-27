package jparse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jparse.java.JavaLexer;

/**
 * Hello world!
 */

public class App {
	public static void main(String[] args) throws IOException, InvalidTokenException, ClassNotFoundException {
		long start = System.currentTimeMillis();
		
		Grammar grammar;
		SyntaticsTable syntaticsTable;

		String fname = "syntaticsTable.ser";
		if (!new File(fname).exists()) {
			System.out.println("Syntatics Table Making ...");
			grammar = new Grammar("CompilationUnit", "$").resource(new FileInputStream("JavaSyntax.txt"));
			syntaticsTable = new SyntaticsTable(grammar);
			syntaticsTable.setup();

			syntaticsTable.serialize(new ObjectOutputStream(new FileOutputStream(fname)));
		} else {
			System.out.println("Syntatics Table Loading ...");
			
			syntaticsTable = SyntaticsTable.deserialize(new ObjectInputStream(new FileInputStream(fname)));
			grammar = syntaticsTable.getGrammar();
		}
		
		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");
		
		syntaticsTable.setup();

		System.out.println("actions.csv < ```");
		System.out.println(syntaticsTable.getActionsCSV());
		System.out.println("```");

		System.out.println("gotos.csv < ```");
		System.out.println(syntaticsTable.getGotosCSV());
		System.out.println("```");

		String sourceCode = """
				import hello.world;
				
				public class Hello {
					public static void myprint(String str) {
					    System.out.println(str);
					}
					
				    public static void main(String[] args) {
				        System.out.println(\"hell jasdklfjaskdlf jaslkdf o world\");
				        myprint();
				    }
				}
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
			Token symbol = lexerForPrint.getSymbol(inStrm);

			if (grammar.isEndSymbol(symbol)) {
				System.out.println(symbol);
				break;
			}

			System.out.println(symbol);
		}

		System.out.println("*** Parser Stack ***");
		Lexer lexer = new JavaLexer(grammar.getEndSymbol());
		Parser parser = new Parser(lexer, syntaticsTable);
		Token symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
		
		
		long end = System.currentTimeMillis();
		System.out.println((end - start)  + "ms");
	}

	public static void maina(String[] args) throws IOException, InvalidTokenException, ClassNotFoundException {
		long start = System.currentTimeMillis();
		
		String grammarSource = """
				Statement -> Expression
				Statement -> Assignment
				Expression -> Identifier
				Expression -> Expression + Expression
				Assignment -> Identifier = Expression
				""";

		Grammar grammar;
		SyntaticsTable syntaticsTable;

		String fname = "syntaticsTable.ser";
		if (!new File(fname).exists()) {
			System.out.println("Syntatics Table Making ...");
			grammar = new Grammar("Statement").resource(new ByteArrayInputStream(grammarSource.getBytes()));
			syntaticsTable = new SyntaticsTable(grammar);
			syntaticsTable.setup();

			syntaticsTable.serialize(new ObjectOutputStream(new FileOutputStream(fname)));
		} else {
			System.out.println("Syntatics Table Loading ...");
			
			syntaticsTable = SyntaticsTable.deserialize(new ObjectInputStream(new FileInputStream(fname)));
			grammar = syntaticsTable.getGrammar();
		}
		
		
		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");
//		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
//		syntaticsTable.setup();

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
			Token symbol = lexerForPrint.getSymbol(inStrm);

			if (grammar.isEndSymbol(symbol)) {
				System.out.println(symbol);
				break;
			}

			System.out.println(symbol);
		}

		System.out.println("*** Parser Stack ***");
		Lexer lexer = new JavaLexer(grammar.getEndSymbol());
		Parser parser = new Parser(lexer, syntaticsTable);
		Token symbol = parser.parse(new ByteArrayInputStream(sourceCode.getBytes()));
		System.out.println(symbol);
		
		long end = System.currentTimeMillis();
		System.out.println((end - start)  + "ms");
	}
}
