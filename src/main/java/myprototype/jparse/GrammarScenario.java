package myprototype.jparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.nonterminal.Nonterminal;

public class GrammarScenario {
	// all value should be greater than -1 
	private HashMap<Class<? extends Nonterminal>, Integer> ruleStates;
	private List<RuleScenario> currentRuleSenarios;
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

	private List<RuleScenario> getCurrentRuleScenarios() {
		return currentRuleSenarios;
	}

	public void setCurrentRuleSenarios(List<RuleScenario> ruleSenarios) {
		this.currentRuleSenarios = ruleSenarios;
	}

	public void setRuleStates(HashMap<Class<? extends Nonterminal>, Integer> ruleStates) {
		this.ruleStates = ruleStates;
	}

	private int getRuleStateFor(Class<? extends Nonterminal> nonterminalClass) {
		return getRuleStates().containsKey(nonterminalClass) ? getRuleStates().get(nonterminalClass) : -1;
	}
	
	public int getNewState() {
		int newState = getState() + 1;
		setState(newState);
		return newState;
	}
	
	public GrammarScenario(Class<? extends Nonterminal> nonterminal, Rule rule) {
		setState(0);
		setCurrentRuleSenarios(new ArrayList<RuleScenario>());
		storeRuleScenarioWithNewState(new RuleScenario(nonterminal, rule));
	}
	
	private int storeRuleScenarioWithNewState(RuleScenario ruleScenario) {
		getCurrentRuleScenarios().add(ruleScenario);
		int newState = getNewState();
		getRuleStates().put(ruleScenario.getNonterminal(), newState);
		return newState;
	}
	
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