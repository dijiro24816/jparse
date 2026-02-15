package myprototype.jparse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SyntaticsTable {
	private Grammar grammar;
	private int terminalSectionColumnLength;
	private int nonterminalSectionColumnLength;

	private List<Action[]> terminalSection;
	private List<Action[]> nonterminalSection;

	public Action getTerminalAction(int state, String symbol) {
		return this.terminalSection.get(state)[this.grammar.getTerminalSymbolIndexOf(symbol)];
	}
	
	public Action getNonterminalAction(int state, String symbol) {
		return this.nonterminalSection.get(state)[this.grammar.getNonterminalSymbolIndexOf(symbol)];
	}
	
	public Action getAction(int state, String symbol) { 
		return this.grammar.isNonterminalSymbol(symbol) ? getNonterminalAction(state, symbol) : getTerminalAction(state, symbol);
	}
	
	
	public SyntaticsTable(Grammar grammar) {
		this.grammar = grammar;
		this.terminalSectionColumnLength = grammar.getTerminalSymbolCount();
		this.nonterminalSectionColumnLength = grammar.getNonterminalSymbolCount();
		this.terminalSection = new ArrayList<Action[]>();
		this.nonterminalSection = new ArrayList<Action[]>();
		createState(grammar);
	}
	
	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = this.terminalSection.size();

		Action[] terminalSection = new Action[this.terminalSectionColumnLength];
		this.terminalSection.add(terminalSection);

		Action[] nonterminalSection = new Action[this.nonterminalSectionColumnLength];
		this.nonterminalSection.add(nonterminalSection);

		return newState;
	}

	private void setTerminalSection(int row, int column, Action action) {
		this.terminalSection.get(row)[column] = action;
	}

	public void setTerminalSection(int row, Action action) {
		for (int column = 0; column < this.terminalSectionColumnLength; column++)
			setTerminalSection(row, column, action);
	}

	private void setNonterminalSection(int row, int column, Action action) {
		this.nonterminalSection.get(row)[column] = action;
	}

//	private List<Item> excludeClosure(Collection<Item> items) {
//		return items.stream().filter(e -> !e.isTakingTheClosure()).toList();
//	}
	
	private int createState(Grammar grammar) {
		int state = createState(grammar, new HashMap<StateKey, Integer>(), StateKey.create(grammar.expandFirstItems()));
		setNonterminalSection(state, grammar.getNonterminalSymbolIndexOf(grammar.getStartSymbol()), new Action(ActionKind.Accept));
		return state;
	}

	private int createState(Grammar grammar, HashMap<StateKey, Integer> keyStates, StateKey key) {

		if (keyStates.containsKey(key))
			return keyStates.get(key);

		int currentState = getNewState();

		keyStates.put(key, currentState);
		
		List<Item> closures = key.getClosures();
		if (closures.size() > 0) {
			// Check reduce-reduce problem
			if (closures.size() > 1)
				throw new RuntimeException("The Grammar has reduce-reduce problem!");
			Item closure = closures.get(0);

			// Set reduce action as default
			// The value is rule index with negative sign
			// TODO: Use follow-set or lookahead-set

			for (String symbol : closure.getLookaheadSet()) {
				setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(symbol),
						new Action(ActionKind.Reduce, grammar.getRuleIndexOf(closure.getRule())));
			}
			
			
//			System.out.println("closure " + currentState + ": " + closure);

//			System.out.println(closure);
			
			// TODO: We should return ?
		}

		for (StateKey derivativeKey : key.getDerivativeKeys(grammar)) {
			String rootSymbol = derivativeKey.getRootSymbol();
			if (grammar.isNonterminalSymbol(rootSymbol)) {
				setNonterminalSection(currentState, grammar.getNonterminalSymbolIndexOf(rootSymbol),
						new Action(ActionKind.Goto, createState(grammar, keyStates, derivativeKey)));

			} else if (grammar.isTerminalSymbol(rootSymbol)) {
				setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(rootSymbol),
						new Action(ActionKind.Shift, createState(grammar, keyStates, derivativeKey)));
			}
		}
		
		return currentState;

	}

