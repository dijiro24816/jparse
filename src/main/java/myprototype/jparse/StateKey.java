package myprototype.jparse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class StateKey {
	private String rootSymbol;
	
	private List<Item> items;
	private List<Item> closures;
	
	private StateKey(List<Item> items, List<Item> closures) {
		
		this.items = items;
		this.closures = closures;
	}
	
	protected List<Item> getItems() {
		return this.items;
	}
	
	public List<Item> getClosures() {
		return this.closures;
	}
	
	public static StateKey create(List<Item> orgItems) {
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<Item> closures = new ArrayList<>();
		Comparator<Item> comparator = Comparator
			    .<Item, String>comparing(Item::getDotSymbol)
			    .thenComparing(e -> new ArrayList<>(e.getLookaheadSet()).stream().sorted(String.CASE_INSENSITIVE_ORDER).toString(), String.CASE_INSENSITIVE_ORDER);
		
		for (Item item : new ArrayList<>(orgItems).stream().sorted(comparator).toList()) {
			if (item.isTakingTheClosure())
				closures.add(item);
			else
				items.add(item);
		}
		
		if (items.size() > 1)
			throw new RuntimeException("The Grammar has reduce-reduce problem!");
		
		return new StateKey(items, closures);
	}
	
	public ArrayList<List<Item>> getDerivativeItemsList() {
		ArrayList<List<Item>> derivativeItemsList = new ArrayList<>();
		
		if (this.items.size() == 0)
			return derivativeItemsList;
		
		int begIndex = 0, endIndex = 0;
		String currentSymbol = items.get(begIndex).getDotSymbol();
		HashSet<String> currentLookaheadSet = items.get(begIndex).getLookaheadSet();
		for (;;) {
			endIndex++;
			if ((endIndex == items.size())
					|| !currentSymbol.equals(items.get(endIndex).getDotSymbol()) || !currentLookaheadSet.equals(items.get(endIndex).getLookaheadSet())) {
				
				List<Item> partOfItems = items.subList(begIndex, endIndex);
				
				for (Item item : partOfItems)
					item.increaseDot();
				
				derivativeItemsList.add(partOfItems);
				
				// postfix processing
				if (endIndex == items.size())
					break;
				begIndex = endIndex;
				currentSymbol = items.get(begIndex).getDotSymbol();
			}
		}
		return derivativeItemsList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;

		StateKey other = (StateKey)obj;
		
		return this.items.equals(other.getItems()) && this.closures.equals(other.getClosures());
	}
	
	
}
