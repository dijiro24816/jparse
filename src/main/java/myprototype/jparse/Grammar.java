package myprototype.jparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grammar {
	private Set<String> terminalSymbolSet;
	private Set<String> nonterminalSymbolSet;
	private Set<String> normalSymbolSet;
	private Set<Rule> ruleSet;
	private List<Rule> rules;
	
	public List<Rule> getRules() {
		return rules;
	}

	public Grammar(Collection<String> terminalSymbols) {
		this.terminalSymbolSet = new HashSet<String>(terminalSymbols);
		this.nonterminalSymbolSet = new HashSet<String>();
		this.normalSymbolSet = new HashSet<String>();
		this.ruleSet = new HashSet<Rule>();
		this.rules = new ArrayList<Rule>();
	}

	public void add(Rule rule) {
		if (!this.ruleSet.add(rule)) {
			System.out.println("WARN: DUPLICATED: " + rule);
			return;
		}

		this.rules.add(rule);

		this.nonterminalSymbolSet.add(rule.getProductSymbol());

		for (String symbol : rule.getSymbols())
			if (!this.terminalSymbolSet.contains(symbol) || !this.nonterminalSymbolSet.contains(symbol))
				this.normalSymbolSet.add(symbol);
	}

	public boolean isInvalid() {
		for (Rule rule : this.ruleSet)
			for (String symbol : rule.getSymbols())
				if (isNormalSymbol(symbol) && isTerminalSymbol(symbol) && isNonterminalSymbol(symbol))
					return false;

		return true;
	}

	public boolean isNormalSymbol(String symbol) {
		return this.normalSymbolSet.contains(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return this.terminalSymbolSet.contains(symbol);
	}

	public boolean isNonterminalSymbol(String symbol) {
		return this.nonterminalSymbolSet.contains(symbol);
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(this.ruleSet.stream().filter(e -> e.equals(nonterminalSymbol)).toList());
	}
	
	

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), this.rules.stream().map(e -> e.toString()).toList());
	}

	public static void main(String[] args) {
		String[] terminals = { "IDENT", "NUMBER" };
		Grammar grammar = new Grammar(Arrays.asList(terminals));
		grammar.add(new Rule("Exp", stack -> {
			return null;
		}, "+", "Exp", "Exp"));
		grammar.add(new Rule("Exp", stack -> {
			return null;
		}, "+", "Exp", "Exp"));

		System.out.println(grammar);
	}
}