//	private int createState(Grammar grammar, HashSet<Item> orgItemKey, HashMap<HashSet<Item>, Integer> itemStates) {
//		// return states if already existing
//		// FIXME: This is very bad implementation
//		for (HashSet<Item> itemKey : itemStates.keySet()) {
//			if (itemKey.equals(orgItemKey))
//				return itemStates.get(itemKey);
//		}
//
//		if (itemStates.containsKey(orgItemKey))
//			return itemStates.get(orgItemKey);
//
//		//		// For debug
//		//		for (Item item : orgItemKey) {
//		//			System.out.println(item);
//		//		}
//
//		int currentState = getNewState();
//
//		// Make rule scenario set that has not moveable dot
//		HashSet<Item> itemKeyClone = new HashSet<>();
//		for (Item item : orgItemKey)
//			itemKeyClone.add(item.clone());
//
//		itemStates.put(itemKeyClone, currentState);
//
//		// Taking the closure
//		List<Item> closures = orgItemKey.stream().filter(e -> e.isTakingTheClosure()).toList();
//		if (closures.size() > 0) {
//			// Check reduce-reduce problem
//			if (closures.size() > 1)
//				throw new RuntimeException("The Grammar has reduce-reduce problem!");
//			Item closure = closures.get(0);
//
//			// Set reduce action as default
//			// The value is rule index with negative sign
//			// TODO: Use follow-set or lookahead-set
//
//			for (String symbol : closure.getLookaheadSet()) {
//				setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(symbol),
//						new Action(ActionKind.Reduce, grammar.getRuleIndexOf(closure.getRule())));
//			}
//
//			System.out.println(closure);
//		}
//
//		ArrayList<Item> items = new ArrayList<>(grammar.expandItems(excludeClosure(orgItemKey)));
//		if (items.size() == 0)
//			return currentState;
//
//		items.sort(Comparator.comparing(e -> ((Item) e).getDotSymbol()));
//		if (items.size() == 0)
//			return currentState;
//
//		int begIndex = 0, endIndex = 0;
//		String currentSymbol = items.get(begIndex).getDotSymbol();
//		for (;;) {
//			endIndex++;
//			if ((endIndex == items.size())
//					|| !currentSymbol.equals(items.get(endIndex).getDotSymbol())) {
//				HashSet<Item> partOfItems = new HashSet<>(items.subList(begIndex, endIndex));
//
//				for (Item item : partOfItems)
//					item.increaseDot();
//
//				// Store the next state for the symbol from the current state
//
//				if (grammar.isNonterminalSymbol(currentSymbol)) {
//					setNonterminalSection(currentState, grammar.getNonterminalSymbolIndexOf(currentSymbol),
//							new Action(ActionKind.Goto, createState(grammar, partOfItems, itemStates)));
//
//				} else if (grammar.isTerminalSymbol(currentSymbol)) {
//					setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(currentSymbol),
//							new Action(ActionKind.Shift, createState(grammar, partOfItems, itemStates)));
//				}
//
//				// postfix processing
//				if (endIndex == items.size())
//					break;
//				begIndex = endIndex;
//				currentSymbol = items.get(begIndex).getDotSymbol();
//			}
//		}
//
//		return currentState;
//	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		for (Action[] actions : this.terminalSection) {
			out.append(Arrays.toString(actions));
			out.append(System.lineSeparator());
		}

		return out.toString();
	}

	public String getActionsCSV(Grammar grammar) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\"\", ");
		stringBuilder.append(grammar.getTerminalSymbolsCSV());
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < this.terminalSection.size(); i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");
			for (int j = 0; j < this.terminalSectionColumnLength; j++) {
				Action[] actions = this.terminalSection.get(i);
				if (actions[j] == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					stringBuilder.append(actions[j].toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < this.terminalSectionColumnLength)
					stringBuilder.append(',');
			}
			
			if (i + 1 < this.terminalSection.size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public String getGotosCSV(Grammar grammar) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\"\", ");
		stringBuilder.append(grammar.getNonterminalSymbolsCSV());
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < this.nonterminalSection.size(); i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");

			for (int j = 0; j < this.nonterminalSectionColumnLength; j++) {
				Action[] gotos = this.nonterminalSection.get(i);
				if (gotos[j] == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					stringBuilder.append(gotos[j].toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < this.nonterminalSectionColumnLength)
					stringBuilder.append(',');
			}
			
			if (i + 1 < this.nonterminalSection.size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

}
