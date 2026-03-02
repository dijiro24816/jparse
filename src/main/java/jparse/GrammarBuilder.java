package jparse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GrammarBuilder {
	private HashMap<String, Integer> nonterminalSymbolIndices;
	private List<String> nonterminalSymbols;
	private HashMap<Rule, Integer> ruleIndices;
	private List<Rule> rules;
	private HashMap<String, Integer> terminalSymbolIndices;
	private List<String> terminalSymbols;

	public GrammarBuilder()	{
		this("$");
	}
	
	public GrammarBuilder(String endSymbol) {
		this.terminalSymbols = new ArrayList<String>();
		this.terminalSymbolIndices = new HashMap<String, Integer>();
		cacheTerminalSymbolWithIndex(endSymbol); // terminal symbol contains end of symbol

		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
		this.nonterminalSymbols = new ArrayList<String>();

		this.ruleIndices = new HashMap<Rule, Integer>();
		this.rules = new ArrayList<Rule>();
	}
	
	public GrammarBuilder add(List<Rule> rules) {
		for (Rule rule : rules) {
			cacheNonterminalSymbolWithIndex(rule.productSymbol());
			if (!cacheRuleWithIndex(rule))
				throw new RuntimeException("Error: Duplicated Rule: " + rule);

			if (rule.size() == 1 && rule.getFirstSymbol().equals(rule.productSymbol()))
				throw new RuntimeException("Error: Invalid Rule: " + rule);
		}

		// DON'T MERGE FOR rules LOOP

		for (Rule rule : rules)
			for (String symbol : rule.symbols())
				if (!isNonterminalSymbol(symbol))
					// We should call this method after saving all of nonterminal symbol has done
					cacheTerminalSymbolWithIndex(symbol);

		return this;
	}

	public GrammarBuilder add(Rule... rules) {
		return add(Arrays.asList(rules));
	}

	public Grammar build() {
		if (getRules().size() == 0)
			throw new RuntimeException("Grammar: Empty rule!");
		
		return build(getNonterminalSymbols().get(0));
	}

	public Grammar build(String productSymbol) {
		return build(productSymbol, "$");
	}

	public Grammar build(String productSymbol, String endSymbol) {
		
		
		return new Grammar(productSymbol, endSymbol, getTerminalSymbolIndices(), getTerminalSymbols(),
				getNonterminalSymbolIndices(), getNonterminalSymbols(), getRuleIndices(), getRules());
	}

	private boolean cacheNonterminalSymbolWithIndex(String symbol) {
		if (getNonterminalSymbolIndices().containsKey(symbol))
			return false;
		getNonterminalSymbolIndices().put(symbol, getNonterminalSymbolIndices().size());
		getNonterminalSymbols().add(symbol);
		return true;
	}

	private boolean cacheRuleWithIndex(Rule rule) {
		if (getRuleIndices().containsKey(rule))
			return false;
		getRuleIndices().put(rule, getRuleIndices().size());
		getRules().add(rule);
		return true;
	}

	private boolean cacheTerminalSymbolWithIndex(String symbol) {
		if (getTerminalSymbolIndices().containsKey(symbol))
			return false;
		getTerminalSymbolIndices().put(symbol, getTerminalSymbolIndices().size());
		getTerminalSymbols().add(symbol);
		return true;
	}

	public HashMap<String, Integer> getNonterminalSymbolIndices() {
		return nonterminalSymbolIndices;
	}

	public List<String> getNonterminalSymbols() {
		return nonterminalSymbols;
	}

	public HashMap<Rule, Integer> getRuleIndices() {
		return ruleIndices;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public HashMap<String, Integer> getTerminalSymbolIndices() {
		return terminalSymbolIndices;
	}

	public List<String> getTerminalSymbols() {
		return terminalSymbols;
	}

	public boolean isNonterminalSymbol(String symbol) {
		return getNonterminalSymbolIndices().containsKey(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return getTerminalSymbolIndices().containsKey(symbol);
	}

	public GrammarBuilder resource(InputStream inStrm) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStrm));

		return add(reader.lines().filter(e -> !e.isBlank() && !e.trim().startsWith("#")).map(e -> {
			List<String> srcs = Arrays.asList(e.split("->", 2));
			String productSymbol = srcs.get(0).trim();
			ArrayList<String> symbols = new ArrayList<>();
			if (srcs.size() > 1)
				symbols.addAll(Arrays.asList(srcs.get(1).trim().split("\s+")));

			return new Rule(productSymbol, symbols);
		}).toList());
	}

	public GrammarBuilder resourceFile(String fileName) throws FileNotFoundException {
		return resource(new FileInputStream(fileName));
	}
	
	public GrammarBuilder resourceString(String string) {
		return resource(new ByteArrayInputStream(string.getBytes()));
	}
}
