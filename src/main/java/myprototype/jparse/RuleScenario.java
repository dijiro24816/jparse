package myprototype.jparse;

import java.util.ArrayList;
import java.util.Objects;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.SymbolKindEnum;

public class RuleScenario implements Cloneable {
	private Rule rule;
	private int dot;

	public Rule getRule() {
		return rule;
	}

	private void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getDot() {
		return dot;
	}

	private void setDot(int dot) {
		this.dot = dot;
	}

	public RuleScenario(Rule rule, int dot) {
		setRule(rule);
		setDot(dot);
	}

	public RuleScenario(Rule rule) {
		this(rule, 0);
	}

	public boolean isTakingTheClosure() {
		return getDot() == getRule().getProductions().length;
	}

	public Production getDotProduction() {
		return getRule().getProductions()[getDot()];
	}

	public boolean isDotNonterminal() {
		return getDotProduction().getSymbol().getKind() == SymbolKindEnum.NONTERMINAL;
	}

	public Rule[] getDotProductionRules() {
		return getDotProduction().getRules();
	}

	public SymbolEnum getDotProductionSymbol() {
		return getDotProduction().getSymbol();
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
	protected RuleScenario clone() {
		return new RuleScenario(this.rule, this.dot);
	}
	
	

}