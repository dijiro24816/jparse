package myprototype.jparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.SymbolKindEnum;

public class GrammarScenario {

	//	public HashMap<Class<? extends Nonterminal>, Integer> getRuleStates() {
	//		return ruleStates;
	//	}

	//	private List<RuleScenario> getCurrentRuleScenarios() {
	//		return currentRuleSenarios;
	//	}
	//
	//	public void setCurrentRuleSenarios(List<RuleScenario> ruleSenarios) {
	//		this.currentRuleSenarios = ruleSenarios;
	//	}

	//	public void setRuleStates(HashMap<Class<? extends Nonterminal>, Integer> ruleStates) {
	//		this.ruleStates = ruleStates;
	//	}

	//	private int getRuleStateFor(SymbolEnum nonterminal) {
	//		return getRuleStates().containsKey(nonterminal) ? getRuleStates().get(nonterminal) : -1;
	//	}

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

	private int getNextStateOfRuleScenarios(HashMap<SymbolEnum, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, List<RuleScenario> ruleScenarios) {
		int currentState = getDotStateForProduction(ruleStates, syntaticsParserTable,
				ruleScenarios.get(0).getDotProduction());

		for (RuleScenario ruleScenario : ruleScenarios) {
			ruleScenario.increaseDot();
		}

		return currentState;
	}

	private int finiteEachRuleScenarios(HashMap<SymbolEnum, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, List<RuleScenario> ruleScenarios) {
		
		// TODO: We should expand all Nonterminal symbol
		
		
		int currentState = syntaticsParserTable.getNewState();

		int begIndex = 0, endIndex = 0;
		SymbolEnum symbol = ruleScenarios.get(begIndex).getDotProductionSymbol();
		while (endIndex++ < ruleScenarios.size()) {
			if ((begIndex != endIndex && endIndex == ruleScenarios.size()) || symbol != ruleScenarios.get(endIndex).getDotProductionSymbol()) {
				List<RuleScenario> partOfRuleScenarios = ruleScenarios.subList(begIndex, endIndex);

				if (symbol.getKind() == SymbolKindEnum.TERMINAL) {
					for (RuleScenario ruleScenario : partOfRuleScenarios)
						ruleScenario.increaseDot();
					
					syntaticsParserTable.put(currentState, symbol.ordinal(),
							finiteEachRuleScenarios(ruleStates, syntaticsParserTable, ruleScenarios));
				} else {
					
				}
				
				for (RuleScenario ruleScenario : partOfRuleScenarios) {
					ruleScenario.increaseDot();
				}
				
				

				int nextState = getDotStateForProduction(ruleStates, syntaticsParserTable,
						partOfRuleScenarios.get(0).getDotProduction());

				for (RuleScenario ruleScenario : partOfRuleScenarios)
					ruleScenario.increaseDot();

				finiteEachRuleScenarios(ruleStates, syntaticsParserTable, partOfRuleScenarios);

				//				int nextState = getNextStateOfRuleScenarios(ruleStates, syntaticsParserTable, partOfRuleScenarios);
				//				syntaticsParserTable.set(currentState, symbol.ordinal(), nextState);

				begIndex = endIndex;
			}
		}

		return currentState;
	}

	private int getDotStateForProduction(HashMap<SymbolEnum, Integer> ruleStates,
			SyntaticsParserTable syntaticsParserTable, Production production) {
		if (production.getSymbol().getKind() == SymbolKindEnum.TERMINAL)
			return syntaticsParserTable.getNewState();

		SymbolEnum nonterminalSymbol = production.getSymbol();
		if (ruleStates.containsKey(nonterminalSymbol))
			return ruleStates.get(nonterminalSymbol);

		List<RuleScenario> ruleScenarios = new ArrayList<RuleScenario>();
		for (Rule rule : production.getRules())
			ruleScenarios.add(new RuleScenario(nonterminalSymbol, rule));

		int newState = finiteEachRuleScenarios(ruleStates, syntaticsParserTable, ruleScenarios);
		ruleStates.put(nonterminalSymbol, newState);

		return newState;
	}

	private int getDot

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