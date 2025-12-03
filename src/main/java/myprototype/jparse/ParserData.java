package myprototype.jparse;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import myprototype.jparse.symbol.Production;
import myprototype.jparse.symbol.Rule;

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

		syntaticsTable.add(new int[getColumnLength()]);

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
}
