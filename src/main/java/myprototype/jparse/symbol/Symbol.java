package myprototype.jparse.symbol;

public class Symbol {
	private SymbolKind kind;

	public SymbolKind getKind() {
		return kind;
	}

	private void setKind(SymbolKind kind) {
		this.kind = kind;
	}

	public Symbol(SymbolKind kind) {
		super();
		setKind(kind);
	}
}
