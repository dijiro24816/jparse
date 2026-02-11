package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.JavaLexer;

/**
 * Hello world!
 */


public class App {
	public static void main(String[] args) {
		OperatorPrecedenceRule operatorPrecedenceRule = new OperatorPrecedenceRule();
		operatorPrecedenceRule.add(PrecedenceDirection.Right, "=");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "+", "-");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "*", "/");

		String[] terminals = { "Identifier", "NUM" };
		Grammar grammar = new Grammar("S", "$", Arrays.asList(terminals),
				new Rule("S", "Stmt"),
				new Rule("Stmt"),
				new Rule("Stmt", "Expr"),
				new Rule("Stmt", "Assg"),
				new Rule("Expr", "Identifier"),
				new Rule("Expr", "NUM"),
				new Rule(operatorPrecedenceRule.getInfo("+"), "Expr", "Expr", "+", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("-"), "Expr", "Expr", "-", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("*"), "Expr", "Expr", "*", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("/"), "Expr", "Expr", "/", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("="), "Assg", "Identifier", "=", "Expr")
				);
		
		
		
//		String[] terminals = { "Identifier", "NUM" };
//		Grammar grammar = new Grammar("S", "$", Arrays.asList(terminals),
//				
//				new Rule("QualifiedIdentifier", "Identifier"),
//				
//				
//				new Rule("S", "Stmt"),
//				new Rule("Stmt", "Expr"),
//				new Rule("Stmt", "Assg"),
//				new Rule("Expr", "Identifier"),
//				new Rule("Expr", "NUM"),
//				new Rule(operatorPrecedenceRule.getInfo("+"), "Expr", "Expr", "+", "Expr"),
//				new Rule(operatorPrecedenceRule.getInfo("-"), "Expr", "Expr", "-", "Expr"),
//				new Rule(operatorPrecedenceRule.getInfo("*"), "Expr", "Expr", "*", "Expr"),
//				new Rule(operatorPrecedenceRule.getInfo("/"), "Expr", "Expr", "/", "Expr"),
//				new Rule(operatorPrecedenceRule.getInfo("="), "Assg", "Identifier", "=", "Expr")
//				);
		
		System.out.println("*** Grammar ***");
		System.out.println(grammar);
		
		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);
		
		System.out.println(syntaticsTable.getActionsCSV(grammar));
		System.out.println(syntaticsTable.getGotosCSV(grammar));
		
		String src = """
	
				""";
		System.out.println("*** Source ***");
		System.out.println("```");
		System.out.println(src);
		System.out.println("```");
		
		try {
			System.out.println("*** Loaded Token ***");
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			JavaLexer lexer = new JavaLexer(grammar.getEndSymbol());
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
		
		
		Parser parser = new Parser(new BufferedLexer(new JavaLexer(grammar.getEndSymbol())), grammar, syntaticsTable);
		try {
			System.out.println("*** Parser Stack ***");
			System.out.println(parser.parse(new ByteArrayInputStream(src.getBytes()), (rule, symbols) -> { return null; }));
		} catch (IOException | InvalidTokenException e) {
			e.printStackTrace();
		}
		
		System.out.println("MSG: Finished!");
	}

	public static void maina(String[] args) throws CloneNotSupportedException {

		//		ArrayList<Sample> samples = new ArrayList<Sample>();
		//		samples.add(new Sample(1, 2));
		//		samples.add(new Sample(1, 2));
		//
		//		System.out.println(samples.stream().mapToInt(s -> { return s.b; }).sum());
		//		
		//		TextBuffer tb = new TextBuffer();
		//		String s5 = """
		//				h\\u0023\\u0023ello world
		//				""";
		//		InputStream is = new ByteArrayInputStream(s5.getBytes());
		//		try {
		//			for (int i = 0; i < 13; i++)
		//				tb.getCharacter(is);
		//			
		//		} catch(IOException e) {
		//			System.out.println(e.getMessage());
		//			System.exit(0);
		//		} catch (InvalidTokenException e) {
		//			e.printStackTrace();
		//			System.exit(0);
		//		}
		//		System.out.println(tb.stringBuilder.toString());
		//		System.out.println(tb.getRawLength());
		//		tb.erase(1, 3);
		//		System.out.println(tb.stringBuilder.toString());
		//		System.out.println(tb.erasedStrings.get(0));
		//		System.out.println(tb.getRawLength());
		//		System.out.println(tb.getByteCount(0));
		//		
		//		
		//		System.exit(0);

		//		int a = 123456789;
		//		
		//		
		//		InputStream in = System.in;
		//		Function<Void, Integer> read = (Void) -> {
		//			try {
		//				return in.read();
		//			} catch (IOException e) {
		//				// TODO 自動生成された catch ブロック
		//				e.printStackTrace();
		//			}
		//			return 0;
		//		};
		//		
		//		
		//		while (read.apply(null) >= 0) {
		//			System.out.println("ok");
		//		}
		//		
		//		
		//		System.out.println("\u0022 + "hello" + \u0022");
		//		
		//		System.out.println(a
		//			         +       
		//			       + - +     
		//			     + - - - +   
		//			   + - - - - - + 
		//			 + - - - - - - - +
		//			   + - - - - - + 
		//			     + - - - +   
		//			       + - +      
		//			         +       
		//		a);
		//		System.exit(a);

		//		String s = """
		//				a017
		//				""";
		//		
		//		String s2 = "";
		//		Tokenizer tokenizer = new Tokenizer();
		//		InputStream ins = new ByteArrayInputStream(s.getBytes());
		//		StringBuilder sb = new StringBuilder();
		//		sb.append("hello");
		//		
		//		try {
		//			tokenizer.textBuffer.jumpNext(ins);
		//		System.out.println(tokenizer.extractOctalDigits(ins));
		//		} catch (IOException e) {
		//			
		//		}
		//		
		//		System.exit(0);

		//		String src = """
		//				/*hello world      private abstract          asdf asdf asdf asd*/   hello private
		//				hello
		//				private
		//				abstract
		//				abs
		//				
		//				null
		//				public class Main
		//				123123
		//				0x1
		//				""";

		//		String src = """
		//				123124
		//				0xFFFF
		//				0
		//				""";

		//		String src = """
		//				public class Main
		//				boolean _sample = true;
		//				0b011
		//				(011)
		//				""";

		//		String src = """
		//				= > < ! ~ ? :
		//				== <= >= != && || ++ --
		//				+ - * / & | ^ % << >> >>>
		//				+= -= *= /= &= |= ^= %= <<= >>= >>>=
		//				""";

		//		String src = """
		//				.1
		//				0.1
		//				0x2p+3
		//				""";
		//		String src = """
		//				'\\u005C141'
		//				'\\141'
		//				""";

		//		String src = """
		//				"hello"
		//				""";
		//		TextBuffer tb = new TextBuffer();
		//		InputStream is = new ByteArrayInputStream(src.getBytes());
		//		try {
		//			System.out.println(tb.getCharacter(is));
		//			System.out.println(tb.getCharacter(is));
		//		} catch (IOException e) {
		//			// TODO 自動生成された catch ブロック
		//			e.printStackTrace();
		//		} catch (InvalidTokenException e) {
		//			// TODO 自動生成された catch ブロック
		//			e.printStackTrace();
		//		}

		//		System.exit(0);

		//'\\u005C\\u005C'

		//		System.out.println(Long.valueOf("0012"));
		//		System.exit(0);

		//		try {
		//			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
		//			int ch;
		//			while ((ch = inStrm.read()) != -1) {
		//				System.out.print((char) ch);
		//			}
		//		} catch (IOException e) {
		//			System.out.println(e.getMessage());
		//			System.exit(0);
		//		}


//		ParserData parserData = new ScenarioWriter().getParserData(s, SymbolEnum.class);
//
//		System.out.println(parserData.getRuleTableString());
//		System.out.println(parserData.getSyntaticsTableString());
//		System.exit(0);
//
//		String src = "+ jkalsdfj klasdfj klasdfj kl 1 2";
//
//		try {
//			Parser parser = new Parser(new Lexer(), parserData);
//			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
//			parser.parse(inStrm);
//			//			System.out.println("MSG: Finished!");
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
	}
}
