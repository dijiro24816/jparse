package myprototype.jparse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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

public class ScenarioWriter {
	private List<Item> excludeClosure(Collection<Item> ruleScenarios) {
		return ruleScenarios.stream().filter(ruleScenario -> !ruleScenario.isTakingTheClosure()).toList();
	}

	private ArrayList<Item> expandRuleScenariosDot(Collection<Item> orgRuleScenarios) {
		ArrayList<Item> ruleScenarios = new ArrayList<>();
		HashSet<SymbolEnum> expandedNonterminals = new HashSet<>();
		ArrayDeque<Item> queue = new ArrayDeque<>();

		for (Item ruleScenario : orgRuleScenarios)
			queue.offer(ruleScenario);

		Item ruleScenario;
		while ((ruleScenario = queue.poll()) != null) {
			ruleScenarios.add(ruleScenario);
			if (ruleScenario.getDotProductionSymbol().getKind() == SymbolKindEnum.NONTERMINAL
					&& !expandedNonterminals.contains(ruleScenario.getDotProductionSymbol())) {
				// Get all of derivative rules fromd dot production, and convert Rule to
				// RuleScenario, and then push it on queue
				for (RuleO rule : ruleScenario.getDotProductionRules())
					queue.offer(new Item(rule));
			}
		}

		return ruleScenarios;
	}

	public ParserData getParserData(ProductionO begProduction, Class<? extends Enum<?>> symbolEnum) {
		ParserData parserData = new ParserData(symbolEnum, begProduction);
		HashSet<Item> ruleScenarioStatesKey = new HashSet<>(
				begProduction.getRules().stream().map(rule -> new Item(rule)).toList());

		takeAction(ruleScenarioStatesKey, new HashMap<HashSet<Item>, Action>(), parserData);
		return parserData;
	}

	private Action takeAction(HashSet<Item> ruleScenarioStatesKey,
			HashMap<HashSet<Item>, Action> ruleScenarioStates, ParserData parserData) {
		// return states if already existing
		// FIXME: This is very bad implementation
		for (Set<Item> ruleScenarioSet : ruleScenarioStates.keySet()) {
			if (ruleScenarioSet.equals(ruleScenarioStatesKey)) {
				return ruleScenarioStates.get(ruleScenarioSet);
			}
		}
		
		
		
		if (ruleScenarioStates.containsKey(ruleScenarioStatesKey))
			return ruleScenarioStates.get(ruleScenarioStatesKey);

		
		int currentState = parserData.getNewState();
		Action enterAction = new Action(ActionKind.Shift, currentState);

		// Make rule scenario set that has not moveable dot
		HashSet<Item> ruleScenarioStatesKeyClone = new HashSet<>();
		for (Item ruleScenario : ruleScenarioStatesKey)
			ruleScenarioStatesKeyClone.add(ruleScenario.clone());

		ruleScenarioStates.put(ruleScenarioStatesKeyClone, enterAction);

		// Taking the closure
		List<Item> closures = ruleScenarioStatesKey.stream()
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

		ArrayList<Item> ruleScenarios = expandRuleScenariosDot(excludeClosure(ruleScenarioStatesKey));
		if (ruleScenarios.size() == 0)
			return enterAction;
		
		ruleScenarios
				.sort(Comparator.comparing(ruleScenario -> ((Item) ruleScenario).getDotProductionSymbol()));
		if (ruleScenarios.size() == 0)
			return enterAction;
		
		int begIndex = 0, endIndex = 0;
		SymbolEnum begSymbol = ruleScenarios.get(begIndex).getDotProductionSymbol();
		for (;;) {
			endIndex++;
			if ((endIndex == ruleScenarios.size()) || begSymbol != ruleScenarios.get(endIndex).getDotProductionSymbol()) {
				HashSet<Item> partOfRuleScenarios = new HashSet<>(ruleScenarios.subList(begIndex, endIndex));

				for (Item ruleScenario : partOfRuleScenarios)
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