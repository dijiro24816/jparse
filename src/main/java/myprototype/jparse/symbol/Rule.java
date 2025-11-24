package myprototype.jparse.symbol;

import java.util.Stack;
import java.util.function.Function;

public class Rule<T extends Symbol> {
	public Production<? extends Symbol>[] productions;
	public Function<Stack<Symbol>, T> compound;
	
	@SafeVarargs
	public Rule(Function<Stack<Symbol>, T> compound, Production<? extends Symbol>... productions) {
		this.compound = compound;
		this.productions = productions;
	}
	
	public T compound(Stack<Symbol> stack) {
		return compound.apply(stack);
	}
}