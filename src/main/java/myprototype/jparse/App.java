package myprototype.jparse;

/**
 * Hello world!
 */

public class App {
	public static void main(String[] args) {
//		Grammar grammar = new Grammar(SymbolEnum.class, MyNonterm.class,
//				new Rule("S", stack -> {
//					return null;
//				}, "Stmt"),
//				new Rule("Stmt", stack -> {
//					return null;
//				}, "Exp"),
//				new Rule("Assg", stack -> {
//					return null;
//				}, "int", "IDENTIFIER_TOKEN", "Exp"),
//				new Rule("Exp", stack -> {
//					return null;
//				}, "+", "Exp", "Exp"),
//				new Rule("Exp", stack -> {
//					return null;
//				}, "-", "Exp", "Exp"),
//				new Rule("Exp", stack -> {
//					return null;
//				}, "*", "Exp", "Exp"),
//				new Rule("Exp", stack -> {
//					return null;
//				}, "/", "Exp", "Exp"));
//
//		if (grammar.isInvalid()) {
//			throw new RuntimeException("Invalid Grammar Rules");
//		}

//		ProductionO s = new ProductionO(SymbolEnum.S);
//		ProductionO stmt = new ProductionO(SymbolEnum.STMT);
//		ProductionO assg = new ProductionO(SymbolEnum.ASSG);
//		ProductionO int_ = new ProductionO(SymbolEnum.INT_KEYWORD_TOKEN);
//		ProductionO exp = new ProductionO(SymbolEnum.EXP);
//		ProductionO digit = new ProductionO(SymbolEnum.INTEGER_LITERAL_TOKEN);
//		ProductionO ident = new ProductionO(SymbolEnum.IDENTIFIER_TOKEN);
//		ProductionO add = new ProductionO(SymbolEnum.ADDITION_OPERATOR_TOKEN);
//		ProductionO sub = new ProductionO(SymbolEnum.SUBTRACTION_OPERATOR_TOKEN);
//		ProductionO mul = new ProductionO(SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN);
//		ProductionO div = new ProductionO(SymbolEnum.DIVISION_OPERATOR_TOKEN);
//
//		// S    -> Stmt
//		s.addRule(new RuleO(stack -> {
//			return null;
//		}, stmt));
//
//		// Stmt -> Assg
//		stmt.addRule(new RuleO(stack -> {
//			return null;
//		}, assg));
//
//		// Stmt -> Exp
//		stmt.addRule(new RuleO(stack -> {
//			return null;
//		}, exp));
//
//		// Assg -> int IDENT Exp
//		assg.addRule(new RuleO(stack -> {
//			return null;
//		}, int_, ident, exp));
//
//		// Exp  -> + Exp Exp
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, add, exp, exp));
//
//		// Exp  -> - Exp Exp
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, sub, exp, exp));
//
//		// Exp  -> * Exp Exp
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, mul, exp, exp));
//
//		// Exp  -> / Exp Exp
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, div, exp, exp));
//
//		// Exp  -> DIGIT
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, digit));
//
//		// Exp  -> IDENT
//		exp.addRule(new RuleO(stack -> {
//			return null;
//		}, ident));
//
//		ParserData parserData = new ScenarioWriter().getParserData(s, SymbolEnum.class);
//
//		//		System.out.println(parserData.getRuleTableString());
//		//		System.out.println(parserData.getSyntaticsTableString());
//		//		System.exit(0);
//
//		//		String src = "int v + + 3.14159265359 0x2p-2 ";
//		String src = "int v + 1 1";
//		//		String src = "+ \\u0031 1";
//		//		int v = \u0031;
//		//		\u0069\u006E\u0074\u0020\u0076\u0020\u003D\u0020\u0031\u003b
//		//		System.out.println(v);
//
//		try {
//			Parser parser = new Parser(new Lexer(), parserData);
//			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
//			parser.parse(inStrm);
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
//		System.out.println("MSG: Finished!");
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
