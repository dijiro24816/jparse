package myprototype.jparse;

import java.util.ArrayList;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class RuleScenario {
	private Class<? extends Nonterminal> nonterminal;
	private Rule rule;
	private int dot;

	public Rule getRule() {
		return rule;
	}

	private void setRule(Rule rule) {
		this.rule = rule;
	}

	private int getDot() {
		return dot;
	}

	private void setDot(int dot) {
		this.dot = dot;
	}

	private void setNonterminal(Class<? extends Nonterminal> nonterminal) {
		this.nonterminal = nonterminal;
	}

	public Class<? extends Nonterminal> getNonterminal() {
		return nonterminal;
	}

	public RuleScenario(Class<? extends Nonterminal> nonterminal, Rule rule, int dot) {
		setNonterminal(nonterminal);
		setRule(rule);
		setDot(dot);
	}

	public RuleScenario(Class<? extends Nonterminal> nonterminal, Rule rule) {
		this(nonterminal, rule, 0);
	}

	public boolean isTakingTheClosure() {
		return getDot() == getRule().getProductions().length;
	}

	public Production getDotProduction() {
		return getRule().getProductions()[getDot()];
	}

	public boolean isDotNonterminal() {
		return getDotProduction().getSymbol().isAssignableFrom(Nonterminal.class);
	}

	public Rule[] getDotProductionRules() {
		return getDotProduction().getRules();
	}

	public Class<? extends Symbol> getDotProductionSymbol() {
		return getDotProduction().getSymbol();
	}

	public void increaseDot() {
		setDot(getDot() + 1);
	}

	public ArrayList<RuleScenario> newDotRuleScenarios() {
		ArrayList<RuleScenario> newRuleScenarios = new ArrayList<>();
		Class<? extends Nonterminal> nonterminal = (Class<? extends Nonterminal>) getDotProductionSymbol();
		for (Rule rule : getDotProductionRules())
			newRuleScenarios.add(new RuleScenario(nonterminal, rule));

		return newRuleScenarios;
	}

}