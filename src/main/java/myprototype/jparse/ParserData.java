package myprototype.jparse;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;

public class ParserData {
	private int columnLength;

	private List<Action[]> syntaticsTable;
	private List<Rule> ruleTable;

	private HashMap<Rule, Integer> ruleTableIndex;

	public List<Rule> getRuleTable() {
		return ruleTable;
	}

	public ParserData(Class<? extends Enum<?>> symbolEnum, Production begProduction) {
		try {
			this.columnLength = ((Object[]) symbolEnum.getMethod("values").invoke(null)).length;
		} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		this.syntaticsTable = new ArrayList<Action[]>();
		this.ruleTable = getRuleSet(begProduction).stream().toList();
		this.ruleTableIndex = new HashMap<>();
		for (int i = 0; i < this.ruleTable.size(); i++)
			this.ruleTableIndex.put(this.ruleTable.get(i), i);
	}

	private HashSet<Rule> getRuleSet(Production orgProduction) {
		HashSet<Rule> ruleSet = new HashSet<>();
		ruleSet.addAll(orgProduction.getRules());
		ArrayDeque<Rule> queue = new ArrayDeque<>();

		queue.addAll(orgProduction.getRules());
		Rule rule;
		while ((rule = queue.poll()) != null)
			for (Production production : rule.getProductions())
				for (Rule derivativeRule : production.getRules())
					if (ruleSet.add(derivativeRule))
						queue.offer(derivativeRule);

		return ruleSet;
	}

	private List<Action[]> getData() {
		return syntaticsTable;
	}

	public List<Action[]> getSyntaticsParserTable() {
		return Collections.unmodifiableList(this.syntaticsTable);
	}

	public int getColumnLength() {
		return this.columnLength;
	}

	public int getRowLength() {
		return syntaticsTable.size();
	}

	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = getRowLength();

		Action[] actions = new Action[getColumnLength()];
		syntaticsTable.add(actions);

		return newState;
	}

	public void setAction(int row, int column, Action action) {
		getData().get(row)[column] = action;
	}

	public void setAction(int row, Action value) {
		for (int column = 0; column < columnLength; column++)
			setAction(row, column, value);
	}
	
	public Action getAction(int state, int token) {
		return getData().get(state)[token];
	}

	public int getRuleIndex(Rule rule) {
		return this.ruleTableIndex.get(rule);
	}

	public String getRuleTableString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < this.ruleTable.size(); i++) {
			Rule rule = this.ruleTable.get(i);
			stringBuilder.append("Rule " + i + ": " + SymbolEnumToString(rule.getOwner().getSymbol()) + " -> "
					+ String.join(" ", Arrays.asList(rule.getProductions()).stream()
							.map(p -> SymbolEnumToString(p.getSymbol())).toList()));
			stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public String SymbolEnumToString(SymbolEnum symbolEnum) {
		switch (symbolEnum) {
		case S:
			return "S";
		case STMT:
			return "Stmt";
		case ASSG:
			return "Assg";
		case INT_KEYWORD_TOKEN:
			return "int";
		case EXP:
			return "Exp";
		case ADDITION_OPERATOR_TOKEN:
			return "+";
		case SUBTRACTION_OPERATOR_TOKEN:
			return "-";
		case MULTIPLICATION_OPERATOR_TOKEN:
			return "*";
		case DIVISION_OPERATOR_TOKEN:
			return "/";
		case INTEGER_LITERAL_TOKEN:
			return "DIGIT";
		case IDENTIFIER_TOKEN:
			return "IDENT";
		default:
			return "UNDEFINED";
		}
	}

	public boolean isTargetSymbol(int sym) {
		return  sym == SymbolEnum.STMT.ordinal() ||
				sym == SymbolEnum.EXP.ordinal() ||
				sym == SymbolEnum.ADDITION_OPERATOR_TOKEN.ordinal() ||
				sym == SymbolEnum.SUBTRACTION_OPERATOR_TOKEN.ordinal() ||
				sym == SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN.ordinal() ||
				sym == SymbolEnum.DIVISION_OPERATOR_TOKEN.ordinal() ||
				sym == SymbolEnum.INTEGER_LITERAL_TOKEN.ordinal() ||
				sym == SymbolEnum.INT_KEYWORD_TOKEN.ordinal() ||
				sym == SymbolEnum.IDENTIFIER_TOKEN.ordinal() ||
				sym == SymbolEnum.ASSG.ordinal();
	}

	public String getSyntaticsTableString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < this.syntaticsTable.size(); i++) {
			stringBuilder.append("State " + i + ": [");
			if (this.columnLength > 0) {
				int j = 0;
				for (;;) {
					if (!isTargetSymbol(j)) {
						j++;
						if (j == this.columnLength)
							break;
						continue;
					}

					String symbolStamp = SymbolEnumToString(SymbolEnum.values()[j]);
					Action action = this.syntaticsTable.get(i)[j];
					String actionString = "None";
					if (action != null)
						actionString = action.getKind() == ActionKind.Accept
								? "" + action.getKind() + " " + action.getArgumentValue()
								: "" + action.getKind() + " " + action.getArgumentValue();

					stringBuilder.append(symbolStamp + "(" + actionString + ")");

					j++;
					if (j == this.columnLength)
						break;
					stringBuilder.append(", ");
				}
			}
			stringBuilder.append("]");

//			stringBuilder.append(Arrays.toString(this.syntaticsTable.get(i)));
			stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}
}
