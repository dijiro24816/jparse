package myprototype.jparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class OperatorPrecedenceRule {

	private HashMap<String, Integer> symbolLevels;
	private ArrayList<PrecedenceDirection> levelDirections;

	public OperatorPrecedenceRule() {
		this.symbolLevels = new HashMap<>();
		this.levelDirections = new ArrayList<>();
	}

	public void add(PrecedenceDirection direction, String... symbols) {
		for (String symbol : symbols)
			this.symbolLevels.put(symbol, this.levelDirections.size());
		this.levelDirections.add(direction);
	}

	public PrecedenceRuleInfo getInfo(String symbol) {
		return new PrecedenceRuleInfo(getPrecedenceDirection(symbol),
				getHighPrioritySymbols(symbol, PrecedenceDirection.Left),
				getHighPrioritySymbols(symbol, PrecedenceDirection.Right));
	}

	public HashSet<String> getHighPrioritySymbols(String symbol, PrecedenceDirection direction) {
		int symbolLevel = this.symbolLevels.get(symbol);
		return new HashSet<String>(this.symbolLevels.entrySet().stream().filter(e -> e.getValue() > symbolLevel)
				.filter(e -> this.levelDirections.get(e.getValue()) == direction).map(e -> e.getKey()).toList());
	}

	public PrecedenceDirection getPrecedenceDirection(String symbol) {
		return this.levelDirections.get(this.symbolLevels.get(symbol));
	}
}
