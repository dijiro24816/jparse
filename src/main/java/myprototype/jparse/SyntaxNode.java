package myprototype.jparse;

import java.io.Serializable;
import java.util.List;

public class SyntaxNode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Rule rule;
	List<Symbol> symbols;
	
	public SyntaxNode() {
		this.rule = null;
		this.symbols = null;
	}
	
	public SyntaxNode(Rule rule, List<Symbol> symbols) {
		super();
		this.rule = rule;
		this.symbols = symbols;
	}

	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public List<Symbol> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	@Override
	public String toString() {
		return "SyntaxNode [rule=" + rule + ", symbols=" + symbols + "]";
	}
}