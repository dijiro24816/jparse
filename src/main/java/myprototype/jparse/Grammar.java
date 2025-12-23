package myprototype.jparse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Grammar {
	private String startSymbol;

	// We can implements like this, because Java resolve the collision by the Chaining method 
	private HashMap<String, Integer> normalSymbolIndices;
	private List<String> normalSymbols;
	private HashMap<String, Integer> terminalSymbolIndices;
	private List<String> terminalSymbols;
	private HashMap<String, Integer> nonterminalSymbolIndices;
	private List<String> nonterminalSymbols;
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
	
	public int getNormalAndTerminalSymbolCount() {
		return this.normalSymbolIndices.size() + this.terminalSymbolIndices.size();
	}
	
	public int getNonterminalSymbolCount() {
		return this.nonterminalSymbolIndices.size();
	}

	public Grammar(String startSymbol, Collection<String> terminalSymbols) {
		this.startSymbol = startSymbol;
		this.normalSymbolIndices = new HashMap<String, Integer>();
		this.normalSymbols = new ArrayList<String>();
		this.terminalSymbolIndices = new HashMap<String, Integer>();
		this.terminalSymbols= new ArrayList<String>();
		terminalSymbols.stream().forEach(e -> beCacheTerminalSymbolWithIndex(e));
		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
		this.nonterminalSymbols = new ArrayList<String>();
		this.ruleIndices = new HashMap<Rule, Integer>();
		this.rules = new ArrayList<Rule>();
	}

	private boolean beCacheNonterminalSymbolWithIndex(String symbol) {
		if (this.nonterminalSymbolIndices.containsKey(symbol))
			return false;
		this.nonterminalSymbolIndices.put(symbol, this.nonterminalSymbolIndices.size());
		this.nonterminalSymbols.add(symbol);
		return true;
	}

	private boolean beCacheNormalSymbolWithIndex(String symbol) {
		if (this.normalSymbolIndices.containsKey(symbol))
			return false;
		this.normalSymbolIndices.put(symbol, getNormalAndTerminalSymbolCount());
		this.normalSymbols.add(symbol);
		return true;
	}

	private boolean beCacheTerminalSymbolWithIndex(String symbol) {
		if (this.terminalSymbolIndices.containsKey(symbol))
			return false;
		this.terminalSymbolIndices.put(symbol, getNormalAndTerminalSymbolCount());
		this.terminalSymbols.add(symbol);
		return true;
	}
	
	private boolean beCacheRuleWithIndex(Rule rule) {
		if (this.ruleIndices.containsKey(rule))
			return false;
		this.ruleIndices.put(rule, this.ruleIndices.size());
		this.rules.add(rule);
		return true;
	}

	public void define(Rule rule) {
		if (this.ruleIndices.containsKey(rule.getProductSymbol())) {
			System.out.println("WARN: DUPLICATED: " + rule);
			return;
		}

		// Add rule with index
		beCacheNonterminalSymbolWithIndex(rule.getProductSymbol());
		beCacheRuleWithIndex(rule);

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
	
	public boolean isNormalOrTerminalSymbol(String symbol) {
		return isNormalSymbol(symbol) || isTerminalSymbol(symbol);
	}

	public boolean isNonterminalSymbol(String symbol) {
		return this.nonterminalSymbolIndices.containsKey(symbol);
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
	
	public int getNormalOrTerminalSymbolIndexOf(String symbol) {
		return isTerminalSymbol(symbol) ? getTerminalSymbolIndexOf(symbol)
				: getNormalSymbolIndexOf(symbol);
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
		grammar.define(new Rule("S", "Stmt"));
		grammar.define(new Rule("Stmt", "Exp"));
		grammar.define(new Rule("Stmt", "Assg"));
		grammar.define(new Rule("Exp", "ID"));
		grammar.define(new Rule("Exp", "NUM"));
		grammar.define(new Rule("Exp", "+", "Exp", "Exp"));
		grammar.define(new Rule("Exp", "-", "Exp", "Exp"));
		grammar.define(new Rule("Exp", "*", "Exp", "Exp"));
		grammar.define(new Rule("Exp", "/", "Exp", "Exp"));
		grammar.define(new Rule("Assg", "int", "ID", "Exp"));
		System.out.println(grammar);

		SyntaticsTable table = new SyntaticsTable(grammar);
		System.out.println(table);
		
		
		try {
			FileWriter writer = new FileWriter("actions.csv");
			writer.write(table.getActionsCSV(grammar));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(table.getActionsCSV(grammar));
		
		System.out.println(table.getGotosCSV(grammar));
		
		System.out.println("FINISHED!");
	}
	
	public String getNormalOrTerminalSymbolsCSV() {
		return String.join(",", Stream.concat(this.terminalSymbols.stream(), this.normalSymbols.stream()).map(e -> "\"" + e + "\"").toList());
	}
	
	public String getNonterminalSymbolsCSV() {
		return String.join(",", this.nonterminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}
}
