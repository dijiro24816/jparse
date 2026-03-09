package jparse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public record StateKey(String rootSymbol, HashSet<Item> items, HashSet<Item> closures) {
	public static StateKey create(Grammar grammar) {
		return StateKey.create(grammar, null, grammar.expandFirstItems());
	}
	
	public static StateKey create(Grammar grammar, String rootSymbol, Collection<Item> orgItems) {
		HashSet<Item> items = new HashSet<>();
		HashSet<Item> closures = new HashSet<>();
		
		
		for (Item item : grammar.expandItems(orgItems)) {
			if (item.hasNext())
				items.add(item);
			else
				closures.add(item);
		}
		return new StateKey(rootSymbol, items, closures);
	}
	
	public ArrayList<StateKey> getDerivativeKeys(Grammar grammar) {
		ArrayList<StateKey> stateKeys = new ArrayList<>();
		
		HashMap<String, List<Item>> symbolItems = new HashMap<>();
		
		for (Item item : items()) {
			if (!symbolItems.containsKey(item.getDotSymbol()))
				symbolItems.put(item.getDotSymbol(), new ArrayList<>());
			
			symbolItems.get(item.getDotSymbol()).add(item);
		}
		
		symbolItems.forEach((k, v) -> {
			stateKeys.add(StateKey.create(grammar, k, v.stream().map(e -> e.next()).toList()));
		});
		
		return stateKeys;
	}
	
	
	
//	public static StateKey create(String rootSymbol, Collection<Item> orgItems) {
//		ArrayList<Item> items = new ArrayList<>(orgItems.stream().filter(e -> !e.isTakingTheClosure()).toList());
//		ArrayList<Item> closures = new ArrayList<>(orgItems.stream().filter(e -> e.isTakingTheClosure()).toList());
//		Comparator<Item> comparator = Comparator.<Item, String> comparing(Item::getRightSymbolsString)
//				.thenComparing(
//						e -> e.lookaheadSet().stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString(),
//						String.CASE_INSENSITIVE_ORDER)
//				.thenComparing(Item::beforeSymbolsString)
//				.thenComparing(Item::getProductSymbol);
//
//		items.sort(comparator);
//		closures.sort(comparator);
//
//		return new StateKey(rootSymbol, items, closures);
//	}
//
//	public ArrayList<StateKey> getDerivativeKeys(Grammar grammar) {
//		ArrayList<StateKey> derivativeKeys = new ArrayList<>();
//
//		if (items().size() == 0)
//			return derivativeKeys;
//
//		int begIndex = 0, endIndex = 0;
//		String currentSymbol = items().get(begIndex).getDotSymbol();
//		for (;;) {
//			endIndex++;
//			if ((endIndex == items().size()) || !currentSymbol.equals(items().get(endIndex).getDotSymbol())) {
//				List<Item> partOfItems = items().subList(begIndex, endIndex).stream().map(e -> e.next()).toList();
//
//				derivativeKeys.add(StateKey.create(currentSymbol, grammar.expandItems(partOfItems)));
//
//				// postfix processing
//				if (endIndex == items().size())
//					break;
//				begIndex = endIndex;
//				currentSymbol = items().get(begIndex).getDotSymbol();
//			}
//		}
//		return derivativeKeys;
//	}
}
