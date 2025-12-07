package myprototype.jparse.symbol;

public class Symbol {
	private SymbolEnum symbolEnum;

	public final int ordinal() {
		return symbolEnum.ordinal();
	}

	private SymbolEnum getSymbolEnum() {
		return symbolEnum;
	}

	private void setSymbolEnum(SymbolEnum symbolEnum) {
		this.symbolEnum = symbolEnum;
	}

	public Symbol(SymbolEnum symbolEnum) {
		super();
		this.symbolEnum = symbolEnum;
	}
}
