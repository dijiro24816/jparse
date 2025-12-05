package myprototype.jparse;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;
import myprototype.jparse.symbol.SymbolEnum;

public class ParserData {
	private int columnLength;

	private List<int[]> syntaticsTable;
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
		this.syntaticsTable = new ArrayList<int[]>();
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
						queue.offer(rule);

		return ruleSet;
	}

	private List<int[]> getData() {
		return syntaticsTable;
	}

	public List<int[]> getSyntaticsParserTable() {
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

		int[] stateLine = new int[getColumnLength()];
		for (int i = 0; i < stateLine.length; i++) {
			// FIXME: This is very bad implementation
			stateLine[i] = 2147483647;
		}
		syntaticsTable.add(stateLine);

		return newState;
	}

	public void setBehavior(int row, int column, int value) {
		getData().get(row)[column] = value;
	}
	
	public void setBehavior(int row, int value) {
		for (int column = 0; column < columnLength; column++)
			setBehavior(row, column, value);
	}
	
	public int getRuleIndex(Rule rule) {
		return this.ruleTableIndex.get(rule);
	}
	
	public String getRuleTableString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < this.ruleTable.size(); i++) {
			Rule rule = this.ruleTable.get(i);
			stringBuilder.append("Rule " + i + ": " + SymbolEnumToString(rule.getOwner().getSymbol()) + " -> " + String.join(" ", Arrays.asList(rule.getProductions()).stream().map(p -> SymbolEnumToString(p.getSymbol())).toList()));
			stringBuilder.append(System.lineSeparator());
		}
		
		return stringBuilder.toString();
	}
	
	public String SymbolEnumToString(SymbolEnum symbolEnum) {
		switch (symbolEnum) {
		case S:
			return "S";
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
		case ROUND_BRACKET_OPEN_SEPARATOR_TOKEN:
			return "(";
		case ROUND_BRACKET_CLOSE_SEPARATOR_TOKEN:
			return ")";
		default:
			return "UNDEFINED";
		}
	}
	
	public String getSyntaticsTableString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < this.syntaticsTable.size(); i++) {
			stringBuilder.append("State " + i + ": [");
			if (this.columnLength > 0) {
				int j = 0;
				for (;;) {
					if (j != SymbolEnum.EXP.ordinal() && j != SymbolEnum.ADDITION_OPERATOR_TOKEN.ordinal() && j != SymbolEnum.SUBTRACTION_OPERATOR_TOKEN.ordinal() && j != SymbolEnum.MULTIPLICATION_OPERATOR_TOKEN.ordinal() && j != SymbolEnum.DIVISION_OPERATOR_TOKEN.ordinal()
							&& j != SymbolEnum.INTEGER_LITERAL_TOKEN.ordinal()) {
						j++;
						if (j == this.columnLength)
							break;
						continue;
					}
					
					String symbolStamp = SymbolEnumToString(SymbolEnum.values()[j]);
					int a = this.syntaticsTable.get(i)[j];
					String action = a < 0 ? "r" + -(a + 1) : "s" + a;
					
					stringBuilder.append(symbolStamp + "(" + action + ")");
					
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
