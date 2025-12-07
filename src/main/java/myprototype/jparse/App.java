package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.Lexer;

/**
 * Hello world!
 */
public class App {
	enum Sample {
		A,
		B
		
		;
	}
	public static void main(String[] args) throws CloneNotSupportedException {

		
		
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
		
		Production s = new Production(SymbolEnum.S);
		Production stmt = new Production(SymbolEnum.STMT);
		Production assg = new Production(SymbolEnum.ASSG);
		Production int_ = new Production(SymbolEnum.INT_KEYWORD_TOKEN);
		Production exp = new Production(SymbolEnum.EXP);
		Production digit = new Production(SymbolEnum.INTEGER_LITERAL_TOKEN);
		Production ident = new Production(SymbolEnum.IDENTIFIER_TOKEN);
		Production add = new Production(SymbolEnum.ADDITION_OPERATOR_TOKEN);	
		Production sub = new Production(SymbolEnum.SUBTRACTION_OPERATOR_TOKEN);
		Production mul = new Production(SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN);
		Production div = new Production(SymbolEnum.DIVISION_OPERATOR_TOKEN);
		s.addRule(new Rule(stack -> { return null; }, stmt));
		stmt.addRule(new Rule(stack -> { return null; }, assg));
		stmt.addRule(new Rule(stack -> { return null; }, exp));
		assg.addRule(new Rule(stack -> { return null; }, int_, ident, exp));
		exp.addRule(new Rule(stack -> { return null; }, add, exp, exp));
		exp.addRule(new Rule(stack -> { return null; }, sub, exp, exp));
		exp.addRule(new Rule(stack -> { return null; }, mul, exp, exp));
		exp.addRule(new Rule(stack -> { return null; }, div, exp, exp));
		exp.addRule(new Rule(stack -> { return null; }, digit));
		exp.addRule(new Rule(stack -> { return null; }, ident));
		ParserData parserData = new ScenarioWriter().getParserData(s, SymbolEnum.class);
		
		String src = "+ 1 2";
		
		try {
			Parser parser = new Parser(new Lexer(), parserData);
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			parser.parse(inStrm);
			System.out.println("MSG: Finished!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
