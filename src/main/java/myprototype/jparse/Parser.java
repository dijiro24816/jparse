package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.syntax.CompilationUnit;
import myprototype.jparse.token.Tokenizer;

public class Parser {
	
	public Tokenizer tokenizer;

	public Parser(Tokenizer tokenizer) {
		super();
		this.tokenizer = tokenizer;
	}
	
	public CompilationUnit parse(InputStream inStrm) throws IOException {
		
		System.out.println(this.tokenizer.tokenize(inStrm));
		System.out.println(this.tokenizer.tokenize(inStrm));
		return new CompilationUnit();
	}
}
