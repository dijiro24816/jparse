package jparse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jparse.java.JavaLexer;

public class App {
	public static void main(String[] args) throws IOException, InvalidTokenException, ClassNotFoundException {
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
		
		grammar = Grammar.loadString(grammarSource);
		syntaticsTable = SyntaticsTable.create(grammar);
		
		
		System.out.println("*** Grammar ***");
		System.out.println(grammar);

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
				a = b + c
				""";

		System.out.println("*** Source Code ***");
		System.out.println("```");
		System.out.print(sourceCode);
		System.out.println("```");

		System.out.println();
		System.out.println("*** Loaded Token ***");
		Lexer lexer = new JavaLexer();
		for (Token token : lexer.tokenizeAll(new ByteArrayInputStream(sourceCode.getBytes())))
			System.out.println(token);
		
		System.out.println("*** Parser Stack ***");
		lexer = new JavaLexer();
		Parser parser = new Parser(lexer, syntaticsTable);
		Token symbol = parser.parseString(sourceCode);
		System.out.println("*** Result Token ***");
		System.out.println(symbol);
		
		long end = System.currentTimeMillis();
		System.out.println((end - start)  + "ms");
	}

	public static void maina(String[] args) throws IOException, InvalidTokenException, ClassNotFoundException {
		long start = System.currentTimeMillis();
		
		Grammar grammar = Grammar.loadFile("JavaSyntax.txt");
		SyntaticsTable syntaticsTable = SyntaticsTable.create(grammar);

		
		System.out.println("*** Grammar ***");
		System.out.println(grammar);

//		System.out.println();
		System.out.println("*** Syntatics Table ***");
		
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
			Token symbol = lexerForPrint.tokenize(inStrm);

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
