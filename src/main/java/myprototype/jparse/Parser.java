package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.symbol.nonterminal.CompilationUnit;
import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.Lexer;
import myprototype.jparse.symbol.terminal.Terminal;

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
