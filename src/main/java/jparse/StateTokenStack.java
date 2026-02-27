package jparse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StateTokenStack {
	private Stack<Integer> states;
	private Stack<Token> tokens;
	
	public StateTokenStack() {
		this.states = new Stack<>();
		this.tokens = new Stack<>();
	}

	public int getCurrentState() {
		return states.peek();
	}
	
	public Token getCurrentSymbol() {
		return this.tokens.peek();
	}
	
	public Token pop() {
		this.states.pop();
		return tokens.pop();
	}

	public List<Token> pop(int length) {
		List<Token> symbols = new ArrayList<>();
		for (int i = 0; i < length; i++)
			symbols.add(pop());
		
		return symbols;
	}
	
	public int push(Integer item) {
		return states.push(item);
	}

	public Token push(Token item) {
		return tokens.push(item);
	}
	
	public void push(Token symbol, int state) {
		push(symbol);
		push(state);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("StateTokenStack[");
		
		int i = 0, j = 0;
		for (;;) {
			if (this.states.size() > i) {
				stringBuilder.append(this.states.get(i));
			} else {
				break;
			}
			i++;
			
			if (this.tokens.size() > j) {
				stringBuilder.append(' ');
				stringBuilder.append(this.tokens.get(j).label());
				
				if (this.states.size() > i)
					stringBuilder.append(' ');
			} else {
				break;
			}
			j++;
		}
		
		stringBuilder.append("]");
		
		return stringBuilder.toString();
	}
	
	
}
