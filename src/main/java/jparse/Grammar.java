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

public record Grammar(String productSymbol, String endSymbol, HashMap<String, Integer> terminalSymbolIndices,
		List<String> terminalSymbols, HashMap<String, Integer> nonterminalSymbolIndices,
		List<String> nonterminalSymbols, HashMap<Rule, Integer> ruleIndices, List<Rule> rules) implements Serializable {

	public static Grammar loadFile(String fileName) throws FileNotFoundException {
		return new GrammarBuilder().resourceFile(fileName).build();
	}

	public static Grammar loadFile(String fileName, String productSymbol) throws FileNotFoundException {
		return new GrammarBuilder().resourceFile(fileName).build(productSymbol);
	}

	public static Grammar loadFile(String fileName, String productSymbol, String endSymbol)
			throws FileNotFoundException {
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
			HashSet<String> lookaheadSet = getLookaheadSetFromItems(items, item.rule().productSymbol());
			lookaheadSet.add(getEndSymbol());
			dstItems.add(new Item(item.rule(), lookaheadSet));
		}

		return dstItems;
	}
	
	public HashSet<Item> integrateItemsLookaheadSet(Collection<Item> orgItems) {
		HashMap<Item, HashSet<String>> itemLookaheadSet = new HashMap<>();
		
		for (Item orgItem : orgItems) {
			Item key = orgItem.withoutLookaheadSet();
			if (!itemLookaheadSet.containsKey(key))
				itemLookaheadSet.put(key, new HashSet<>());
			
			itemLookaheadSet.get(key).addAll(orgItem.lookaheadSet());
		}
		
		HashSet<Item> items = new HashSet<>();
		itemLookaheadSet.forEach((k, v) -> {
			items.add(k.withLookaheadSet(v));
		});
		
		return items;
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
		
		
		for (Rule rule : rules) {
//			if (rules.stream().anyMatch(e -> e.productSymbol().equals("SingleTypeImportDeclaration"))) {
//				System.out.println(rules);
//			}
			
			dstItems.add(new Item(rule, getLookaheadSetFromItems(items, rule.productSymbol())));
		}

		return integrateItemsLookaheadSet(dstItems);
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

		for (Item item : orgItems) {

			if (item.isReachingLastSymbol())
				// Nonterm1 -> . Nonterm2
				// add Lookahead-Set(Nonterm2)
				lookaheadSet.addAll(item.lookaheadSet());
			else
				// Nonterm -> . Nonterm Term
				lookaheadSet.addAll(getFirstSet(item.getDotNextSymbol()));
		}

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
