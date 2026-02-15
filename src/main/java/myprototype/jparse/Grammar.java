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

public class Grammar {
	public static void maina(String[] args) {
		OperatorPrecedenceRule operatorPrecedenceRule = new OperatorPrecedenceRule();
		operatorPrecedenceRule.add(PrecedenceDirection.Right, "=");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "+", "-");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "*", "/");

		Grammar grammar = new Grammar("S", "$", 
				new Rule("S", "Stmt"),
				new Rule("Stmt"),
				new Rule("Stmt", "Expr"),
				new Rule("Stmt", "Assg"),
				new Rule("Expr", "Identifier"),
				new Rule("Expr", "NUM"),
				new Rule(operatorPrecedenceRule.getInfo("+"), "Expr", "Expr", "+", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("-"), "Expr", "Expr", "-", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("*"), "Expr", "Expr", "*", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("/"), "Expr", "Expr", "/", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("="), "Assg", "Identifier", "=", "Expr")
				);

		System.out.println("*** Grammar ***");
		System.out.println(grammar);

		SyntaticsTable syntaticsTable = new SyntaticsTable(grammar);

		System.out.println(syntaticsTable.getActionsCSV(grammar));
		System.out.println(syntaticsTable.getGotosCSV(grammar));

		try {
			{
				FileWriter writer = new FileWriter("actions.csv");
				writer.write(syntaticsTable.getActionsCSV(grammar));
				writer.flush();
				writer.close();
			}
			{
				FileWriter writer = new FileWriter("gotos.csv");
				writer.write(syntaticsTable.getGotosCSV(grammar));
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void mainb(String[] args) {
		// HashSet<String> h1 = new HashSet();
		// HashSet<String> h2 = new HashSet();
		//
		// h1.add("hello");
		// h2.add("world");
		//
		// System.out.println(Stream.concat(h1.stream(), h2.stream()).toList());
		// System.out.println(new HashSet<String>(Stream.concat(h1.stream(),
		// h2.stream()).toList()));
		//
		// System.out.println(h1);
		// System.out.println(h2);
		//
		//
		// System.exit(0);
		OperatorPrecedenceRule operatorPrecedenceRule = new OperatorPrecedenceRule();
		operatorPrecedenceRule.add(PrecedenceDirection.Right, "=");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "+", "-");
		operatorPrecedenceRule.add(PrecedenceDirection.Left, "*", "/");

		PrecedenceRuleInfo info = operatorPrecedenceRule.getInfo("=");

//		String[] terminals = { "Identifier", "NUM" };
		Grammar grammar = new Grammar("S", "$", new Rule("S", "Stmt"), new Rule("Stmt", "Expr"),
				new Rule("Stmt", "Assg"), new Rule("Expr", "Identifier"), new Rule("Expr", "NUM"),
				new Rule(operatorPrecedenceRule.getInfo("+"), "Expr", "Expr", "+", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("-"), "Expr", "Expr", "-", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("*"), "Expr", "Expr", "*", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("/"), "Expr", "Expr", "/", "Expr"),
				new Rule(operatorPrecedenceRule.getInfo("="), "Assg", "ID", "=", "Expr"));
		System.out.println(grammar);

		System.out.println(grammar.getTerminalSymbolsCSV());

		SyntaticsTable table = new SyntaticsTable(grammar);
		System.out.println(table);

		try {
			{
				FileWriter writer = new FileWriter("actions.csv");
				writer.write(table.getActionsCSV(grammar));
				writer.flush();
				writer.close();
			}
			{
				FileWriter writer = new FileWriter("gotos.csv");
				writer.write(table.getGotosCSV(grammar));
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(table.getActionsCSV(grammar));

		System.out.println(table.getGotosCSV(grammar));

		System.out.println("FINISHED!");
	}

	private String begSymbol;
	private String endSymbol;

	private HashMap<String, Integer> nonterminalSymbolIndices;
	private List<String> nonterminalSymbols;
	private HashMap<Rule, Integer> ruleIndices;
	private List<Rule> rules;

	private HashMap<String, Integer> terminalSymbolIndices;

	private List<String> terminalSymbols;

	public Grammar(String startSymbol, String endSymbol, Rule... rules) {
		this.begSymbol = startSymbol;
		this.endSymbol = endSymbol;

		this.terminalSymbols = new ArrayList<String>();
		this.terminalSymbolIndices = new HashMap<String, Integer>();
		cacheTerminalSymbolWithIndex(endSymbol); // terminal symbol contains end of symbol

		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
		this.nonterminalSymbols = new ArrayList<String>();
		cacheNonterminalSymbolWithIndex(startSymbol);

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
				if (!isNonterminalSymbol(symbol))
					// We should call this method after saving all of nonterminal symbol has done
					cacheTerminalSymbolWithIndex(symbol);
	}

	private boolean cacheNonterminalSymbolWithIndex(String symbol) {
		if (this.nonterminalSymbolIndices.containsKey(symbol))
			return false;
		this.nonterminalSymbolIndices.put(symbol, this.nonterminalSymbolIndices.size());
		this.nonterminalSymbols.add(symbol);
		return true;
	}

	private boolean cacheRuleWithIndex(Rule rule) {
		if (this.ruleIndices.containsKey(rule))
			return false;
		this.ruleIndices.put(rule, this.ruleIndices.size());
		this.rules.add(rule);
		return true;
	}

	private boolean cacheTerminalSymbolWithIndex(String symbol) {
		if (this.terminalSymbolIndices.containsKey(symbol))
			return false;
		this.terminalSymbolIndices.put(symbol, this.terminalSymbolIndices.size());
		this.terminalSymbols.add(symbol);
		return true;
	}

	public HashSet<Item> expandFirstItems() {
		HashSet<Item> items = new HashSet<>(
				expandSymbolsRules(getStartSymbol()).stream().map(e -> new Item(e)).toList());
		HashSet<Item> dstItems = new HashSet<>();
		for (Item item : items) {
			HashSet<String> lookaheadSet = getLookaheadSetFromItems(items, item.getRule().getProductSymbol());
			lookaheadSet.add("$");
			dstItems.add(new Item(item.getRule(), lookaheadSet));
		}

//		HashSet<String> lookaheadSet = new HashSet<>();
//		lookaheadSet.add(getEndSymbol());
//		HashSet<Item> items = expandItems(expandSymbolsRules(getStartSymbol()).stream().map(e -> new Item(e, lookaheadSet)).toList());
		
		return dstItems;

//		HashSet<String> lookaheadSet = new HashSet<>();
//		lookaheadSet.add(getEndSymbol());
//		return new HashSet<>(
//				expandSymbolsRules(getStartSymbol()).stream().map(e -> new Item(e, lookaheadSet)).toList());
	}

	public HashSet<Item> expandItems(Collection<Item> orgItems) {
//		List<String> symbols = getDotSymbols(orgItems);
//		
//		System.out.println("dot symbols: " + symbols);
//		HashSet<Rule> expandedRules = new HashSet<>();
//		HashSet<Item> expandedItems = new HashSet<>(orgItems);
//		
//		for (Rule rule : expandSymbolsRules(symbols))
//			if (expandedRules.add(rule)) {
//				// We must replace expandedItems with new items
//				Item item = new Item(rule, getLookaheadSetFromItems(expandedItems, rule.getProductSymbol()));
////				expandedItems.remove(item);
//				expandedItems.add(item);
//			}
//		return new ArrayList(expandedItems);

		HashSet<Rule> rules = expandSymbolsRules(getDotSymbols(orgItems));

		HashSet<Item> items = new HashSet<>();
		items.addAll(orgItems);
		items.addAll(rules.stream().map(e -> new Item(e)).toList());

		HashSet<Item> dstItems = new HashSet<>(orgItems);
		for (Rule rule : rules)
			dstItems.add(new Item(rule, getLookaheadSetFromItems(items, rule.getProductSymbol())));

//		System.out.println("-----------------------------");
//		System.out.println(dstItems);
		
		for (Item item : dstItems) {
			if (item.getLookaheadSet().isEmpty())
				System.out.println(item);
		}
		
		return dstItems;
	}

	public HashSet<Item> expandItems(Item... orgItems) {
		return expandItems(Arrays.asList(orgItems));
	}

	public HashSet<Rule> expandSymbolsRules(Collection<String> symbols) {
		HashSet<Rule> symbolsRules = new HashSet<>();
		ArrayDeque<String> nonterminalSymbols = new ArrayDeque<>(symbols);
		HashSet<String> foundNonterminals = new HashSet<>(symbols);

		String nonterminalSymbol;
		while ((nonterminalSymbol = nonterminalSymbols.poll()) != null) {
			Set<Rule> symbolRules = getRulesOf(nonterminalSymbol);
			symbolsRules.addAll(symbolRules);
			
//			System.out.println(symbolRules);

			for (Rule rule : symbolRules) {
				if (rule.isEmpty())
					continue;
				
				String symbol = rule.getFirstSymbol();
				if (isNonterminalSymbol(symbol) && foundNonterminals.add(symbol))
					nonterminalSymbols.add(symbol);
			}
		}
//		System.exit(0);
		return symbolsRules;
	}

	public HashSet<Rule> expandSymbolsRules(String... symbols) {
		return expandSymbolsRules(Arrays.asList(symbols));
	}

	public List<String> getDotSymbols(Collection<Item> items) {
		return items.stream().map(e -> e.getDotSymbol()).filter(e -> e != null).toList();
	}

	public String getEndSymbol() {
		return endSymbol;
	}

	// This implementation is very slow. but now, it's ok!
	private HashSet<String> getFirstSet(String symbol) {
		if (isTerminalSymbol(symbol)) {
			HashSet<String> symbols = new HashSet<>();
			symbols.add(symbol);
			return symbols;
		}

		return new HashSet<>(expandSymbolsRules(symbol).stream().map(e -> e.getFirstSymbol())
				.filter(e -> isTerminalSymbol(e)).toList());
	}

	public HashSet<String> getLookaheadSetFromItems(Collection<Item> items, String targetSymbol) {
		HashSet<String> lookaheadSet = new HashSet<>();

		ArrayList<String> symbols = new ArrayList<>();
		symbols.add(targetSymbol);

		boolean found;
		do {
			found = false;

			String currentSymbol = symbols.get(symbols.size() - 1);

			for (Item item : items) {
//				System.out.println(item);
				if (item.isReplaceable() && item.getDotSymbol().equals(currentSymbol)) {
					symbols.add(item.getProductSymbol());
					found = true;
					break;
				}
			}

		} while (found);

		List<Item> orgItems = items.stream().filter(e -> e.getDotSymbol() != null)
				.filter(e -> symbols.stream().anyMatch(s -> s.equals(e.getDotSymbol()))).toList();
//		List<Item> orgItems = items.stream().filter(e -> e.getDotSymbol() != null).filter(e -> symbols.anyMatch(e.getDotSymbol()) e.getDotSymbol().equals(targetSymbol)).toList();

		for (Item item : orgItems) {

			if (item.isReachingLastSymbol())
				// Nonterm1 -> . Nonterm2
				// add Lookahead-Set(Nonterm2)
				lookaheadSet.addAll(item.getLookaheadSet());
			else
				// Nonterm -> . Nonterm Term
				lookaheadSet.addAll(getFirstSet(item.getDotNextSymbol()));
		}

//		if (new HashSet<>(orgItems.stream().map(e -> e.getProductSymbol()).toList()).contains("Stmt")) {
//			System.out.println(items);
//			System.out.println(symbol);
//			System.out.println(lookaheadSet);
////			System.exit(0);
//		}
		return lookaheadSet;
	}

	public int getNonterminalSymbolCount() {
		return this.nonterminalSymbolIndices.size();
	}

	public int getNonterminalSymbolIndexOf(String nonterminalSymbol) {
		return this.nonterminalSymbolIndices.get(nonterminalSymbol);
	}

	public String getNonterminalSymbolsCSV() {
		return String.join(",", this.nonterminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}

	public int getRuleIndexOf(Rule rule) {
		return this.ruleIndices.get(rule);
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(
				this.rules.stream().filter(e -> e.getProductSymbol().equals(nonterminalSymbol)).toList());
	}

	public String getStartSymbol() {
		return this.begSymbol;
	}

	public int getTerminalSymbolCount() {
		return this.terminalSymbolIndices.size();
	}

	public int getTerminalSymbolIndexOf(String terminalSymbol) {
		return this.terminalSymbolIndices.get(terminalSymbol);
	}

	public String getTerminalSymbolsCSV() {
		return String.join(",", this.terminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}

	public boolean isEndSymbol(Symbol symbol) {
		return symbol.getLabel().equals(this.endSymbol);
	}

	public boolean isNonterminalSymbol(String symbol) {
		return this.nonterminalSymbolIndices.containsKey(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return this.terminalSymbolIndices.containsKey(symbol);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < this.rules.size(); i++) {
			stringBuilder.append(i);
			stringBuilder.append(": ");
			stringBuilder.append(this.rules.get(i));
			
			if (i + 1 < this.rules.size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
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
}
