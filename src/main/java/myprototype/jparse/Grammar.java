package myprototype.jparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grammar {
	private String startSymbol;
	
	// We can implements like this, because Java resolve the collision by the Chaining method 
	private HashMap<String, Integer> normalSymbolIndices;
	private HashMap<String, Integer> terminalSymbolIndices;
	private HashMap<String, Integer> nonterminalSymbolRuleIndices;
	private List<Rule> rules;

	public List<Rule> getRules() {
		return rules;
	}
	
	public String getStartSymbol() {
		return this.startSymbol;
	}

	public int getNormalSymbolCount() {
		return this.normalSymbolIndices.size();
	}

	public int getTerminalSymbolCount() {
		return this.terminalSymbolIndices.size();
	}

	public Grammar(String startSymbol, Collection<String> terminalSymbols) {
		this.startSymbol = startSymbol;
		this.normalSymbolIndices = new HashMap<String, Integer>();
		this.terminalSymbolIndices = new HashMap<String, Integer>();
		terminalSymbols.stream().forEach(e -> addTerminalSymbolWithIndex(e));
		this.nonterminalSymbolRuleIndices = new HashMap<String, Integer>();
		this.rules = new ArrayList<Rule>();
	}

	private int addNonterminalSymbolWithIndex(String symbol) {
		return this.nonterminalSymbolRuleIndices.put(symbol, this.nonterminalSymbolRuleIndices.size());
	}
	
	private int addNormalSymbolWithIndex(String symbol) {
		return this.normalSymbolIndices.put(symbol, getNormalAndTerminalCount());
	}
	
	private int addTerminalSymbolWithIndex(String symbol) {
		return this.terminalSymbolIndices.put(symbol, getNormalAndTerminalCount());
	}
	
	public int getNormalAndTerminalCount() {
		return this.normalSymbolIndices.size() + this.terminalSymbolIndices.size();
	}

	public void add(Rule rule) {
		if (this.nonterminalSymbolRuleIndices.containsKey(rule.getProductSymbol())) {
			System.out.println("WARN: DUPLICATED: " + rule);
			return;
		}

		// Add rule with index
		addNonterminalSymbolWithIndex(rule.getProductSymbol());
		this.rules.add(rule);

		for (String symbol : rule.getSymbols())
			if ((!isTerminalSymbol(symbol) || !isNonterminalSymbol(symbol)) &&
					!this.normalSymbolIndices.containsKey(symbol))
				addNormalSymbolWithIndex(symbol);
	}

	public void validateNonterminalSymbolClasses() {
		for (Rule rule : this.rules) {
			try {
				Class<?> klass = Class.forName(rule.getProductSymbol());
				if (!klass.isAssignableFrom(Class.forName("Nonterminal")))
					throw new RuntimeException(
							"user defined Nonterminal class does not inherit System Nonterminal class");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public boolean isNormalSymbol(String symbol) {
		return this.normalSymbolIndices.containsKey(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return this.terminalSymbolIndices.containsKey(symbol);
	}

	public boolean isNonterminalSymbol(String symbol) {
		return this.nonterminalSymbolRuleIndices.containsKey(symbol);
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(this.rules.stream().filter(e -> e.equals(nonterminalSymbol)).toList());
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), this.rules.stream().map(e -> e.toString()).toList());
	}

	public static void main(String[] args) {
		String[] terminals = { "IDENT", "NUMBER" };
		Grammar grammar = new Grammar("Exp", Arrays.asList(terminals));
		grammar.add(new Rule("Exp", "+", "Exp", "Exp"));
		grammar.add(new Rule("Exp", "+", "Exp", "Exp"));

		System.out.println(grammar);
	}
}
