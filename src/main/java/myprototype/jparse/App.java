package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
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
		syntaticsTable.setup();

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
		Grammar grammar = new Grammar("CompilationUnit", "$").resource(new FileInputStream("JavaSyntax.txt"));

		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		System.out.println();
		System.out.println("*** Syntatics Table ***");
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
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
				    public static void main(String[] args) {
				        System.out.println();
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
}
