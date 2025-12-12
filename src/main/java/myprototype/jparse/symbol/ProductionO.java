
package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.HashSet;

public class ProductionO {
//	private Class<? extends Symbol> symbol;
	private SymbolEnum symbol;
	private HashSet<RuleO> rules;

	public SymbolEnum getSymbol() {
		return symbol;
	}

//	private void setSymbol(Class<? extends Symbol> symbol) {
//		this.symbol = symbol;
//	}

	public HashSet<RuleO> getRules() {
		return rules;
	}

	public ProductionO(SymbolEnum symbol) {
		this.symbol = symbol;
		this.rules = new HashSet<>();
	}

	public boolean addRule(RuleO rule) {
		boolean result = this.rules.add(rule);
		if (result)
			rule.setOwner(this);
		return result;
	}
	
	public boolean removeRule(RuleO rule) {
		boolean result = this.rules.remove(rule);
		if (result)
			rule.setOwner(null);
		return result;
	}

}
