package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class Rule {
	public Production[] productions;
	public Function<Stack<? extends Symbol>, ? extends Nonterminal> compound;
	
	public Production[] getProductions() {
		return productions;
	}

	public Rule(Function<Stack<? extends Symbol>, ? extends Nonterminal> compound, Production... productions) {
		this.compound = compound;
		this.productions = productions;
	}

	@Override
	public String toString() {
		return String.join(" ", Arrays.asList(productions).stream().map(production -> production.getSymbol().name()).toList());
	}
	
	

//	public Nonterminal compound(Stack<Symbol> stack) {
//		return compound.apply(stack);
//	}
}