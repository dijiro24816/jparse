package myprototype.jparse;

public class SyntaxNode {
	Rule rule;
	Object[] elements;
	public Rule getRule() {
		return rule;
	}
	public Object[] getElements() {
		return elements;
	}
	
	public Object getElement(int index) {
		return elements[index];
	}
	
	
}