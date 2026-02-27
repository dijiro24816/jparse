package jparse;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Item {
	public static List<Item> generateBeginningItemsOf(Grammar grammar) {
		HashSet<String> lookAheadSet = new HashSet<>();
		lookAheadSet.add(grammar.getEndSymbol());
		return Item.generateItemsOf(grammar, grammar.getProductSymbol(), lookAheadSet);
	}
	public static List<Item> generateItemsOf(Grammar grammar, String symbol, HashSet<String> lookAheadSet) {
		return grammar.getRulesOf(symbol).stream().map(e -> new Item(e, lookAheadSet)).toList();
	}
	private int dot;
	
	private HashSet<String> lookaheadSet;

	private Rule rule;

	public Item(Rule rule) {
		this(rule, new HashSet<String>());
	}
	
	public Item(Rule rule, HashSet<String> lookAhead) {
		this(rule, lookAhead, 0);
	}
	
	public Item(Rule rule, HashSet<String> lookaheadSet, int dot) {
		this.rule = rule;
		this.lookaheadSet = lookaheadSet;
		this.dot = dot;
	}
	
	public String beforeSymbolsString() {
		return String.join(" ", this.rule.symbols().subList(0, this.dot));
	}

	@Override
	protected Item clone() {
		return new Item(this.rule, this.lookaheadSet, this.dot);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Item other = (Item) obj;
		return this.dot == other.getDot() && this.rule.equals(other.getRule()) && this.lookaheadSet.equals(other.getLookaheadSet());
	}

	public List<Item> generateDotItemsOf(Grammar grammar, HashSet<String> orgLookaheadSet) {
		
		// Generate lookahead-set
		if (getRestSymbolsCount() == 1)
			return Item.generateItemsOf(grammar, getDotSymbol(), orgLookaheadSet);
		
		HashSet<String> lookaheadSet = new HashSet<>();
		
		return Item.generateItemsOf(grammar, getDotSymbol(), lookaheadSet);
		
	}
	
	public int getDot() {
		return dot;
	}
	
	public String getDotNextSymbol() {
		return this.rule.symbols().get(this.dot + 1);
	}
	
	public String getDotSymbol() {
		return isTakingTheClosure() ? null : this.rule.symbols().get(this.dot);
	}

	public HashSet<String> getLookaheadSet() {
		return this.lookaheadSet;
	}
	
	public String getProductSymbol() {
		return rule.productSymbol();
	}
	
	public int getRestSymbolsCount() {
		return this.rule.symbols().size() - this.dot;
	}
	
	public String getRightSymbolsString() {
		return String.join(" ", this.rule.symbols().subList(this.dot, this.rule.symbols().size()));
	}
	
	public Rule getRule() {
		return rule;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.dot) + this.rule.hashCode();
	}
	
	public void increaseDot() {
		this.dot++;
	}

	public boolean isDotNonterminal(Grammar grammar) {
		return grammar.isNonterminalSymbol(getDotSymbol());
	}
	
//	public static List<Item> expandDot(Grammar grammar, HashSet<String> lookAheadSet, ArrayDeque<Item> itemQueue) {
//		List<Item> items = new ArrayList<>();
//		HashSet<String> expandedNonterminals = new HashSet<>();
//
//		Item item;
//		while ((item = itemQueue.poll()) != null) {
//			items.add(item);
//			if (grammar.isNonterminalSymbol(item.getDotSymbol())
//					&& !expandedNonterminals.contains(item.getDotSymbol())) {
//				// Get all of derivative rules fromd dot production, and convert Rule to
//				// RuleScenario, and then push it on queue
//				item.generateDotItemsOf(grammar, lookAheadSet).stream().forEach(e -> itemQueue.offer(e));
//			}
//		}
//
//		return items;
//	}
//
//	public static List<Item> expandDot(Grammar grammar, HashSet<String> lookAheadSet, Collection<Item> orgItems) {
//		ArrayDeque<Item> itemQueue = new ArrayDeque<>();
//		orgItems.stream().forEach(e -> itemQueue.offer(e));
//		return expandDot(grammar, lookAheadSet, itemQueue);
//	}
//

	
	
	public boolean isReachingLastSymbol() {
		return getRestSymbolsCount() == 1;
	}

	public boolean isReplaceable() {
		return getDot() == 0 && getRule().isReplaceable();
	}
	
	public boolean isTakingTheClosure() {
		return getRestSymbolsCount() == 0;
	}


	public int size() {
		return this.rule.size();
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
}
