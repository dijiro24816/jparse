package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.BiFunction;

import myprototype.jparse.symbol.terminal.InvalidTokenException;

public class Parser {
	private BufferedLexer lexer;
	private Grammar grammar;
	private SyntaticsTable table;

	public Parser(BufferedLexer lexer, Grammar grammar, SyntaticsTable table) {
		this.grammar = grammar;
		this.table = table;
		this.lexer = lexer;
	}
	
	public Object parse(InputStream inStrm, BiFunction<Rule, List<Symbol>, Object> compounder) throws IOException, InvalidTokenException {
		StateSymbolStack stack = null;
		Action action = new Action(ActionKind.Start, 0);
		
		for (;;) {
			switch (action.getKind()) {
			case Start:
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
				System.out.println(" (" + this.lexer.peek(inStrm).getLabel() + ") Reduce Rule -- " + action.getArgumentValue() + ": " + this.grammar.getRules().get(action.getArgumentValue()));
				
				Rule rule = this.grammar.getRules().get(action.getArgumentValue());
				List<Symbol> fromSymbols = stack.pop(rule.getSymbols().size());
				int beg = -1, end = -1;
				if (fromSymbols.size() > 0) { // Nonterm -> 
					beg = fromSymbols.get(0).getBeg();
					end = fromSymbols.get(fromSymbols.size() - 1).getEnd();
				}
				Symbol toSymbol = new Symbol(rule.getProductSymbol(), beg, end, compounder.apply(rule, fromSymbols));
				System.out.print(stack);
				
				action = this.table.getNonterminalAction(stack.getCurrentState(), toSymbol.getLabel());
				if (action.getKind() == ActionKind.Accept) {
					System.out.println(" (" + toSymbol.getLabel() + ") Accept");
					return toSymbol;
				}
				System.out.println(" (" + toSymbol.getLabel() + ") Goto State -- " + action.getArgumentValue());
				
				
				stack.push(toSymbol);
				stack.push(action.getArgumentValue());
				
//				action = this.table.getNonterminalAction(stack.getCurrentState(), toSymbol.getLabel());
//				continue;
				break;

			case Goto:
				System.out.print(stack);
				System.out.println(" (" + this.lexer.peek(inStrm).getLabel() + ") Goto State -- " + action.getArgumentValue());
				
				stack.push(this.lexer.getSymbol(inStrm));
				stack.push(action.getArgumentValue());
				break;
				
			case Accept:
				return stack.pop();
			}
			
//			System.out.println(this.lexer.peek(inStrm));
			action = this.table.getAction(stack.getCurrentState(), this.lexer.peek(inStrm).getLabel());
		}
	}
}
