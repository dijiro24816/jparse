package myprototype.jparse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StateSymbolStack {
	private Stack<Integer> states;
	private Stack<Symbol> symbols;
	
	public StateSymbolStack() {
		this.states = new Stack<>();
		this.symbols = new Stack<>();
	}

	public int push(Integer item) {
		return states.push(item);
	}
	
	public Symbol push(Symbol item) {
		return symbols.push(item);
	}
	
	public void push(Symbol symbol, int state) {
		push(symbol);
		push(state);
	}

	public Symbol pop() {
		this.states.pop();
		return symbols.pop();
	}
	
	public List<Symbol> pop(int length) {
		List<Symbol> symbols = new ArrayList<>();
		for (int i = 0; i < length; i++)
			symbols.add(pop());
		
		return symbols;
	}

	public int getCurrentState() {
		return states.peek();
	}
}
