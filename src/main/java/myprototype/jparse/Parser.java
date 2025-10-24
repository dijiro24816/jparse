package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.syntax.CompilationUnit;
import myprototype.jparse.token.Token;
import myprototype.jparse.token.Tokenizer;

public class Parser {
	
	public Tokenizer tokenizer;

	public Parser(Tokenizer tokenizer) {
		super();
		this.tokenizer = tokenizer;
	}
	
	public CompilationUnit parse(InputStream inStrm) throws IOException {
		Token token;
		
		while ((token = this.tokenizer.tokenize(inStrm)) != null) {
			System.out.println(token);
		}
		return new CompilationUnit();
	}
}
