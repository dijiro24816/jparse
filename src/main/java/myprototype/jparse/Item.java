package myprototype.jparse;

import java.util.List;
import java.util.Objects;

public class Item {
	private Rule rule;
	private int dot;
	
	public String getProductSymbol() {
		return rule.getProductSymbol();
	}

	public Rule getRule() {
		return rule;
	}

	public int getDot() {
		return dot;
	}

	public Item(Rule rule, int dot) {
		this.rule = rule;
		this.dot = dot;
	}

	public Item(Rule rule) {
		this(rule, 0);
	}

	public boolean isTakingTheClosure() {
		return this.dot == this.rule.getSymbols().size();
	}

	public String getDotSymbol() {
		return this.rule.getSymbols().get(this.dot);
	}
	
	public boolean isDotNonterminal(Grammar grammar) {
		return grammar.isNonterminalSymbol(getDotSymbol());
	}

	public List<Item> generateDotItemsOf(Grammar grammar) {
		return Item.generateItemsOf(grammar, getDotSymbol());
	}
	
	public static List<Item> generateItemsOf(Grammar grammar) {
		return Item.generateItemsOf(grammar, grammar.getStartSymbol());
	}

	public static List<Item> generateItemsOf(Grammar grammar, String symbol) {
		return grammar.getRulesOf(symbol).stream().map(e -> new Item(e)).toList();
	}
	
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
		return this.dot == other.getDot() && this.rule.equals(other.getRule());
	}


	@Override
	protected Item clone() {
		return new Item(this.rule, this.dot);
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

		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
