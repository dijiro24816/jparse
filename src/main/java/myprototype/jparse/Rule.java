package myprototype.jparse;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Rule {
	private String productSymbol;
	private Function<StateSymbolStack, Object> compounder;
	private List<String> symbols;
	
	public String getProductSymbol() {
		return productSymbol;
	}

	public Function<StateSymbolStack, Object> getCompounder() {
		return compounder;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public Rule(String productSymbol, Function<StateSymbolStack, Object> compounder, String...symbols) {
		this.productSymbol = productSymbol;
		this.compounder = compounder;
		this.symbols = Arrays.asList(symbols);
	}

	@Override
	public String toString() {
		return this.productSymbol + " -> " + String.join(" ", this.symbols);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (obj.getClass().getName() != this.getClass().getName())
			return false;
		
		Rule otherRule = (Rule)obj;
		
		return this.productSymbol.equals(otherRule.getProductSymbol()) && this.symbols.equals(otherRule.getSymbols());
	}
	
	@Override
	public int hashCode() {
		return this.productSymbol.hashCode() + this.symbols.hashCode();
	}

	public static void main(String[] args) {
		Rule rule = new Rule("Stmt", stack -> { return null; }, "+", "Exp", "Exp");
		System.out.println(rule);
	}
}