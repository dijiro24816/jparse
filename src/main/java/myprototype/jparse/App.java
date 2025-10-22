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
		System.out.println("Hello World!");

		String src = """
				/*hello world*/   hello world
				hello
				""";
		try {
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			int ch;
			while ((ch = inStrm.read()) != -1) {
				System.out.print((char) ch);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		try {
			Parser parser = new Parser(new Tokenizer());
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			parser.parse(inStrm);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
