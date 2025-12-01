package myprototype.jparse;

import java.util.ArrayList;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.SymbolKindEnum;

public class RuleScenario {
	private SymbolEnum nonterminal;
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

	private void setNonterminal(SymbolEnum nonterminal) {
		this.nonterminal = nonterminal;
	}

	public SymbolEnum getNonterminal() {
		return nonterminal;
	}

	public RuleScenario(SymbolEnum nonterminal, Rule rule, int dot) {
		this.nonterminal = nonterminal;
		setRule(rule);
		setDot(dot);
	}

	public RuleScenario(SymbolEnum nonterminal, Rule rule) {
		this(nonterminal, rule, 0);
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
		setDot(getDot() + 1);
	}

	public ArrayList<RuleScenario> newDotRuleScenarios() {
		ArrayList<RuleScenario> newRuleScenarios = new ArrayList<>();
		SymbolEnum nonterminalSymbol = getDotProductionSymbol();
		for (Rule rule : getDotProductionRules())
			newRuleScenarios.add(new RuleScenario(nonterminal, rule));

		return newRuleScenarios;
	}

}