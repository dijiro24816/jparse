package myprototype.jparse;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Item {
	private Rule rule;
	private int dot;
	private HashSet<String> lookaheadSet;
	
	public String getProductSymbol() {
		return rule.getProductSymbol();
	}

	public Rule getRule() {
		return rule;
	}

	public int getDot() {
		return dot;
	}
	
	public HashSet<String> getLookaheadSet() {
		return this.lookaheadSet;
	}
	
	public Item(Rule rule, HashSet<String> lookaheadSet, int dot) {
		this.rule = rule;
		this.lookaheadSet = lookaheadSet;
		this.dot = dot;
	}

	public Item(Rule rule, HashSet<String> lookAhead) {
		this(rule, lookAhead, 0);
	}
	
	public Item(Rule rule) {
		this(rule, new HashSet<String>());
	}

	public boolean isTakingTheClosure() {
		return getRestSymbolsCount() == 0;
	}
	
	public boolean isReachingLastSymbol() {
		return getRestSymbolsCount() == 1;
	}
	
	public int getRestSymbolsCount() {
		return this.rule.getSymbols().size() - this.dot;
	}

	public String getDotSymbol() {
		return isTakingTheClosure() ? null : this.rule.getSymbols().get(this.dot);
	}
	
	public String getDotNextSymbol() {
		return this.rule.getSymbols().get(this.dot + 1);
	}
	
	public String getRightSymbolsString() {
		return String.join(" ", this.rule.getSymbols().subList(this.dot, this.rule.getSymbols().size()));
	}
	
	public boolean isDotNonterminal(Grammar grammar) {
		return grammar.isNonterminalSymbol(getDotSymbol());
	}

	public List<Item> generateDotItemsOf(Grammar grammar, HashSet<String> orgLookaheadSet) {
		
		// Generate lookahead-set
		if (getRestSymbolsCount() == 1)
			return Item.generateItemsOf(grammar, getDotSymbol(), orgLookaheadSet);
		
		HashSet<String> lookaheadSet = new HashSet<>();
		
		return Item.generateItemsOf(grammar, getDotSymbol(), lookaheadSet);
		
	}
	
	public static List<Item> generateBeginningItemsOf(Grammar grammar) {
		HashSet<String> lookAheadSet = new HashSet<>();
		lookAheadSet.add(grammar.getEndSymbol());
		return Item.generateItemsOf(grammar, grammar.getStartSymbol(), lookAheadSet);
	}

	public static List<Item> generateItemsOf(Grammar grammar, String symbol, HashSet<String> lookAheadSet) {
		return grammar.getRulesOf(symbol).stream().map(e -> new Item(e, lookAheadSet)).toList();
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

	
	
	public void increaseDot() {
		this.dot++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.dot) + this.rule.hashCode();
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


	@Override
	protected Item clone() {
		return new Item(this.rule, this.lookaheadSet, this.dot);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Item [" + this.getProductSymbol() + " -> ");

		if (this.dot == 0)
			stringBuilder.append(". ");
		
		List<String> symbols = this.rule.getSymbols();
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
		stringBuilder.append(this.lookaheadSet);
		return stringBuilder.toString();
	}
}
