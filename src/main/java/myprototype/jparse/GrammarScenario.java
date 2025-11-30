package myprototype.jparse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.Nonterminal;
import myprototype.jparse.symbol.terminal.Terminal;

public class GrammarScenario {
	// all value should be greater than -1
	private HashMap<Class<? extends Nonterminal>, Integer> ruleStates;

	// WARN: Don't change this list's order
	private List<List<Integer>> syntaticsParserTable;

	public List<List<Integer>> getSyntaticsParserTable() {
		return Collections.unmodifiableList(this.syntaticsParserTable);
	}

//	private List<RuleScenario> currentRuleSenarios;
	private int state;

	private int getState() {
		return state;
	}

	private void setState(int state) {
		this.state = state;
	}

	public HashMap<Class<? extends Nonterminal>, Integer> getRuleStates() {
		return ruleStates;
	}

//	private List<RuleScenario> getCurrentRuleScenarios() {
//		return currentRuleSenarios;
//	}
//
//	public void setCurrentRuleSenarios(List<RuleScenario> ruleSenarios) {
//		this.currentRuleSenarios = ruleSenarios;
//	}

	public void setRuleStates(HashMap<Class<? extends Nonterminal>, Integer> ruleStates) {
		this.ruleStates = ruleStates;
	}

	private int getRuleStateFor(Class<? extends Nonterminal> nonterminalClass) {
		return getRuleStates().containsKey(nonterminalClass) ? getRuleStates().get(nonterminalClass) : -1;
	}

//	public GrammarScenario(Class<? extends Nonterminal> nonterminal, Rule rule) {
//		setState(0);
////		setCurrentRuleSenarios(new ArrayList<RuleScenario>());
//		storeRuleScenarioWithNewState(new RuleScenario(nonterminal, rule));
//	}

//	private int storeRuleScenarioWithNewState(RuleScenario ruleScenario) {
////		getCurrentRuleScenarios().add(ruleScenario);
//		int newState = getNewState();
//		getRuleStates().put(ruleScenario.getNonterminal(), newState);
//		return newState;
//	}

	private void completeCurrentDotsOnTerminal(RuleScenario ruleScenario) {

	}

	private int getNextStateOfRuleScenarios(HashMap<Class<? extends Nonterminal>, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, List<RuleScenario> ruleScenarios) {
		
		int nextState = getDotStateForProduction(ruleStates, syntaticsParserTable, ruleScenarios.getFirst().);
		
		
		return 0;
	}

	private int finiteEachRuleScenarios(HashMap<Class<? extends Nonterminal>, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, List<RuleScenario> ruleScenarios) {
		int begState = syntaticsParserTable.getNewState();

		int begIndex, endIndex;
		Class<? extends Symbol> symbol = ruleScenarios.get(0).getDotProductionSymbol();
		for (begIndex = 0, endIndex = 1; endIndex < ruleScenarios.size(); endIndex++) {
			if (symbol != ruleScenarios.get(endIndex).getDotProductionSymbol()) {
				List<RuleScenario> derivativeRuleScenarios = ruleScenarios.subList(begIndex, endIndex);

				int state = finiteEachRuleScenarios(ruleStates, syntaticsParserTable, derivativeRuleScenarios);

				begIndex = endIndex;
			}
		}
		List<RuleScenario> derivativeRuleScenarios = ruleScenarios.subList(begIndex, endIndex);

		// TODO: define new state

		int state = finiteEachRuleScenarios(ruleStates, syntaticsParserTable, derivativeRuleScenarios);

		return begState;
	}

	private int getDotStateForProduction(HashMap<Class<? extends Nonterminal>, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, Production production) {
		if (production.getSymbol().isAssignableFrom(Terminal.class))
			return syntaticsParserTable.getNewState();

		Class<? extends Nonterminal> nonterminal = (Class<? extends Nonterminal>) production.getSymbol();
		if (ruleStates.containsKey(nonterminal))
			return ruleStates.get(nonterminal);

		List<RuleScenario> ruleScenarios = new ArrayList<RuleScenario>();
		for (Rule rule : production.getRules())
			ruleScenarios.add(new RuleScenario(nonterminal, rule));

		int newState = finiteEachRuleScenarios(ruleStates, syntaticsParserTable, ruleScenarios);
		ruleStates.put(nonterminal, newState);

		return newState;
	}
//	
//	private int getNewStateFromDotOnNonterminal(RuleScenario orgRuleScenario) {
//		int state;
//		if (getRuleStateFor(orgRuleScenario.getDotProductionSymbol()))
//		
//		ArrayList<RuleScenario> ruleScenarios = new ArrayList<>();
//		for (Rule rule : orgRuleScenario.getDotProductionRules())
//			ruleScenarios.add(orgRuleScenario);
//		
//		
//		orgRuleScenario.increaseDot();
//		return getNewState();
//	}
//	
//	// complete expanded rule scenarios
//	private void completeDots(List<RuleScenario> ruleScenarios) {
//		ruleScenarios = ruleScenarios.stream()
//				.sorted(
//						Comparator.comparing(
//								(ruleScenario) -> ((RuleScenario) ruleScenario).getDotProductionSymbol().getName()))
//				.toList();
//
//		int begIndex, endIndex;
//		for (begIndex = 0, endIndex = 1; endIndex < ruleScenarios.size(); endIndex++) {
//			if (ruleScenarios.get(begIndex).getDotProductionSymbol() != ruleScenarios.get(endIndex)
//					.getDotProductionSymbol()) {
//				List<RuleScenario> derivativeRuleScenarios = ruleScenarios.subList(begIndex, endIndex);
//				if (ruleScenarios.get(begIndex).getDotProductionSymbol().isAssignableFrom(Nonterminal.class)) {
//					// TODO: do for nonterminal
////					completeDots(ruleScenarios.get(begIndex).getDotProduction().getRules());
//				} else {
//
//				}
//
//				begIndex = endIndex;
//			}
//		}
//		// TODO: move dot to next
//		completeDots(ruleScenarios.subList(begIndex, endIndex));
//	}

//	@SuppressWarnings("unchecked")
//	private void completeCurrentDot(RuleScenario currentRuleScenario, ) {
//		if (currentRuleScenario.getCurrentProduction().isTerminal())
//			return;
//		
//		Class<? extends Nonterminal> currentSymbol = (Class<? extends Nonterminal>) currentRuleScenario.getCurrentProductionSymbol();
//		
//		int state = getRuleStateFor(currentSymbol);
//		if (state < 0) {
//			for (Rule rule : currentRuleScenario.getCurrentProductionRules()) {
//				RuleScenario ruleScenario = new RuleScenario(currentSymbol, rule);
//				storeRuleScenarioWithState(ruleScenario);
//				completeCurrentDot(ruleScenario);
//			}
//		}
//			
//	}
//	
//	private int completeCurrentDots() {
//		for (RuleScenario ruleScenario : getCurrentRuleScenarios()) {
//			
//		}
//		
//		return 0;
//	}
//	
////	private int 
//	
//
//	public ArrayList<ArrayList<Integer>> createParsingTable(Class<? extends Nonterminal> nonterminal, Rule rule) {
//		ArrayList<ArrayList<Integer>> parsingTable = new ArrayList<>();
//		
//		
//		
//		RuleScenario start = new RuleScenario(nonterminal, rule);
//		
//		for (;;) {
//			ArrayList<Integer> stateRow = new ArrayList<>(SymbolKind.size());
//			
//			
//			
//			break;
//		}
//		
//		return parsingTable;
//	}

}