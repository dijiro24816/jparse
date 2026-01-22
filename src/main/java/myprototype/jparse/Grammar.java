package myprototype.jparse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Grammar {
	private String begSymbol;
	private String endSymbol;

	// We can implements like this, because Java resolve the collision by the Chaining method 
	private HashSet<String> specificTerminalSymbolSet;
	private List<String> specificTerminalSymbolList;
	private HashSet<String> generalTerminalSymbolSet;
	private List<String> generalTerminalSymbolList;
	private HashMap<String, Integer> terminalSymbolIndices;

	private HashMap<String, Integer> nonterminalSymbolIndices;
	private List<String> nonterminalSymbols;
	private HashMap<Rule, Integer> ruleIndices;
	private List<Rule> rules;

	public List<Rule> getRules() {
		return rules;
	}

	public String getStartSymbol() {
		return this.begSymbol;
	}

	public String getEndSymbol() {
		return endSymbol;
	}

	public int getTerminalSymbolCount() {
		return this.terminalSymbolIndices.size();
	}

	public int getNonterminalSymbolCount() {
		return this.nonterminalSymbolIndices.size();
	}

	public Grammar(String startSymbol, String endSymbol, Collection<String> terminalSymbols, Rule... rules) {
		this.begSymbol = startSymbol;
		this.endSymbol = endSymbol;

		this.specificTerminalSymbolSet = new HashSet<String>();
		this.specificTerminalSymbolList = new ArrayList<String>();
		this.generalTerminalSymbolSet = new HashSet<String>();
		this.generalTerminalSymbolList = new ArrayList<String>();
		this.terminalSymbolIndices = new HashMap<String, Integer>();
		cacheGeneralTerminalSymbolWithIndex(endSymbol); // terminal symbol contains end of symbol
		terminalSymbols.stream().forEach(e -> cacheGeneralTerminalSymbolWithIndex(e));

		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
		this.nonterminalSymbols = new ArrayList<String>();
		this.ruleIndices = new HashMap<Rule, Integer>();
		this.rules = new ArrayList<Rule>();

		for (Rule rule : rules) {
			cacheNonterminalSymbolWithIndex(rule.getProductSymbol());
			if (!cacheRuleWithIndex(rule))
				throw new RuntimeException("Error: Duplicated Rule: " + rule);
		}

		// DON'T MERGE FOR rules LOOP

		for (Rule rule : rules)
			for (String symbol : rule.getSymbols())
				if (!isTerminalSymbol(symbol) && !isNonterminalSymbol(symbol))
					// We should call this method after saving all of nonterminal symbol has done
					cacheSpecificTerminalSymbolWithIndex(symbol);
	}

	private boolean cacheNonterminalSymbolWithIndex(String symbol) {
		if (this.nonterminalSymbolIndices.containsKey(symbol))
			return false;
		this.nonterminalSymbolIndices.put(symbol, this.nonterminalSymbolIndices.size());
		this.nonterminalSymbols.add(symbol);
		return true;
	}

	private boolean cacheGeneralTerminalSymbolWithIndex(String symbol) {
		if (this.generalTerminalSymbolSet.contains(symbol))
			return false;
		this.generalTerminalSymbolSet.add(symbol);
		this.generalTerminalSymbolList.add(symbol);
		cacheTerminalSymbolWithIndex(symbol);
		return true;
	}

	private boolean cacheSpecificTerminalSymbolWithIndex(String symbol) {
		if (this.specificTerminalSymbolSet.contains(symbol))
			return false;
		this.specificTerminalSymbolSet.add(symbol);
		this.specificTerminalSymbolList.add(symbol);
		cacheTerminalSymbolWithIndex(symbol);
		return true;
	}

	private boolean cacheTerminalSymbolWithIndex(String symbol) {
		if (this.terminalSymbolIndices.containsKey(symbol))
			return false;
		this.terminalSymbolIndices.put(symbol, getTerminalSymbolCount());
		return true;
	}

	private boolean cacheRuleWithIndex(Rule rule) {
		if (this.ruleIndices.containsKey(rule))
			return false;
		this.ruleIndices.put(rule, this.ruleIndices.size());
		this.rules.add(rule);
		return true;
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

	public boolean isSpecificTerminalSymbol(String symbol) {
		return this.specificTerminalSymbolSet.contains(symbol);
	}

	public boolean isGeneralTerminalSymbol(String symbol) {
		return this.generalTerminalSymbolSet.contains(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return this.terminalSymbolIndices.containsKey(symbol);
	}

	public boolean isNonterminalSymbol(String symbol) {
		return this.nonterminalSymbolIndices.containsKey(symbol);
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(
				this.rules.stream().filter(e -> e.getProductSymbol().equals(nonterminalSymbol)).toList());
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

	public HashSet<Rule> expandSymbolsRules(String... symbols) {
		return expandSymbolsRules(Arrays.asList(symbols));
	}

	public HashSet<Rule> expandSymbolsRules(Collection<String> symbols) {
		HashSet<Rule> symbolsRules = new HashSet<>();
		ArrayDeque<String> nonterminalSymbols = new ArrayDeque<>(symbols);
		HashSet<String> foundNonterminals = new HashSet<>(symbols);

		String nonterminalSymbol;
		while ((nonterminalSymbol = nonterminalSymbols.poll()) != null) {
			Set<Rule> symbolRules = getRulesOf(nonterminalSymbol);
			symbolsRules.addAll(symbolRules);

			for (Rule rule : symbolRules)
				for (String symbol : rule.getSymbols())
					if (isNonterminalSymbol(symbol) && foundNonterminals.add(symbol))
						nonterminalSymbols.add(symbol);
		}

		return symbolsRules;
	}

	public HashSet<Item> expandFirstItems() {
		HashSet<String> lookaheadSet = new HashSet<>();
		lookaheadSet.add(getEndSymbol());
		return new HashSet<>(
				expandSymbolsRules(getStartSymbol()).stream().map(e -> new Item(e, lookaheadSet)).toList());
	}

	public List<Item> expandItems(HashSet<String> lookAheadSet, Item... orgItems) {
		return expandItems(lookAheadSet, Arrays.asList(orgItems));
	}

	public List<Item> expandItems(HashSet<String> lookaheadSet, Collection<Item> items) {

		// TODO: implements lookaheadSet inheritance

		return new ArrayList<>(
				expandSymbolsRules(
						items.stream()
								.map(e -> e.getDotSymbol()).toList()).stream()
										.map(e -> new Item(e, lookaheadSet))
										.toList());
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), this.rules.stream().map(e -> e.toString()).toList());
	}

	public static void main(String[] args) {
		//		HashSet<String> h1 = new HashSet();
		//		HashSet<String> h2 = new HashSet();
		//		
		//		h1.add("hello");
		//		h2.add("world");
		//		
		//		System.out.println(Stream.concat(h1.stream(), h2.stream()).toList());
		//		System.out.println(new HashSet<String>(Stream.concat(h1.stream(), h2.stream()).toList()));
		//		
		//		System.out.println(h1);
		//		System.out.println(h2);
		//		
		//		
		//		System.exit(0);

		OperatorPrecedenceRule operatorPrecedenceRule = new OperatorPrecedenceRule();
		operatorPrecedenceRule.add(PrecedenceDirection.Right, "=");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "+", "-");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "*", "/");

		PrecedenceRuleInfo info = operatorPrecedenceRule.getInfo("=");

		String[] terminals = { "ID", "NUM" };
		Grammar grammar = new Grammar("S", "$", Arrays.asList(terminals),
				new Rule("S", "Stmt"),
				new Rule("Stmt", "Expr"),
				new Rule("Stmt", "Assg"),
				new Rule("Expr", "ID"),
				new Rule("Expr", "NUM"),
				new Rule(operatorPrecedenceRule.getInfo("+"), "Expr", "+", "Expr", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("-"), "Expr", "-", "Expr", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("*"), "Expr", "*", "Expr", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("/"), "Expr", "/", "Expr", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("="), "Assg", "=", "ID", "Expr"));
		System.out.println(grammar);

		System.out.println(grammar.getTerminalSymbolsCSV());

		SyntaticsTable table = new SyntaticsTable(grammar);
		System.out.println(table);

		try {
			{
				FileWriter writer = new FileWriter("actions.csv");
				writer.write(table.getActionsCSV(grammar));
				writer.flush();
			}
			{
				FileWriter writer = new FileWriter("gotos.csv");
				writer.write(table.getGotosCSV(grammar));
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(table.getActionsCSV(grammar));

		System.out.println(table.getGotosCSV(grammar));

		System.out.println("FINISHED!");
	}

	public String getTerminalSymbolsCSV() {
		return String.join(",",
				Stream.concat(this.generalTerminalSymbolList.stream(), this.specificTerminalSymbolList.stream())
						.map(e -> "\"" + e + "\"").toList());
	}

	public String getNonterminalSymbolsCSV() {
		return String.join(",", this.nonterminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}
}
