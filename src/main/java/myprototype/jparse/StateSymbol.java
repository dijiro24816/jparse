package myprototype.jparse;

import myprototype.jparse.symbol.Symbol;

public class StateSymbol {
	private Symbol symbol;
	private int state;
	
	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public int getState() {
		return state;
	}

	public StateSymbol(int state, Symbol symbol) {
		this.symbol = symbol;
		this.state = state;
	}
}
