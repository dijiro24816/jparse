package myprototype.jparse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SyntaticsTable {
	private int columnLength;

	private List<Action[]> actionsList;
	private List<Action[]> gotosList;

	public SyntaticsTable(Grammar grammar) {
		this.columnLength = grammar.getNormalAndTerminalCount();
		setupActionData(grammar);
	}

	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = this.actionsList.size();

		Action[] actions = new Action[this.columnLength];
		this.actionsList.add(actions);

		return newState;
	}

	public void setAction(int row, int column, Action action) {
		this.actionsList.get(row)[column] = action;
	}

	public void setAction(int row, Action value) {
		for (int column = 0; column < this.columnLength; column++)
			setAction(row, column, value);
	}

	private void setupActionData(Grammar grammar) {
		this.actionsList = new ArrayList<Action[]>();

		HashSet<Item> itemStatesKey = new HashSet<>(Item.generateItemsOf(grammar));

		takeAction(grammar, itemStatesKey, new HashMap<HashSet<Item>, Action>());
	}

	private List<Item> excludeClosure(Collection<Item> items) {
		return items.stream().filter(e -> !e.isTakingTheClosure()).toList();
	}

	private List<Item> expandItemsDot(Grammar grammar, Collection<Item> orgItems) {
		List<Item> items = new ArrayList<>();
		HashSet<String> expandedNonterminals = new HashSet<>();
		ArrayDeque<Item> itemQueue = new ArrayDeque<>();

		orgItems.stream().forEach(e -> itemQueue.offer(e));

		Item item;
		while ((item = itemQueue.poll()) != null) {
			items.add(item);
			if (grammar.isNonterminalSymbol(item.getDotSymbol())
					&& !expandedNonterminals.contains(item.getDotSymbol())) {
				// Get all of derivative rules fromd dot production, and convert Rule to
				// RuleScenario, and then push it on queue
				item.generateDotItemsOf(grammar).stream().forEach(e -> itemQueue.offer(e));
			}
		}

		return items;
	}

	private Action takeAction(Grammar grammar, HashSet<Item> orgItemKey, HashMap<HashSet<Item>, Action> itemStates) {
		// return states if already existing
		// FIXME: This is very bad implementation
		for (HashSet<Item> itemKey : itemStates.keySet()) {
			if (itemKey.equals(orgItemKey)) {
				return itemStates.get(itemKey);
			}
		}

		if (itemStates.containsKey(orgItemKey))
			return itemStates.get(orgItemKey);

		int currentState = getNewState();
		Action enterAction = new Action(ActionKind.Shift, currentState);

		// Make rule scenario set that has not moveable dot
		HashSet<Item> itemKeyClone = new HashSet<>();
		for (Item item : orgItemKey)
			itemKeyClone.add(item.clone());

		itemStates.put(itemKeyClone, enterAction);

		// Taking the closure
		List<Item> closures = orgItemKey.stream().filter(e -> e.isTakingTheClosure()).toList();
		if (closures.size() > 0) {
			// Check reduce-reduce problem
			if (closures.size() > 1)
				throw new RuntimeException("The Grammar has reduce-reduce problem!");
			Item closure = closures.get(0);

			// Set reduce action as default
			// The value is rule index with negative sign
			// TODO: Use follow-set or lookahead-set
			setAction(currentState, new Action(ActionKind.Reduce, grammar.getRuleIndexOf(closure.getRule())));
		}

		List<Item> items = expandItemsDot(grammar, excludeClosure(orgItemKey));
		if (items.size() == 0)
			return enterAction;

		items.sort(Comparator.comparing(e -> ((Item) e).getDotSymbol()));
		if (items.size() == 0)
			return enterAction;

		int begIndex = 0, endIndex = 0;
		String begSymbol = items.get(begIndex).getDotSymbol();
		for (;;) {
			endIndex++;
			if ((endIndex == items.size())
					|| !begSymbol.equals(items.get(endIndex).getDotSymbol())) {
				HashSet<Item> partOfItems = new HashSet<>(items.subList(begIndex, endIndex));

				for (Item item : partOfItems)
					item.increaseDot();

				// Store next state for the symbol from the current state
				setAction(currentState, grammar.getNonterminalSymbolIndexOf(begSymbol),
						takeAction(grammar, partOfItems, itemStates));

				// postfix processing
				if (endIndex == items.size())
					break;
				begIndex = endIndex;
				begSymbol = items.get(begIndex).getDotSymbol();
			}
		}

		return enterAction;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		for (Action[] actions : this.actionsList) {
			out.append(Arrays.toString(actions));
			out.append(System.lineSeparator());
		}
		
		return out.toString();
	}
	
	
}
