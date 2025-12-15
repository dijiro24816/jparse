package myprototype.jparse;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Item {
	private Grammar grammar;
	private Rule rule;
	private int dot;

	public Rule getRule() {
		return rule;
	}

	public int getDot() {
		return dot;
	}

	public Item(Grammar grammar, Rule rule, int dot) {
		this.grammar = grammar;
		this.rule = rule;
		this.dot = dot;
	}

	public Item(Grammar grammar, Rule rule) {
		this(grammar, rule, 0);
	}

	public boolean isTakingTheClosure() {
		return this.dot == getRule().getSymbols().size();
	}

	public String getDotSymbol() {
		return this.rule.getSymbols().get(this.dot);
	}

	public boolean isDotNonterminal() {
		return this.grammar.isNonterminalSymbol(getDotSymbol());
	}
	
	public Collection<Item> generateDotSymbolItems() {
		return Item.generate(this.grammar, getDotSymbol());
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

		RuleScenario other = (RuleScenario) obj;
		return this.dot == other.getDot() && this.rule.equals(other.getRule());
	}

	@Override
	protected Item clone() {
		return new Item(this.grammar, this.rule, this.dot);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Item [");

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
	
	public static Collection<Item> generate(Grammar grammar, String nonterminalSymbol) {
		return grammar.getRulesOf(nonterminalSymbol).stream().map(e -> new Item(grammar, e)).toList();
	}
}
