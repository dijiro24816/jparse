package myprototype.jparse;

import java.util.function.Function;

public class Rule {
	private Enum<?> productSymbol;
	private Function<StateSymbolStack, Object> compounder;
	private Object[] symbols;
	
	public Enum<?> getProductSymbol() {
		return productSymbol;
	}

	public Function<StateSymbolStack, Object> getCompounder() {
		return compounder;
	}

	public Object[] getSymbols() {
		return symbols;
	}

	public Rule(Enum<?> productSymbol, Function<StateSymbolStack, Object> compounder, Object...symbols) {
		this.productSymbol = productSymbol;
		this.compounder = compounder;
		this.symbols = symbols;
	}
}