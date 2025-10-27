package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.token.Tokenizer;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {

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
	
		String src = """
				= > < ! ~ ? :
				== <= >= != && || ++ --
				+ - * / & | ^ % << >> >>>
				+= -= *= /= &= |= ^= %= <<= >>= >>>=
				""";
		
		
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
		
		try {
			Parser parser = new Parser(new Tokenizer());
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			parser.parse(inStrm);
			System.out.println("MSG: Finished!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
