package myprototype.jparse;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import myprototype.jparse.token.Tokenizer;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		String src = """
				hello
				""";

		try {
			Parser parser = new Parser(new Tokenizer());
			parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}
}
