
package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.HashSet;

public class Production {
//	private Class<? extends Symbol> symbol;
	private SymbolEnum symbol;
	private HashSet<Rule> rules;

	public SymbolEnum getSymbol() {
		return symbol;
	}

//	private void setSymbol(Class<? extends Symbol> symbol) {
//		this.symbol = symbol;
//	}

	public HashSet<Rule> getRules() {
		return rules;
	}

	public Production(SymbolEnum symbol) {
		this.symbol = symbol;
		this.rules = new HashSet<>();
	}

	public boolean addRule(Rule rule) {
		boolean result = this.rules.add(rule);
		if (result)
			rule.setOwner(this);
		return result;
	}
	
	public boolean removeRule(Rule rule) {
		boolean result = this.rules.remove(rule);
		if (result)
			rule.setOwner(null);
		return result;
	}

}
