package jparse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public record Item(Rule rule, int dot, HashSet<String> lookaheadSet) implements Iterator<Item>{
	public Item(Rule rule, HashSet<String> lookaheadSet) {
		this(rule, 0, lookaheadSet);
	}
	
	public Item(Rule rule, int dot) {
		this(rule, dot, new HashSet<>());
	}
	
	public Item(Rule rule) {
		this(rule, new HashSet<>());
	}
	
	public static List<Item> generateBeginningItemsOf(Grammar grammar) {
		HashSet<String> lookAheadSet = new HashSet<>();
		lookAheadSet.add(grammar.getEndSymbol());
		return Item.generateItemsOf(grammar, grammar.getProductSymbol(), lookAheadSet);
	}
	
	public static List<Item> generateItemsOf(Grammar grammar, String symbol, HashSet<String> lookAheadSet) {
		return grammar.getRulesOf(symbol).stream().map(e -> new Item(e, lookAheadSet)).toList();
	}

	public String beforeSymbolsString() {
		return String.join(" ", this.rule.symbols().subList(0, this.dot));
	}

	public List<Item> generateDotItemsOf(Grammar grammar, HashSet<String> orgLookaheadSet) {
		
		// Generate lookahead-set
		if (getRestSymbolsCount() == 1)
			return Item.generateItemsOf(grammar, getDotSymbol(), orgLookaheadSet);
		
		HashSet<String> lookaheadSet = new HashSet<>();
		
		return Item.generateItemsOf(grammar, getDotSymbol(), lookaheadSet);
		
	}
	
	public String getDotNextSymbol() {
		return rule().symbols().get(this.dot + 1);
	}
	
	public String getDotSymbol() {
		return hasNext() ? rule().symbols().get(dot()) : null;
	}

	public String getProductSymbol() {
		return rule().productSymbol();
	}
	
	public int getRestSymbolsCount() {
		return rule().symbols().size() - dot();
	}
	
	public String getRightSymbolsString() {
		return String.join(" ", rule().symbols().subList(dot(), rule().symbols().size()));
	}
	
	public boolean isDotNonterminal(Grammar grammar) {
		return grammar.isNonterminalSymbol(getDotSymbol());
	}
	
	public boolean isReachingLastSymbol() {
		return getRestSymbolsCount() == 1;
	}

	public boolean isReplaceable() {
		return dot() == 0 && rule().isReplaceable();
	}
	
	public boolean isTakingTheClosure() {
		return !hasNext();
	}
	
	
	public Item withoutLookaheadSet() {
		return new Item(rule(), dot());
	}
	
	public Item withLookaheadSet(HashSet<String> lookaheadSet) {
		return new Item(rule(), dot(), lookaheadSet);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Item [" + this.getProductSymbol() + " -> ");

		if (this.dot == 0)
			stringBuilder.append(". ");
		
		List<String> symbols = this.rule.symbols();
		if (symbols.size() > 0) {
			int i = 0;
			for (;;) {
				stringBuilder.append(symbols.get(i));

				i++;

				if (i == this.dot)
					stringBuilder.append(" .");

				if (i == symbols.size())
					break;

				stringBuilder.append(" ");
			}
		}

		stringBuilder.append("] & ");
		stringBuilder.append(this.lookaheadSet.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString());
		
		return stringBuilder.toString();
	}

	@Override
	public boolean hasNext() {
		return dot < rule.symbols().size();
	}

	@Override
	public Item next() {
		return new Item(rule, dot + 1, lookaheadSet);
	}
}
