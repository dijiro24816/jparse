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
	
	public static Parser create(BufferedLexer lexer, Grammar grammar) {
		return new Parser(lexer, grammar, new SyntaticsTable(grammar));
	}
	
	public Action parse(InputStream inStrm, BiFunction<Rule, List<Symbol>, Object> compounder) throws IOException, InvalidTokenException {
		StateSymbolStack stack = null;
		Action action = new Action(ActionKind.Start, 0);
		
		for (;;) {
			switch (action.getKind()) {
			case Start:
				stack = new StateSymbolStack();
				stack.push((int)action.getArgumentValue());
				break;
				
			case Shift:
				stack.push(this.lexer.getSymbol(inStrm));
				stack.push((int)action.getArgumentValue());
				break;
				
			case Reduce:
				Rule rule = this.grammar.getRules().get((int)action.getArgumentValue());
				List<Symbol> fromSymbols = stack.pop(rule.getSymbols().size());
				int beg = fromSymbols.get(0).getBeg();
				int end = fromSymbols.get(fromSymbols.size() - 1).getEnd();
				Symbol toSymbol = new Symbol(rule.getProductSymbol(), beg, end, compounder.apply(rule, fromSymbols));
				stack.push(toSymbol);
				continue;

			case Goto:
				Symbol symbol = stack.pop();
				stack.push((int)this.table.getNonterminalAction(stack.getCurrentState(), symbol.getLabel()).getArgumentValue());
				break;
				
			case Accept:
				return new Action(ActionKind.Accept, stack.pop());
				
			case Error:
				return action;
			}
			
			action = this.table.getAction(stack.getCurrentState(), this.lexer.peek(inStrm).getLabel());
		}
	}
}
