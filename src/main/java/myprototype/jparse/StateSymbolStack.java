package myprototype.jparse;

import java.util.Stack;

import myprototype.jparse.symbol.Symbol;

public class StateSymbolStack {
	private Stack<Integer> states;
	private Stack<Symbol> symbols;
	
	public StateSymbolStack() {
		this.states.push(0);
	}

	public Integer push(Integer item) {
		return states.push(item);
	}

	public Symbol push(Symbol item) {
		return symbols.push(item);
	}

	public Symbol pop() {
		this.states.pop();
		return symbols.pop();
	}

	public Integer peek() {
		return states.peek();
	}
}
