package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.StateSymbol;
import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class RuleO {
	private ProductionO owner;
	public ProductionO[] productions;
	public Function<Stack<StateSymbol>, ? extends Nonterminal> compound;
	
	public ProductionO getOwner() {
		return owner;
	}
	
	public void setOwner(ProductionO owner) {
		if (owner == this.owner)
			return;
		
		if (owner == null || this.owner != null)
			this.owner.removeRule(this);
		
		this.owner = owner;
	}

	public ProductionO[] getProductions() {
		return productions;
	}

	public RuleO(Function<Stack<StateSymbol>, ? extends Nonterminal> compound, ProductionO... productions) {
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