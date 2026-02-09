package myprototype.jparse;

public class Symbol {
	private String label;
	private int beg;
	private int end;
	private Object value;
	
	public String getLabel() {
		return label;
	}

	public Object getValue() {
		return value;
	}
	
	public int getBeg() {
		return beg;
	}

	public int getEnd() {
		return end;
	}

	public Symbol(String label, int beg, int end, Object value) {
		this.label = label;
		this.beg = beg;
		this.end = end;
		this.value = value;
	}
	
	public Symbol(String label, int beg, int end) {
		this(label, beg, end, null);
	}

	@Override
	public String toString() {
		return "Symbol [label=" + this.label + " beg=" + this.beg + " end=" + this.end + " value=" + this.value + "]";
	}
	
	
}
