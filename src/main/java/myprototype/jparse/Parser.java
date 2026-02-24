package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import myprototype.jparse.symbol.terminal.InvalidTokenException;

public class Parser {
	private BufferedLexer lexer;
	private SyntaticsTable table;

	public Parser(BufferedLexer lexer, SyntaticsTable table) {
		this.table = table;
		this.lexer = lexer;
	}
	
	public Symbol parse(InputStream inStrm) throws IOException, InvalidTokenException {
		return parse(inStrm, (rule, stack) -> {
			return new SyntaxNode(rule, stack);
		});
	}
	
	public Symbol parse(InputStream inStrm, BiFunction<Rule, List<Symbol>, Object> compounder) throws IOException, InvalidTokenException {
		StateSymbolStack stack = null;
		this.table.setup();
		Action action = new Action(ActionKind.Begin, 0);
		for (;;) {
			switch (action.getKind()) {
			case Begin:
				stack = new StateSymbolStack();
				stack.push(action.getArgumentValue());
				break;
				
			case Shift:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).getLabel() + ") Shift Symbol -- " + action.getArgumentValue());
				
				stack.push(this.lexer.getSymbol(inStrm));
				stack.push(action.getArgumentValue());
				
				break;
				
			case Reduce:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).getLabel() + ") Reduce Rule -- " + action.getArgumentValue() + ": " + table.getRuleOf(action.getArgumentValue()));
				
				Rule rule = table.getRuleOf(action.getArgumentValue());
				List<Symbol> fromSymbols = stack.pop(rule.getSymbols().size());
				Collections.reverse(fromSymbols);
				
				int beg = -1, end = -1;
				if (fromSymbols.size() > 0) { // Nonterm -> 
					for (int i = 0; i < fromSymbols.size(); i++) {
						beg = fromSymbols.get(i).getBeg();
						if (beg >= 0) 
							break;
					}
					for (int i = 0; i < fromSymbols.size(); i++) {
						end = fromSymbols.get(fromSymbols.size() - i - 1).getEnd();
						if (end >= 0)
							break;
					}
				}
				Symbol toSymbol = new Symbol(rule.getProductSymbol(), beg, end, compounder.apply(rule, fromSymbols));
				stack.push(toSymbol);
				System.out.print(stack);
				
				action = table.getNonterminalAction(stack.getCurrentState(), toSymbol.getLabel());
				if (action.getKind() == ActionKind.Accept) {
					System.out.println(" Accept");
					return toSymbol;
				}
				System.out.println(" Goto State -- " + action.getArgumentValue());
				
				stack.push(action.getArgumentValue());
				
//				action = this.table.getNonterminalAction(stack.getCurrentState(), toSymbol.getLabel());
//				continue;
				break;

			case Goto:
				stack.push(this.lexer.getSymbol(inStrm));
				System.out.print(stack);
				System.out.println("Goto State -- " + action.getArgumentValue());
				
				stack.push(action.getArgumentValue());
				break;
				
			case Accept:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).getLabel() + ") Accept");
				return stack.pop();
			}
			
			action = table.getAction(stack.getCurrentState(), this.lexer.peek(inStrm).getLabel());
			if (action == null) {
				System.out.println("Unexpected token " + this.lexer.peek(inStrm).getLabel());
				System.exit(0);
			}
			
			
			
		}
	}
}
