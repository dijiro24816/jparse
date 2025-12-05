package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class Rule {
	private Production owner;
	public Production[] productions;
	public Function<Stack<? extends Symbol>, ? extends Nonterminal> compound;
	
	public Production getOwner() {
		return owner;
	}
	
	public void setOwner(Production owner) {
		if (owner == this.owner)
			return;
		
		if (owner == null || this.owner != null)
			this.owner.removeRule(this);
		
		this.owner = owner;
	}

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