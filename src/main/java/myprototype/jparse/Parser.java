package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.BiFunction;

import myprototype.jparse.symbol.terminal.InvalidTokenException;

//@FunctionalInterface
//interface Compounder<Rule, List<?>, Object> {
//	Object apply(Rule rule, List<?> list) {
//		return nuill;
//	}
//}

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
		StateSymbolStack stack = new StateSymbolStack();
		stack.push(0);
		
		for (;;) {
			Action action = this.table.getAction(stack.getCurrentState(), this.lexer.peek(inStrm).getLabel());
			
			switch (action.getKind()) {
			case Shift:
				stack.push(this.lexer.getSymbol(inStrm), action.getArgumentValue());
				break;
				
			case Reduce:
				Rule rule = this.grammar.getRules().get(action.getArgumentValue());
				List<Symbol> fromSymbols = stack.pop(rule.getSymbols().size());
				int beg = fromSymbols.get(0).getBeg();
				int end = fromSymbols.get(fromSymbols.size() - 1).getEnd();
				Symbol toSymbol = new Symbol(rule.getProductSymbol(), beg, end, compounder.apply(rule, fromSymbols));
				stack.push(toSymbol, this.table.getAction(stack.getCurrentState(), toSymbol.getLabel()).getArgumentValue());
				break;
				
			default:
				return action;
			}
		}
	}
}
