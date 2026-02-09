package myprototype.jparse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class StateKey {
	private String rootSymbol;

	private List<Item> items;
	private List<Item> closures;

	public StateKey(String rootSymbol, List<Item> items, List<Item> closures) {
		this.rootSymbol = rootSymbol;
		this.items = items;
		this.closures = closures;
	}

	public String getRootSymbol() {
		return this.rootSymbol;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public List<Item> getClosures() {
		return this.closures;
	}

	public static StateKey create(Collection<Item> orgItems) {
		return create(null, orgItems);
	}

	public static StateKey create(String rootSymbol, Collection<Item> orgItems) {
		ArrayList<Item> items = new ArrayList<>(orgItems.stream().filter(e -> !e.isTakingTheClosure()).toList());
		ArrayList<Item> closures = new ArrayList<>(orgItems.stream().filter(e -> e.isTakingTheClosure()).toList());
		Comparator<Item> comparator = Comparator.<Item, String>comparing(Item::getDotSymbol).thenComparing(
				e -> e.getLookaheadSet().stream().sorted(String.CASE_INSENSITIVE_ORDER).toString(),
				String.CASE_INSENSITIVE_ORDER);

		items.sort(comparator);
		closures.sort(comparator);
		
		return new StateKey(rootSymbol, items, closures);
	}

	public ArrayList<StateKey> getDerivativeKeys(Grammar grammar) {
		ArrayList<StateKey> derivativeKeys = new ArrayList<>();

		if (this.items.size() == 0)
			return derivativeKeys;

		int begIndex = 0, endIndex = 0;
		String currentSymbol = this.items.get(begIndex).getDotSymbol();
		HashSet<String> currentLookaheadSet = this.items.get(begIndex).getLookaheadSet();
		for (;;) {
			endIndex++;
			if ((endIndex == this.items.size()) || !currentSymbol.equals(this.items.get(endIndex).getDotSymbol())
					|| !currentLookaheadSet.equals(this.items.get(endIndex).getLookaheadSet())) {

				List<Item> partOfItems = this.items.subList(begIndex, endIndex).stream().map(e -> e.clone()).toList();
				for (Item item : partOfItems)
					item.increaseDot();
				
				derivativeKeys.add(StateKey.create(currentSymbol, grammar.expandItems(partOfItems)));

				// postfix processing
				if (endIndex == this.items.size())
					break;
				begIndex = endIndex;
				currentSymbol = this.items.get(begIndex).getDotSymbol();
			}
		}
		return derivativeKeys;
	}

	@Override
	public int hashCode() {
		return this.items.hashCode() + this.closures.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		StateKey other = (StateKey) obj;

		return this.items.equals(other.getItems()) && this.closures.equals(other.getClosures());
	}

	@Override
	public String toString() {
		return "StateKey [rootSymbol=" + this.rootSymbol + " items=" + this.items + " closures=" + this.closures + "]";
	}

}
