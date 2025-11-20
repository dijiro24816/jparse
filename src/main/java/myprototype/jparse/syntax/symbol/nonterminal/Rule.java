package myprototype.jparse.syntax.symbol.nonterminal;

import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.syntax.symbol.Symbol;

public class Rule<T extends Symbol> {
	public Production<T>[] productions;
	public Function<Stack<Symbol>, T> compound;
	
	public Rule(Function<Stack<Symbol>, T> compound, Production... productions) {
		this.compound = compound;
		this.productions = productions;
	}
	
	public T compound(Stack<Symbol> stack) {
		return compound.apply(stack);
	}
}