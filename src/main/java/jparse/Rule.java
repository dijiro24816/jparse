package jparse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public record Rule(String productSymbol, PrecedenceRuleInfo precedenceInfo, List<String> symbols)
		implements Serializable {
	public String getFirstSymbol() {
		return this.symbols.size() > 0 ? this.symbols.get(0) : null;
	}
	
	public Rule(String productSymbol, String... symbols) {
		this(productSymbol, Arrays.asList(symbols));
	}
	
	public Rule(String productSymbol, List<String> symbols) {
		this(productSymbol, new PrecedenceRuleInfo(), symbols);
	}

	public boolean isEmpty() {
		return this.symbols.isEmpty();
	}

	public boolean isReplaceable() {
		return this.symbols.size() == 1;
	}

	public int size() {
		return this.symbols.size();
	}

	@Override
	public String toString() {
		return this.productSymbol + " -> " + String.join(" ", this.symbols);
	}
}
