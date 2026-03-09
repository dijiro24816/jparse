package jparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SyntaticsTableBuilder {
	private Grammar grammar;
	private List<Action[]> nonterminalSection;
	private List<Action[]> terminalSection;

	public SyntaticsTableBuilder(Grammar grammar) {
		this.grammar = grammar;
		this.terminalSection = new ArrayList<Action[]>();
		this.nonterminalSection = new ArrayList<Action[]>();
	}

	public SyntaticsTable build() {
		int state = createState(new HashMap<StateKey, Integer>(), StateKey.create(grammar));

		int lastState = getNewState();
		if (grammar.isNonterminalSymbol(grammar.getProductSymbol())) {
			setNonterminalSection(state, grammar.getNonterminalSymbolIndexOf(grammar.getProductSymbol()),
					new Action(ActionKind.Goto, lastState));
		} else {
			setTerminalSection(state, grammar.getTerminalSymbolIndexOf(grammar.getProductSymbol()),
					new Action(ActionKind.Shift, lastState));
		}
		if (grammar.isNonterminalSymbol(grammar.getEndSymbol())) {
			setNonterminalSection(lastState, grammar.getNonterminalSymbolIndexOf(grammar.getEndSymbol()),
					new Action(ActionKind.Accept));
		} else {
			setTerminalSection(lastState, grammar.getTerminalSymbolIndexOf(grammar.getEndSymbol()),
					new Action(ActionKind.Accept));
		}
		
		int stateCount = lastState + 1;
		
		Action[] terminalSectionArray = new Action[getTerminalSectionColumnLength() * stateCount];
		Action[] nonterminalSectionArray = new Action[getNonterminalSectionColumnLength() * stateCount];
		
		for (int i = 0; i < stateCount; i++) {
			for (int j = 0; j < getTerminalSectionColumnLength(); j++)
				terminalSectionArray[i * getTerminalSectionColumnLength() + j] = getTerminalAction(i, j);
			
			for (int j = 0; j < getNonterminalSectionColumnLength(); j++)
				nonterminalSectionArray[i * getNonterminalSectionColumnLength() + j] = getNonterminalAction(i, j);
		}
		
		return new SyntaticsTable(this.grammar, stateCount, terminalSectionArray, nonterminalSectionArray);
	}
	
	private int createState(HashMap<StateKey, Integer> keyStates, StateKey key) {

		if (keyStates.containsKey(key))
			return keyStates.get(key);

		int currentState = getNewState();
		
		//		if (currentState == 11) {
		//			StateKey k10 = null;
		//			StateKey k11 = key;
		//			
		//			for (StateKey k : keyStates.keySet()) {
		//				if (keyStates.get(k) == 10) {
		//					k10 = k;
		//					break;
		//				}
		//			}
		//			
		//			
		//			System.out.println(new HashSet(k10.getItems()).equals(new HashSet(k11.getItems())));
		//			System.out.println(k10.getItems().equals(k11.getItems()));
		//			System.out.println(k10);
		//			System.out.println(k11);
		//			
		//			
		//			System.exit(0);
		//		}

		keyStates.put(key, currentState);
		//		System.out.println("" + currentState + " -" + key);
		HashSet<Item> closures = key.closures();
		if (closures.size() > 0) {
			// Check reduce-reduce problem
			if (closures.size() > 1) {
				//				System.out.println(closures);

				// Java has reduce-reduce problem in:
				// BlockStatement.LocalVariableDeclarationStatement.VariableModifier.Annotation
				// BlockStatement.ClassOrInterfaceDeclaration.Modifier.Annotation
				boolean skip = false;

				if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("Modifier"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("Statement"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ArrayInitializer"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("TypeName"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ExpressionName"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ElementValue"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("InterfaceType"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ClassType"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("PrimaryNoNewArray"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("VariableModifier"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("PostfixExpression"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("UnannClassType"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("UnannClassOrInterfaceType"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("AdditionalBound"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("MethodModifier"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("PrimitiveType"))) {
					skip = true;
				} else if (closures.stream()
						.anyMatch(e -> e.getProductSymbol().equals("AnnotationInterfaceElementModifier"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ClassOrInterfaceType"))) {
					skip = true;
				} else if (closures.stream().anyMatch(e -> e.getProductSymbol().equals("ClassModifier"))) {
					skip = true;
				}

				if (!skip)
					throw new RuntimeException("The Grammar has reduce-reduce problem!");

				//				System.out.println("skip");
			}
			//			Item closure = closures.get(0);

			// Set reduce action as default
			// The value is rule index with negative sign
			// TODO: Use follow-set or lookahead-set

			for (Item closure : closures) {
				for (String symbol : closure.lookaheadSet()) {
					setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(symbol),
							new Action(ActionKind.Reduce, grammar.getRuleIndexOf(closure.rule())));
				}
			}

			//			System.out.println("closure " + currentState + ": " + closure);

			//			System.out.println(closure);

			// TODO: We should return ?
		}
		for (StateKey derivativeKey : key.getDerivativeKeys(grammar)) {
			String rootSymbol = derivativeKey.rootSymbol();
			if (grammar.isNonterminalSymbol(rootSymbol)) {
				setNonterminalSection(currentState, grammar.getNonterminalSymbolIndexOf(rootSymbol),
						new Action(ActionKind.Goto, createState(keyStates, derivativeKey)));

			} else if (grammar.isTerminalSymbol(rootSymbol)) {
				setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(rootSymbol),
						new Action(ActionKind.Shift, createState(keyStates, derivativeKey)));
			}
		}

		return currentState;

	}

	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = this.terminalSection.size();

		Action[] terminalSection = new Action[getTerminalSectionColumnLength()];
		this.terminalSection.add(terminalSection);

		Action[] nonterminalSection = new Action[getNonterminalSectionColumnLength()];
		this.nonterminalSection.add(nonterminalSection);

		return newState;
	}

	private Action getNonterminalAction(int row, int col) {
		return this.nonterminalSection.get(row)[col];
	}

	private int getNonterminalSectionColumnLength() {
		return this.grammar.getNonterminalSymbolCount();
	}
	
	private Action getTerminalAction(int row, int col) {
		return this.terminalSection.get(row)[col];
	}
	
	private int getTerminalSectionColumnLength() {
		return this.grammar.getTerminalSymbolCount();
	}

	private void setNonterminalSection(int row, int column, Action action) {
		this.nonterminalSection.get(row)[column] = action;
	}

	public void setNonterminalSection(List<Action[]> nonterminalSection) {
		this.nonterminalSection = nonterminalSection;
	}

	public void setTerminalSection(int row, Action action) {
		for (int column = 0; column < getTerminalSectionColumnLength(); column++)
			setTerminalSection(row, column, action);
	}

	private void setTerminalSection(int row, int column, Action action) {
		this.terminalSection.get(row)[column] = action;
	}

	public void setTerminalSection(List<Action[]> terminalSection) {
		this.terminalSection = terminalSection;
	}
}
