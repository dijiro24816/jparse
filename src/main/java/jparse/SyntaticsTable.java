package jparse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public record SyntaticsTable(Grammar grammar, int stateCount, Action[] terminalSection, Action[] nonterminalSection)
		implements Serializable {

	public static SyntaticsTable create(Grammar grammar) {
		return new SyntaticsTableBuilder(grammar).build();
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

		for (int i = 0; i < stateCount(); i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");
			for (int j = 0; j < getTerminalSectionColumnLength(); j++) {
				//				Action[] actions = this.terminalSection.get(i);
				if (getTerminalAction(i, j) == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					//					stringBuilder.append(actions[j].toShortString());
					stringBuilder.append(getTerminalAction(i, j).toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < getTerminalSectionColumnLength())
					stringBuilder.append(',');
			}

			if (i + 1 < stateCount())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public String getGotosCSV() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\"\", ");
		stringBuilder.append(this.grammar.getNonterminalSymbolsCSV());
		stringBuilder.append(System.lineSeparator());

		for (int i = 0; i < this.stateCount; i++) {
			stringBuilder.append('"');
			stringBuilder.append(i);
			stringBuilder.append("\",");

			for (int j = 0; j < getNonterminalSectionColumnLength(); j++) {
				if (getNonterminalAction(i, j) == null) {
					stringBuilder.append("\"\"");
				} else {
					stringBuilder.append('"');
					stringBuilder.append(getNonterminalAction(i, j).toShortString());
					stringBuilder.append('"');
				}

				if (j + 1 < getNonterminalSectionColumnLength())
					stringBuilder.append(',');
			}

			if (i + 1 < stateCount())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public Rule getRuleOf(int index) {
		return this.grammar.getRules().get(index);
	}

	private int getNonterminalSectionColumnLength() {
		return this.grammar.getNonterminalSymbolCount();
	}

	private int getTerminalSectionColumnLength() {
		return this.grammar.getTerminalSymbolCount();
	}

	private Action getTerminalAction(int state, String symbol) {
		return getTerminalAction(state, this.grammar.getTerminalSymbolIndexOf(symbol));
	}

	private Action getTerminalAction(int row, int col) {
		return this.terminalSection[row * getTerminalSectionColumnLength() + col];
	}

	private Action getNonterminalAction(int state, String symbol) {
		return getNonterminalAction(state, this.grammar.getNonterminalSymbolIndexOf(symbol));
	}

	private Action getNonterminalAction(int row, int col) {
		return this.nonterminalSection[row * getNonterminalSectionColumnLength() + col];
	}

	public static SyntaticsTable deserialize(ObjectInputStream inStrm) throws ClassNotFoundException, IOException {
		return (SyntaticsTable) inStrm.readObject();
	}

	public void serialize(ObjectOutputStream outStrm) throws IOException {
		outStrm.writeObject(this);
	}
}
