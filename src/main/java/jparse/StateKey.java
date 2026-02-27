package jparse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public record StateKey(String rootSymbol, List<Item> items, List<Item> closures) {
	public static StateKey create(Collection<Item> orgItems) {
		return create(null, orgItems);
	}

	public static StateKey create(String rootSymbol, Collection<Item> orgItems) {
		ArrayList<Item> items = new ArrayList<>(orgItems.stream().filter(e -> !e.isTakingTheClosure()).toList());
		ArrayList<Item> closures = new ArrayList<>(orgItems.stream().filter(e -> e.isTakingTheClosure()).toList());
		Comparator<Item> comparator = Comparator.<Item, String> comparing(Item::getRightSymbolsString)
				.thenComparing(
						e -> e.getLookaheadSet().stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString(),
						String.CASE_INSENSITIVE_ORDER)
				.thenComparing(Item::beforeSymbolsString)
				.thenComparing(Item::getProductSymbol);

		items.sort(comparator);
		closures.sort(comparator);

		return new StateKey(rootSymbol, items, closures);
	}

	public ArrayList<StateKey> getDerivativeKeys(Grammar grammar) {
		ArrayList<StateKey> derivativeKeys = new ArrayList<>();

		if (items.size() == 0)
			return derivativeKeys;

		int begIndex = 0, endIndex = 0;
		String currentSymbol = items.get(begIndex).getDotSymbol();
		for (;;) {
			endIndex++;
			if ((endIndex == items.size()) || !currentSymbol.equals(items.get(endIndex).getDotSymbol())) {
				List<Item> partOfItems = items.subList(begIndex, endIndex).stream().map(e -> e.clone()).toList();
				for (Item item : partOfItems)
					item.increaseDot();

				derivativeKeys.add(StateKey.create(currentSymbol, grammar.expandItems(partOfItems)));

				// postfix processing
				if (endIndex == items.size())
					break;
				begIndex = endIndex;
				currentSymbol = items.get(begIndex).getDotSymbol();
			}
		}
		return derivativeKeys;
	}
}
