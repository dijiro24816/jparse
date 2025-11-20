package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.syntax.CompilationUnit;
import myprototype.jparse.syntax.symbol.terminal.InvalidTokenException;
import myprototype.jparse.syntax.symbol.terminal.Terminal;
import myprototype.jparse.syntax.symbol.terminal.Lexer;

public class Parser {
	
	public Lexer tokenizer;

	public Parser(Lexer tokenizer) {
		super();
		this.tokenizer = tokenizer;
	}
	
	public CompilationUnit parse(InputStream inStrm) throws IOException {
		Terminal token;
		
		try {
			while ((token = this.tokenizer.tokenize(inStrm)) != null) {
				System.out.println(token);
			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return new CompilationUnit();
	}
}
