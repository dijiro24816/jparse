
package myprototype.jparse.symbol;

import myprototype.jparse.symbol.terminal.Terminal;

public class Production {
	private Class<? extends Symbol> symbol;
	private Rule[] rules;

	public Class<? extends Symbol> getSymbol() {
		return symbol;
	}

	private void setSymbol(Class<? extends Symbol> symbol) {
		this.symbol = symbol;
	}

	public Rule[] getRules() {
		return rules;
	}

	public void setRules(Rule[] rules) {
		this.rules = rules;
	}
	
	@SafeVarargs
	public Production(Class<? extends Symbol> symbol, Rule... rules) {
		this.setSymbol(symbol);
		setRules(rules);
	}
	
	public boolean isTerminal() {
		return getSymbol().isAssignableFrom(Terminal.class);
	}

	public static void main(String[] args) {

		
//		List l = new ArrayList<Number>();
//		List<String> ls = l; // 型の不一致
//		l.add(0, new Integer(42)); // Integerオブジェクトを追加
//		String s = ls.get(0); // ここでClassCastExceptionが発生する
		
		System.exit(0);
		
		
//		Production<IdentifierToken> identifierTokenProduction = new Production<>(IdentifierToken.class);
//		Function<Stack<Symbol>, QuolifiedIdentifierElement> compound = ;
		
		// QuolifiedIdentifierElement ::= IdentifierToken
//		Rule<QuolifiedIdentifierElement> quolifiedIdentifierElementRule = new Rule<QuolifiedIdentifierElement>(
//				(Stack<Symbol> stack) -> {
//					return null;
//				}, identifierTokenProduction);
//		Production<QuolifiedIdentifierNode> QuolifiedIdentifierElementProduction = new Production<>(
//				QuolifiedIdentifierNode.class,
//				new Rule<>(
//						(Stack<Symbol> stack) -> {
//							return null;
//						}, identifierTokenProduction)
//				);
		
		
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
