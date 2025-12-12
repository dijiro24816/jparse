package myprototype.jparse;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Grammar {
	private Class<? extends Enum<?>> terminalEnum;
	private Class<? extends Enum<?>> nonterminalEnum;
	private Rule[] rules;

	public Grammar(Class<? extends Enum<?>> terminalEnum, Class<? extends Enum<?>> nonterminalEnum,
			Rule... rules) {
		this.terminalEnum = terminalEnum;
		this.nonterminalEnum = nonterminalEnum;
		this.rules = rules;
	}
	
	public boolean isValid() {
		for (Rule rule : this.rules)
			for (Object symbol : rule.getSymbols())
				if (symbol.getClass() != String.class && symbol.getClass() != this.terminalEnum && symbol.getClass() != this.nonterminalEnum)
					return false;
		
		
			
		return true;
	}
	
	public boolean isTerminal(Object object) {
		return object.getClass() == this.terminalEnum;
	}
	
	public boolean isNonterminal(Object object) {
		return object.getClass() == this.nonterminalEnum;
	}

	public Collection<String> getKeywords() {
		HashSet<String> keywords = new HashSet<>();
		for (Rule rule : this.rules)
			for (Object symbol : rule.getSymbols())
				if (symbol.getClass() == String.class)
					keywords.add((String)symbol);
		return keywords;
	}
	
	public HashMap<Rule, Integer> getIndicesOfRules() {
		HashMap<Rule, Integer> indicesOfRules = new HashMap<>();
		for (int i = 0; i < rules.length; i++)
			indicesOfRules.put(this.rules[i], i);
		return indicesOfRules;
	}
}
