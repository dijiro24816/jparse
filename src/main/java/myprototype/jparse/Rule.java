package myprototype.jparse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private PrecedenceRuleInfo precedenceInfo;

	private String productSymbol;

	private List<String> symbols;

	public Rule() {
		this.precedenceInfo = null;
		this.productSymbol = null;
		this.symbols = null;
	}
	
	public Rule(PrecedenceRuleInfo precedenceInfo, String productSymbol, List<String> symbols) {
		this.precedenceInfo = precedenceInfo;
		this.productSymbol = productSymbol;
		this.symbols = symbols;
	}

	public Rule(PrecedenceRuleInfo precedenceInfo, String productSymbol, String... symbols) {
		this(new PrecedenceRuleInfo(), productSymbol, Arrays.asList(symbols));
	}

	public Rule(String productSymbol, List<String> symbols) {
		this(new PrecedenceRuleInfo(), productSymbol, symbols);
	}

	public Rule(String productSymbol, String... symbols) {
		this(productSymbol, Arrays.asList(symbols));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != this.getClass())
			return false;

		Rule otherRule = (Rule) obj;

		return this.productSymbol.equals(otherRule.getProductSymbol()) && this.symbols.equals(otherRule.getSymbols())
				&& this.precedenceInfo.equals(otherRule.getPrecedenceInfo());
	}

	public String getFirstSymbol() {
		return this.symbols.size() > 0 ? this.symbols.get(0) : null;
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

	public void setPrecedenceInfo(PrecedenceRuleInfo precedenceInfo) {
		this.precedenceInfo = precedenceInfo;
	}

	public void setProductSymbol(String productSymbol) {
		this.productSymbol = productSymbol;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

	public int size() {
		return this.symbols.size();
	}

	@Override
	public String toString() {
		return this.productSymbol + " -> " + String.join(" ", this.symbols);
	}
}