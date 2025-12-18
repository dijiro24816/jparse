package myprototype.jparse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import myprototype.jparse.symbol.ProductionO;
import myprototype.jparse.symbol.RuleO;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.SymbolKindEnum;

public class SyntaticsTable {
	private int columnLength;
	
	private List<Action[]> actionData;
	private Grammar grammar;
	
	public SyntaticsTable(Grammar grammar) {
		this.grammar = grammar;
		this.columnLength = grammar.getNormalAndTerminalCount();
		this.actionData = new ArrayList<Action[]>();
		
		setupActionData();
	}
	
	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = this.actionData.size();

		Action[] actions = new Action[this.columnLength];
		this.actionData.add(actions);

		return newState;
	}
	
	private void setupActionData() {
			
	}
	
	private List<RuleScenario> excludeClosure(Collection<RuleScenario> ruleScenarios) {
		return ruleScenarios.stream().filter(ruleScenario -> !ruleScenario.isTakingTheClosure()).toList();
	}

	private ArrayList<RuleScenario> expandRuleScenariosDot(Collection<RuleScenario> orgRuleScenarios) {
		ArrayList<RuleScenario> ruleScenarios = new ArrayList<>();
		HashSet<SymbolEnum> expandedNonterminals = new HashSet<>();
		ArrayDeque<RuleScenario> queue = new ArrayDeque<>();

		for (RuleScenario ruleScenario : orgRuleScenarios)
			queue.offer(ruleScenario);

		RuleScenario ruleScenario;
		while ((ruleScenario = queue.poll()) != null) {
			ruleScenarios.add(ruleScenario);
			if (ruleScenario.getDotProductionSymbol().getKind() == SymbolKindEnum.NONTERMINAL
					&& !expandedNonterminals.contains(ruleScenario.getDotProductionSymbol())) {
				// Get all of derivative rules fromd dot production, and convert Rule to
				// RuleScenario, and then push it on queue
				for (RuleO rule : ruleScenario.getDotProductionRules())
					queue.offer(new RuleScenario(rule));
			}
		}

		return ruleScenarios;
	}

	public ParserData getParserData(ProductionO begProduction, Class<? extends Enum<?>> symbolEnum) {
		ParserData parserData = new ParserData(symbolEnum, begProduction);
		HashSet<RuleScenario> ruleScenarioStatesKey = new HashSet<>(
				begProduction.getRules().stream().map(rule -> new RuleScenario(rule)).toList());

		takeAction(ruleScenarioStatesKey, new HashMap<HashSet<RuleScenario>, Action>(), parserData);
		return parserData;
	}

	private Action takeAction(HashSet<RuleScenario> ruleScenarioStatesKey,
			HashMap<HashSet<RuleScenario>, Action> ruleScenarioStates, ParserData parserData) {
		// return states if already existing
		// FIXME: This is very bad implementation
		for (Set<RuleScenario> ruleScenarioSet : ruleScenarioStates.keySet()) {
			if (ruleScenarioSet.equals(ruleScenarioStatesKey)) {
				return ruleScenarioStates.get(ruleScenarioSet);
			}
		}
		
		
		
		if (ruleScenarioStates.containsKey(ruleScenarioStatesKey))
			return ruleScenarioStates.get(ruleScenarioStatesKey);

		
		int currentState = parserData.getNewState();
		Action enterAction = new Action(ActionKind.Shift, currentState);

		// Make rule scenario set that has not moveable dot
		HashSet<RuleScenario> ruleScenarioStatesKeyClone = new HashSet<>();
		for (RuleScenario ruleScenario : ruleScenarioStatesKey)
			ruleScenarioStatesKeyClone.add(ruleScenario.clone());

		ruleScenarioStates.put(ruleScenarioStatesKeyClone, enterAction);

		// Taking the closure
		List<RuleScenario> closures = ruleScenarioStatesKey.stream()
				.filter(ruleScenario -> ruleScenario.isTakingTheClosure()).toList();
		if (closures.size() > 0) {
			// Check reduce-reduce problem
			if (closures.size() > 1)
				throw new RuntimeException("The Grammar has reduce-reduce problem!");

			// Set reduce action as default
			// The value is rule index with negative sign
			// TODO: Use follow-set or lookahead-set
			parserData.setAction(currentState, new Action(ActionKind.Reduce, parserData.getRuleIndex(closures.get(0).getRule())));
		}

		ArrayList<RuleScenario> ruleScenarios = expandRuleScenariosDot(excludeClosure(ruleScenarioStatesKey));
		if (ruleScenarios.size() == 0)
			return enterAction;
		
		ruleScenarios
				.sort(Comparator.comparing(ruleScenario -> ((RuleScenario) ruleScenario).getDotProductionSymbol()));
		if (ruleScenarios.size() == 0)
			return enterAction;
		
		int begIndex = 0, endIndex = 0;
		SymbolEnum begSymbol = ruleScenarios.get(begIndex).getDotProductionSymbol();
		for (;;) {
			endIndex++;
			if ((endIndex == ruleScenarios.size()) || begSymbol != ruleScenarios.get(endIndex).getDotProductionSymbol()) {
				HashSet<RuleScenario> partOfRuleScenarios = new HashSet<>(ruleScenarios.subList(begIndex, endIndex));

				for (RuleScenario ruleScenario : partOfRuleScenarios)
					ruleScenario.increaseDot();

				// Store next state for the symbol from the current state
				parserData.setAction(currentState, begSymbol.ordinal(),
						takeAction(partOfRuleScenarios, ruleScenarioStates, parserData));

				// postfix processing
				if (endIndex == ruleScenarios.size())
					break;
				begIndex = endIndex;
				begSymbol = ruleScenarios.get(begIndex).getDotProductionSymbol();
			}
		}

		return enterAction;
	}
}
