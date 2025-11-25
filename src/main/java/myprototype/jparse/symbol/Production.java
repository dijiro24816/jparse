
package myprototype.jparse.symbol;

import java.util.ArrayList;
import java.util.Stack;

import myprototype.jparse.symbol.nonterminal.QuolifiedIdentifierNode;
import myprototype.jparse.symbol.terminal.IdentifierToken;


class B {

	@Override
	public String toString() {
		return "B []";
	}
}

class A {
	ArrayList<?>[] arrayLists;
	
	public A(ArrayList<?>... arrayLists) {
		this.arrayLists = arrayLists;
	}
}

public class Production<T extends Symbol> {
	private Class<T> symbol;
	private Rule<T>[] rules;

	public Class<T> getSymbol() {
		return symbol;
	}

	private void setSymbol(Class<T> symbol) {
		this.symbol = symbol;
	}

	public Rule<T>[] getRules() {
		return rules;
	}

	public void setRules(Rule<T>[] rules) {
		this.rules = rules;
	}
	@SafeVarargs
	public Production(Class<T> symbol, Rule<T>... rules) {
		this.setSymbol(symbol);
		setRules(rules);
	}

	public static void main(String[] args) {

		
//		List l = new ArrayList<Number>();
//		List<String> ls = l; // 型の不一致
//		l.add(0, new Integer(42)); // Integerオブジェクトを追加
//		String s = ls.get(0); // ここでClassCastExceptionが発生する
		
		System.exit(0);
		
		
		Production<IdentifierToken> identifierTokenProduction = new Production<>(IdentifierToken.class);
//		Function<Stack<Symbol>, QuolifiedIdentifierElement> compound = ;
		
		// QuolifiedIdentifierElement ::= IdentifierToken
//		Rule<QuolifiedIdentifierElement> quolifiedIdentifierElementRule = new Rule<QuolifiedIdentifierElement>(
//				(Stack<Symbol> stack) -> {
//					return null;
//				}, identifierTokenProduction);
		Production<QuolifiedIdentifierNode> QuolifiedIdentifierElementProduction = new Production<>(
				QuolifiedIdentifierNode.class,
				new Rule<>(
						(Stack<Symbol> stack) -> {
							return null;
						}, identifierTokenProduction)
				);
		
		
//		Production<QuolifiedIdentifierElement> product = new Production<QuolifiedIdentifierElement>(
//				QuolifiedIdentifierElement.class,
//				new Rule<QuolifiedIdentifierElement>(
//						(Stack<Symbol> stack) -> {
//							return null;
//						},
//						new Production<QuolifiedIdentifierElement>(IdentifierToken.class)
//						)
//				);
	}



}
