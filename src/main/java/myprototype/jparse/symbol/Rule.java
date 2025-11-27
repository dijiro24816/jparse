package myprototype.jparse.symbol;

import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class Rule {
	public Production[] productions;
	public Function<Stack<Symbol>, ? extends Nonterminal> compound;
	
	public Production[] getProductions() {
		return productions;
	}

	public Rule(Function<Stack<Symbol>, ? extends Nonterminal> compound, Production... productions) {
		this.compound = compound;
		this.productions = productions;
	}
	
//	public Nonterminal compound(Stack<Symbol> stack) {
//		return compound.apply(stack);
//	}
}