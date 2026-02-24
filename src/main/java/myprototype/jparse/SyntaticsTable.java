package myprototype.jparse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SyntaticsTable implements Serializable {
	private static final long serialVersionUID = 1L;

	public static SyntaticsTable deserialize(ObjectInputStream inStrm) throws ClassNotFoundException, IOException {
		return (SyntaticsTable) inStrm.readObject();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Grammar grammar;

	private List<Action[]> nonterminalSection;

	private List<Action[]> terminalSection;

	public SyntaticsTable() {
		this.grammar = null;
		this.terminalSection = null;
		this.nonterminalSection = null;
	}

	public SyntaticsTable(Grammar grammar) {
		this.grammar = grammar;
		this.terminalSection = new ArrayList<Action[]>();
		this.nonterminalSection = new ArrayList<Action[]>();
	}

	public int createState() {
		if (this.terminalSection.size() + this.nonterminalSection.size() > 0)
			return 0;

		int state = createState(new HashMap<StateKey, Integer>(), StateKey.create(grammar.expandFirstItems()));

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

		return state;
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
		List<Item> closures = key.getClosures();
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
				for (String symbol : closure.getLookaheadSet()) {
					setTerminalSection(currentState, grammar.getTerminalSymbolIndexOf(symbol),
							new Action(ActionKind.Reduce, grammar.getRuleIndexOf(closure.getRule())));
				}
			}

//			System.out.println("closure " + currentState + ": " + closure);

//			System.out.println(closure);

			// TODO: We should return ?
		}
		for (StateKey derivativeKey : key.getDerivativeKeys(grammar)) {
			String rootSymbol = derivativeKey.getRootSymbol();
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

	public Action getAction(int state, String symbol) {
		return this.grammar.isNonterminalSymbol(symbol) ? getNonterminalAction(state, symbol)
				: getTerminalAction(state, symbol);
	}

	public String getActionsCSV() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\"\", ");
		stringBuilder.append(this.grammar.getTerminalSymbolsCSV());
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < this.terminalSection.size(); i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");
			for (int j = 0; j < getTerminalSectionColumnLength(); j++) {
				Action[] actions = this.terminalSection.get(i);
				if (actions[j] == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					stringBuilder.append(actions[j].toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < getTerminalSectionColumnLength())
					stringBuilder.append(',');
			}

			if (i + 1 < this.terminalSection.size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public String getGotosCSV() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\"\", ");
		stringBuilder.append(this.grammar.getNonterminalSymbolsCSV());
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < this.nonterminalSection.size(); i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");

			for (int j = 0; j < getNonterminalSectionColumnLength(); j++) {
				Action[] gotos = this.nonterminalSection.get(i);
				if (gotos[j] == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					stringBuilder.append(gotos[j].toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < getNonterminalSectionColumnLength())
					stringBuilder.append(',');
			}

			if (i + 1 < this.nonterminalSection.size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public Grammar getGrammar() {
		return grammar;
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

	public Action getNonterminalAction(int state, String symbol) {
		return this.nonterminalSection.get(state)[this.grammar.getNonterminalSymbolIndexOf(symbol)];
	}

	public List<Action[]> getNonterminalSection() {
		return nonterminalSection;
	}

	private int getNonterminalSectionColumnLength() {
		return this.grammar.getNonterminalSymbolCount();
	}

	public Rule getRuleOf(int index) {
		return this.grammar.getRules().get(index);
	}

	public Action getTerminalAction(int state, String symbol) {
		return this.terminalSection.get(state)[this.grammar.getTerminalSymbolIndexOf(symbol)];
	}

	public List<Action[]> getTerminalSection() {
		return terminalSection;
	}

	private int getTerminalSectionColumnLength() {
		return this.grammar.getTerminalSymbolCount();
	}

	public void serialize(ObjectOutputStream outStrm) throws IOException {
		outStrm.writeObject(this);
	}

	public void setGrammar(Grammar grammar) {
		this.grammar = grammar;
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

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();

		for (Action[] actions : this.terminalSection) {
			out.append(Arrays.toString(actions));
			out.append(System.lineSeparator());
		}

		return out.toString();
	}

}
