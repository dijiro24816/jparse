
package myprototype.jparse.syntax.symbol.nonterminal;

import java.util.Stack;
import java.util.function.Function;

import myprototype.jparse.syntax.symbol.Symbol;
import myprototype.jparse.syntax.symbol.terminal.IdentifierToken;

public class Production<T extends Symbol> {
	Class<T> symbol;
	Rule<T>[] rules;

	public Production(Class<T> symbol, Rule<T>... rules) {
		this.symbol = symbol;
		this.rules = rules;
	}

	public static void main(String[] args) {
		Production<IdentifierToken> identifierTokenProduction = new Production<>(IdentifierToken.class);
		Function<Stack<Symbol>, QuolifiedIdentifierElement> compound = ;
		
		// QuolifiedIdentifierElement ::= IdentifierToken
//		Rule<QuolifiedIdentifierElement> quolifiedIdentifierElementRule = new Rule<QuolifiedIdentifierElement>(
//				(Stack<Symbol> stack) -> {
//					return null;
//				}, identifierTokenProduction);
		Production<QuolifiedIdentifierElement> QuolifiedIdentifierElementProduction = new Production<QuolifiedIdentifierElement>(
				QuolifiedIdentifierElement.class,
				new Rule<QuolifiedIdentifierElement>(
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
