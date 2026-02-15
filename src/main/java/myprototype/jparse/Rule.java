package myprototype.jparse;

import java.util.Arrays;
import java.util.List;

public class Rule {
	public static void main(String[] args) {
		Rule rule = new Rule("Stmt", "+", "Exp", "Exp");
		System.out.println(rule);
	}
private PrecedenceRuleInfo precedenceInfo;
	
	private String productSymbol;
	
	//	private Function<StateSymbolStack, Object> compounder;
	private List<String> symbols; // symbols.size() > 0

	public Rule(PrecedenceRuleInfo precedenceInfo, String productSymbol, String...symbols) {
		this.precedenceInfo = precedenceInfo;
		this.productSymbol = productSymbol;
		this.symbols = Arrays.asList(symbols);
	}

//	public Function<StateSymbolStack, Object> getCompounder() {
//		return compounder;
//	}

	public Rule(String productSymbol, String...symbols) {
		this(new PrecedenceRuleInfo(), productSymbol, symbols);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this)
			return true;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		Rule otherRule = (Rule)obj;
		
		return this.productSymbol.equals(otherRule.getProductSymbol()) && this.symbols.equals(otherRule.getSymbols()) && this.precedenceInfo.equals(otherRule.getPrecedenceInfo());
	}
	
	public String getFirstSymbol() {
		return symbols.get(0);
	}
	
	public PrecedenceRuleInfo getPrecedenceInfo() {
		return precedenceInfo;
	}
	
	public String getProductSymbol() {
		return productSymbol;
	}
	
	public List<String> getSymbols() {
		return symbols;
	}

	@Override
	public int hashCode() {
		return this.productSymbol.hashCode() + this.symbols.hashCode() + this.precedenceInfo.hashCode();
	}
	
	public boolean isEmpty() {
		return this.symbols.isEmpty();
	}
	
	public boolean isReplaceable() {
		return this.symbols.size() == 1;
	}
	
	public int size() {
		return this.symbols.size();
	}

	@Override
	public String toString() {
		return this.productSymbol + " -> " + String.join(" ", this.symbols);
	}
}