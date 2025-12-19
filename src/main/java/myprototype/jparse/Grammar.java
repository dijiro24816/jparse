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
	private HashMap<String, Integer> nonterminalSymbolIndices;
	private HashMap<Rule, Integer> ruleIndices;
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
		terminalSymbols.stream().forEach(e -> beCacheTerminalSymbolWithIndex(e));
		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
		this.ruleIndices = new HashMap<Rule, Integer>();
		this.rules = new ArrayList<Rule>();
	}

	private boolean beCacheNonterminalSymbolWithIndex(String symbol) {
		if (this.nonterminalSymbolIndices.containsKey(symbol))
			return false;
		this.nonterminalSymbolIndices.put(symbol, this.nonterminalSymbolIndices.size());
		return true;
	}

	private boolean beCacheRuleWithIndex(Rule rule) {
		if (this.ruleIndices.containsKey(rule))
			return false;
		this.ruleIndices.put(rule, this.ruleIndices.size());
		return true;
	}

	private boolean beCacheNormalSymbolWithIndex(String symbol) {
		if (this.normalSymbolIndices.containsKey(symbol))
			return false;
		this.normalSymbolIndices.put(symbol, getNormalAndTerminalCount());
		return true;
	}

	private boolean beCacheTerminalSymbolWithIndex(String symbol) {
		if (this.terminalSymbolIndices.containsKey(symbol))
			return false;
		this.terminalSymbolIndices.put(symbol, getNormalAndTerminalCount());
		return true;
	}

	public int getNormalAndTerminalCount() {
		return this.normalSymbolIndices.size() + this.terminalSymbolIndices.size();
	}

	public void add(Rule rule) {
		if (this.ruleIndices.containsKey(rule.getProductSymbol())) {
			System.out.println("WARN: DUPLICATED: " + rule);
			return;
		}

		// Add rule with index
		beCacheNonterminalSymbolWithIndex(rule.getProductSymbol());
		beCacheRuleWithIndex(rule);
		this.rules.add(rule);

		for (String symbol : rule.getSymbols())
			if ((!isTerminalSymbol(symbol) || !isNonterminalSymbol(symbol)) &&
					!this.normalSymbolIndices.containsKey(symbol))
				beCacheNormalSymbolWithIndex(symbol);
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
		return this.ruleIndices.containsKey(symbol);
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(this.rules.stream().filter(e -> e.getProductSymbol().equals(nonterminalSymbol)).toList());
	}

	public int getNormalSymbolIndexOf(String normalSymbol) {
		return this.normalSymbolIndices.get(normalSymbol);
	}

	public int getTerminalSymbolIndexOf(String terminalSymbol) {
		return this.terminalSymbolIndices.get(terminalSymbol);
	}

	public int getNonterminalSymbolIndexOf(String nonterminalSymbol) {
		return this.nonterminalSymbolIndices.get(nonterminalSymbol);
	}

	public int getRuleIndexOf(Rule rule) {
		return this.ruleIndices.get(rule);
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), this.rules.stream().map(e -> e.toString()).toList());
	}

	public static void main(String[] args) {
		String[] terminals = { "ID", "NUM" };
		Grammar grammar = new Grammar("S", Arrays.asList(terminals));
		grammar.add(new Rule("S", "Stmt"));
		grammar.add(new Rule("Stmt", "Exp"));
		grammar.add(new Rule("Stmt", "Assg"));
		grammar.add(new Rule("Exp", "ID"));
		grammar.add(new Rule("Exp", "NUM"));
		grammar.add(new Rule("Exp", "+", "Exp", "Exp"));
		grammar.add(new Rule("Exp", "-", "Exp", "Exp"));
		grammar.add(new Rule("Exp", "*", "Exp", "Exp"));
		grammar.add(new Rule("Exp", "/", "Exp", "Exp"));
		grammar.add(new Rule("Assg", "int", "ID", "Exp"));
		System.out.println(grammar);

		SyntaticsTable table = new SyntaticsTable(grammar);
		System.out.println(table);
		
		System.out.println("FINISHED!");
	}
}
