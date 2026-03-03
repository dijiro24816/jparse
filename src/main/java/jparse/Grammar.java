package jparse;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Grammar(String productSymbol, String endSymbol, HashMap<String, Integer> terminalSymbolIndices, List<String> terminalSymbols, HashMap<String, Integer> nonterminalSymbolIndices,
		List<String> nonterminalSymbols, HashMap<Rule, Integer> ruleIndices, List<Rule> rules) implements Serializable {

	public static Grammar loadFile(String fileName) throws FileNotFoundException {
		return new GrammarBuilder().resourceFile(fileName).build();
	}
	
	public static Grammar loadFile(String fileName, String productSymbol) throws FileNotFoundException {
		return new GrammarBuilder().resourceFile(fileName).build(productSymbol);
	}
	
	public static Grammar loadFile(String fileName, String productSymbol, String endSymbol) throws FileNotFoundException {
		return new GrammarBuilder().resourceFile(fileName).build(productSymbol, endSymbol);
	}
	
	public static Grammar loadString(String string) {
		return new GrammarBuilder().resourceString(string).build();
	}
	
	public static Grammar loadString(String string, String begSymbol) {
		return new GrammarBuilder().resourceString(string).build(begSymbol);
	}
	
	public static Grammar loadString(String string, String begSymbol, String endSymbol) {
		return new GrammarBuilder().resourceString(string).build(begSymbol, endSymbol);
	}
	
	public HashSet<Item> expandFirstItems() {
		HashSet<Item> items = new HashSet<>(
				expandSymbolsRules(getProductSymbol()).stream().map(e -> new Item(e)).toList());
		HashSet<Item> dstItems = new HashSet<>();
		for (Item item : items) {
			HashSet<String> lookaheadSet = getLookaheadSetFromItems(items, item.getRule().productSymbol());
			lookaheadSet.add(getEndSymbol());
			dstItems.add(new Item(item.getRule(), lookaheadSet));
		}

		return dstItems;
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
			dstItems.add(new Item(rule, getLookaheadSetFromItems(items, rule.productSymbol())));

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
		return nonterminalSymbolIndices.size();
	}

	public int getNonterminalSymbolIndexOf(String nonterminalSymbol) {
		return nonterminalSymbolIndices.get(nonterminalSymbol);
	}

	public HashMap<String, Integer> getNonterminalSymbolIndices() {
		return nonterminalSymbolIndices;
	}

	public List<String> getNonterminalSymbols() {
		return nonterminalSymbols;
	}

	public String getNonterminalSymbolsCSV() {
		return String.join(",", nonterminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}

	public String getProductSymbol() {
		return productSymbol;
	}

	public int getRuleIndexOf(Rule rule) {
		return ruleIndices.get(rule);
	}

	public HashMap<Rule, Integer> getRuleIndices() {
		return ruleIndices;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public Set<Rule> getRulesOf(String nonterminalSymbol) {
		return new HashSet<Rule>(
				rules.stream().filter(e -> e.productSymbol().equals(nonterminalSymbol)).toList());
	}

	public int getTerminalSymbolCount() {
		return terminalSymbolIndices.size();
	}

	public int getTerminalSymbolIndexOf(String terminalSymbol) {
		return terminalSymbolIndices.get(terminalSymbol);
	}

	public HashMap<String, Integer> getTerminalSymbolIndices() {
		return terminalSymbolIndices;
	}

	public List<String> getTerminalSymbols() {
		return terminalSymbols;
	}

	public String getTerminalSymbolsCSV() {
		return String.join(",", terminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
	}

	public boolean isEndSymbol(Token symbol) {
		return symbol.label().equals(getEndSymbol());
	}

	public boolean isNonterminalSymbol(String symbol) {
		return getNonterminalSymbolIndices().containsKey(symbol);
	}

	public boolean isTerminalSymbol(String symbol) {
		return getTerminalSymbolIndices().containsKey(symbol);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < getRules().size(); i++) {
			stringBuilder.append(i);
			stringBuilder.append(": ");
			stringBuilder.append(getRules().get(i));

			if (i + 1 < getRules().size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}
}

//public class Grammar implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	private String endSymbol;
//	private HashMap<String, Integer> nonterminalSymbolIndices;
//
//	private List<String> nonterminalSymbols;
//	private String productSymbol;
//
//	private HashMap<Rule, Integer> ruleIndices;
//	private List<Rule> rules;
//
//	private HashMap<String, Integer> terminalSymbolIndices;
//	private List<String> terminalSymbols;
//
//	public Grammar() {
//		this.productSymbol = null;
//		this.endSymbol = null;
//		this.nonterminalSymbolIndices = null;
//		this.ruleIndices = null;
//		this.rules = null;
//		this.terminalSymbolIndices = null;
//		this.terminalSymbols = null;
//	}
//
//	public Grammar(String productSymbol) {
//		this(productSymbol, "$");
//	}
//
//	public Grammar(String productSymbol, String endSymbol) {
//		this.productSymbol = productSymbol;
//		this.endSymbol = endSymbol;
//
//		this.terminalSymbols = new ArrayList<String>();
//		this.terminalSymbolIndices = new HashMap<String, Integer>();
//		cacheTerminalSymbolWithIndex(endSymbol); // terminal symbol contains end of symbol
//
//		this.nonterminalSymbolIndices = new HashMap<String, Integer>();
//		this.nonterminalSymbols = new ArrayList<String>();
//		cacheNonterminalSymbolWithIndex(productSymbol);
//
//		this.ruleIndices = new HashMap<Rule, Integer>();
//		this.rules = new ArrayList<Rule>();
//	}
//
//	
//
//	public HashSet<Item> expandFirstItems() {
//		HashSet<Item> items = new HashSet<>(
//				expandSymbolsRules(getProductSymbol()).stream().map(e -> new Item(e)).toList());
//		HashSet<Item> dstItems = new HashSet<>();
//		for (Item item : items) {
//			HashSet<String> lookaheadSet = getLookaheadSetFromItems(items, item.getRule().productSymbol());
//			lookaheadSet.add(getEndSymbol());
//			dstItems.add(new Item(item.getRule(), lookaheadSet));
//		}
//
//		return dstItems;
//	}
//
//	public HashSet<Item> expandItems(Collection<Item> orgItems) {
//		//		List<String> symbols = getDotSymbols(orgItems);
//		//		
//		//		System.out.println("dot symbols: " + symbols);
//		//		HashSet<Rule> expandedRules = new HashSet<>();
//		//		HashSet<Item> expandedItems = new HashSet<>(orgItems);
//		//		
//		//		for (Rule rule : expandSymbolsRules(symbols))
//		//			if (expandedRules.add(rule)) {
//		//				// We must replace expandedItems with new items
//		//				Item item = new Item(rule, getLookaheadSetFromItems(expandedItems, rule.getProductSymbol()));
//		////				expandedItems.remove(item);
//		//				expandedItems.add(item);
//		//			}
//		//		return new ArrayList(expandedItems);
//
//		HashSet<Rule> rules = expandSymbolsRules(getDotSymbols(orgItems));
//
//		HashSet<Item> items = new HashSet<>();
//		items.addAll(orgItems);
//		items.addAll(rules.stream().map(e -> new Item(e)).toList());
//
//		HashSet<Item> dstItems = new HashSet<>(orgItems);
//		for (Rule rule : rules)
//			dstItems.add(new Item(rule, getLookaheadSetFromItems(items, rule.productSymbol())));
//
//		//		System.out.println("-----------------------------");
//		//		System.out.println(dstItems);
//		for (Item item : dstItems) {
//			if (item.getLookaheadSet().isEmpty())
//				System.out.println(item);
//		}
//
//		return dstItems;
//	}
//
//	public HashSet<Item> expandItems(Item... orgItems) {
//		return expandItems(Arrays.asList(orgItems));
//	}
//
//	public HashSet<Rule> expandSymbolsRules(Collection<String> symbols) {
//		HashSet<Rule> symbolsRules = new HashSet<>();
//		ArrayDeque<String> nonterminalSymbols = new ArrayDeque<>(symbols);
//		HashSet<String> foundNonterminals = new HashSet<>(symbols);
//
//		String nonterminalSymbol;
//		while ((nonterminalSymbol = nonterminalSymbols.poll()) != null) {
//			Set<Rule> symbolRules = getRulesOf(nonterminalSymbol);
//			symbolsRules.addAll(symbolRules);
//
//			//			System.out.println(symbolRules);
//
//			for (Rule rule : symbolRules) {
//				if (rule.isEmpty())
//					continue;
//
//				String symbol = rule.getFirstSymbol();
//				if (isNonterminalSymbol(symbol) && foundNonterminals.add(symbol))
//					nonterminalSymbols.add(symbol);
//			}
//		}
//		//		System.exit(0);
//		return symbolsRules;
//	}
//
//	public HashSet<Rule> expandSymbolsRules(String... symbols) {
//		return expandSymbolsRules(Arrays.asList(symbols));
//	}
//
//	public List<String> getDotSymbols(Collection<Item> items) {
//		return items.stream().map(e -> e.getDotSymbol()).filter(e -> e != null).toList();
//	}
//
//	public String getEndSymbol() {
//		return endSymbol;
//	}
//
//	// This implementation is very slow. but now, it's ok!
//	private HashSet<String> getFirstSet(String symbol) {
//		if (isTerminalSymbol(symbol)) {
//			HashSet<String> symbols = new HashSet<>();
//			symbols.add(symbol);
//			return symbols;
//		}
//
//		return new HashSet<>(expandSymbolsRules(symbol).stream().map(e -> e.getFirstSymbol())
//				.filter(e -> isTerminalSymbol(e)).toList());
//	}
//
//	public HashSet<String> getLookaheadSetFromItems(Collection<Item> items, String targetSymbol) {
//		HashSet<String> lookaheadSet = new HashSet<>();
//
//		ArrayList<String> symbols = new ArrayList<>();
//		symbols.add(targetSymbol);
//
//		boolean found;
//		do {
//			found = false;
//
//			String currentSymbol = symbols.get(symbols.size() - 1);
//
//			for (Item item : items) {
//				if (item.isReplaceable() && item.getDotSymbol().equals(currentSymbol)) {
//					symbols.add(item.getProductSymbol());
//					found = true;
//					break;
//				}
//			}
//
//		} while (found);
//
//		List<Item> orgItems = items.stream().filter(e -> e.getDotSymbol() != null)
//				.filter(e -> symbols.stream().anyMatch(s -> s.equals(e.getDotSymbol()))).toList();
//		//		List<Item> orgItems = items.stream().filter(e -> e.getDotSymbol() != null).filter(e -> symbols.anyMatch(e.getDotSymbol()) e.getDotSymbol().equals(targetSymbol)).toList();
//
//		for (Item item : orgItems) {
//
//			if (item.isReachingLastSymbol())
//				// Nonterm1 -> . Nonterm2
//				// add Lookahead-Set(Nonterm2)
//				lookaheadSet.addAll(item.getLookaheadSet());
//			else
//				// Nonterm -> . Nonterm Term
//				lookaheadSet.addAll(getFirstSet(item.getDotNextSymbol()));
//		}
//
//		//		if (new HashSet<>(orgItems.stream().map(e -> e.getProductSymbol()).toList()).contains("Stmt")) {
//		//			System.out.println(items);
//		//			System.out.println(symbol);
//		//			System.out.println(lookaheadSet);
//		////			System.exit(0);
//		//		}
//		return lookaheadSet;
//	}
//
//	public int getNonterminalSymbolCount() {
//		return nonterminalSymbolIndices.size();
//	}
//
//	public int getNonterminalSymbolIndexOf(String nonterminalSymbol) {
//		return nonterminalSymbolIndices.get(nonterminalSymbol);
//	}
//
//	public HashMap<String, Integer> getNonterminalSymbolIndices() {
//		return nonterminalSymbolIndices;
//	}
//
//	public List<String> getNonterminalSymbols() {
//		return nonterminalSymbols;
//	}
//
//	public String getNonterminalSymbolsCSV() {
//		return String.join(",", nonterminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
//	}
//
//	public String getProductSymbol() {
//		return productSymbol;
//	}
//
//	public int getRuleIndexOf(Rule rule) {
//		return ruleIndices.get(rule);
//	}
//
//	public HashMap<Rule, Integer> getRuleIndices() {
//		return ruleIndices;
//	}
//
//	public List<Rule> getRules() {
//		return rules;
//	}
//
//	public Set<Rule> getRulesOf(String nonterminalSymbol) {
//		return new HashSet<Rule>(
//				rules.stream().filter(e -> e.productSymbol().equals(nonterminalSymbol)).toList());
//	}
//
//	public int getTerminalSymbolCount() {
//		return terminalSymbolIndices.size();
//	}
//
//	public int getTerminalSymbolIndexOf(String terminalSymbol) {
//		return terminalSymbolIndices.get(terminalSymbol);
//	}
//
//	public HashMap<String, Integer> getTerminalSymbolIndices() {
//		return terminalSymbolIndices;
//	}
//
//	public List<String> getTerminalSymbols() {
//		return terminalSymbols;
//	}
//
//	public String getTerminalSymbolsCSV() {
//		return String.join(",", terminalSymbols.stream().map(e -> "\"" + e + "\"").toList());
//	}
//
//	public boolean isEndSymbol(Token symbol) {
//		return symbol.label().equals(getEndSymbol());
//	}
//
//	public boolean isNonterminalSymbol(String symbol) {
//		return getNonterminalSymbolIndices().containsKey(symbol);
//	}
//
//	public boolean isTerminalSymbol(String symbol) {
//		return getTerminalSymbolIndices().containsKey(symbol);
//	}
//
//	public Grammar resource(InputStream inStrm) {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inStrm));
//
//		return resource(reader.lines().filter(e -> !e.isBlank() && !e.trim().startsWith("#")).map(e -> {
//			List<String> srcs = Arrays.asList(e.split("->", 2));
//			String productSymbol = srcs.get(0).trim();
//			ArrayList<String> symbols = new ArrayList<>();
//			if (srcs.size() > 1)
//				symbols.addAll(Arrays.asList(srcs.get(1).trim().split("\s+")));
//
//			return new Rule(productSymbol, symbols);
//		}).toList());
//	}
//
//	public Grammar resource(List<Rule> rules) {
//		if (rules.size() == 0)
//			return this;
//
//		if (getProductSymbol() == null) {
//			String productSymbol = rules.get(0).productSymbol();
//			setProductSymbol(productSymbol);
//			cacheNonterminalSymbolWithIndex(productSymbol);
//		}
//
//		for (Rule rule : rules) {
//			cacheNonterminalSymbolWithIndex(rule.productSymbol());
//			if (!cacheRuleWithIndex(rule))
//				throw new RuntimeException("Error: Duplicated Rule: " + rule);
//
//			if (rule.size() == 1 && rule.getFirstSymbol().equals(rule.productSymbol()))
//				throw new RuntimeException("Error: Invalid Rule: " + rule);
//		}
//
//		// DON'T MERGE FOR rules LOOP
//
//		for (Rule rule : rules)
//			for (String symbol : rule.symbols())
//				if (!isNonterminalSymbol(symbol))
//					// We should call this method after saving all of nonterminal symbol has done
//					cacheTerminalSymbolWithIndex(symbol);
//
//		return this;
//	}
//
//	public Grammar resource(Rule... rules) {
//		return resource(Arrays.asList(rules));
//	}
//
//	public void setEndSymbol(String endSymbol) {
//		this.endSymbol = endSymbol;
//	}
//
//	public void setNonterminalSymbolIndices(HashMap<String, Integer> nonterminalSymbolIndices) {
//		this.nonterminalSymbolIndices = nonterminalSymbolIndices;
//	}
//
//	public void setNonterminalSymbols(List<String> nonterminalSymbols) {
//		this.nonterminalSymbols = nonterminalSymbols;
//	}
//
//	public void setProductSymbol(String productSymbol) {
//		this.productSymbol = productSymbol;
//	}
//
//	public void setRuleIndices(HashMap<Rule, Integer> ruleIndices) {
//		this.ruleIndices = ruleIndices;
//	}
//
//	public void setRules(List<Rule> rules) {
//		this.rules = rules;
//	}
//
//	public void setTerminalSymbolIndices(HashMap<String, Integer> terminalSymbolIndices) {
//		this.terminalSymbolIndices = terminalSymbolIndices;
//	}
//
//	public void setTerminalSymbols(List<String> terminalSymbols) {
//		this.terminalSymbols = terminalSymbols;
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder stringBuilder = new StringBuilder();
//
//		for (int i = 0; i < getRules().size(); i++) {
//			stringBuilder.append(i);
//			stringBuilder.append(": ");
//			stringBuilder.append(getRules().get(i));
//
//			if (i + 1 < getRules().size())
//				stringBuilder.append(System.lineSeparator());
//		}
//
//		return stringBuilder.toString();
//	}
//
//}
