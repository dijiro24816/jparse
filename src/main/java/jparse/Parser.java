package jparse;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class Parser {
	private BufferedLexer lexer;
	private SyntaticsTable table;

	public Parser(Lexer lexer, SyntaticsTable table) {
		this.table = table;
		this.lexer = new BufferedLexer(lexer);
	}
	
	public Token parse(InputStream inStrm) throws IOException, InvalidTokenException {
		return parse(inStrm, (rule, stack) -> {
			return new SyntaxNode(rule, stack);
		});
	}
	
	public Token parse(InputStream inStrm, BiFunction<Rule, List<Token>, Object> compounder) throws IOException, InvalidTokenException {
		StateTokenStack stack = null;
		this.table.setup();
		Action action = new Action(ActionKind.Begin, 0);
		for (;;) {
			switch (action.kind()) {
			case Begin:
				stack = new StateTokenStack();
				stack.push(action.argumentValue());
				break;
				
			case Shift:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).label() + ") Shift Symbol -- " + action.argumentValue());
				
				stack.push(this.lexer.getSymbol(inStrm));
				stack.push(action.argumentValue());
				
				break;
				
			case Reduce:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).label() + ") Reduce Rule -- " + action.argumentValue() + ": " + table.getRuleOf(action.argumentValue()));
				
				Rule rule = table.getRuleOf(action.argumentValue());
				List<Token> fromSymbols = stack.pop(rule.symbols().size());
				Collections.reverse(fromSymbols);
				
				int beg = -1, end = -1;
				if (fromSymbols.size() > 0) { // Nonterm -> 
					for (int i = 0; i < fromSymbols.size(); i++) {
						beg = fromSymbols.get(i).beg();
						if (beg >= 0) 
							break;
					}
					for (int i = 0; i < fromSymbols.size(); i++) {
						end = fromSymbols.get(fromSymbols.size() - i - 1).end();
						if (end >= 0)
							break;
					}
				}
				Token toSymbol = new Token(rule.productSymbol(), beg, end, compounder.apply(rule, fromSymbols));
				stack.push(toSymbol);
				System.out.print(stack);
				
				action = table.getNonterminalAction(stack.getCurrentState(), toSymbol.label());
				if (action.kind() == ActionKind.Accept) {
					System.out.println(" Accept");
					return toSymbol;
				}
				System.out.println(" Goto State -- " + action.argumentValue());
				
				stack.push(action.argumentValue());
				
//				action = this.table.getNonterminalAction(stack.getCurrentState(), toSymbol.getLabel());
//				continue;
				break;

			case Goto:
				stack.push(this.lexer.getSymbol(inStrm));
				System.out.print(stack);
				System.out.println("Goto State -- " + action.argumentValue());
				
				stack.push(action.argumentValue());
				break;
				
			case Accept:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).label() + ") Accept");
				return stack.pop();
			}
			
			action = table.getAction(stack.getCurrentState(), this.lexer.peek(inStrm).label());
			if (action == null) {
				System.out.println("Unexpected token " + this.lexer.peek(inStrm).label());
				System.exit(0);
			}
			
			
			
		}
	}
	
	public Token parseCode(String code) throws IOException, InvalidTokenException {
		return parse(new ByteArrayInputStream(code.getBytes()));
	}
	
	public Token parseFile(String fileName) throws FileNotFoundException, IOException, InvalidTokenException {
		return parse(new FileInputStream(fileName));
	}
}
