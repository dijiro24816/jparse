package myprototype.jparse.symbol;

public class Symbol {
	private SymbolEnum kind;

	public final int ordinal() {
		return kind.ordinal();
	}

	private SymbolEnum getKind() {
		return kind;
	}

	private void setKind(SymbolEnum kind) {
		this.kind = kind;
	}

	public Symbol(SymbolEnum kind) {
		super();
		setKind(kind);
	}
}
