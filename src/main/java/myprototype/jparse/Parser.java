package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.lr1.SymbolCache;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.StmtNode;
import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.Lexer;

public class Parser {
	
	public SymbolCache source;
	
	private ParserData data;

	public Parser(Lexer lexer, ParserData data) {
		super();
		this.source = new SymbolCache(lexer);
		this.data = data;
	}
	
	public Parser(SymbolCache source, ParserData data) {
		super();
		this.source = source;
		this.data = data;
		
	}
	
	public StmtNode parse(InputStream inStrm) throws IOException {
		
//		int currentState = 0;
//		Stack<Integer> states = new Stack<>();
//		Stack<Symbol> symbols = new Stack<>();
//		
//		Stack<StateSymbol> stateSymbolStack = new Stack<>();
//		
//		Symbol symbol = null;
		
		Symbol symbol;
		
		try {
//			while (())
			
//			while (symbol != null || (symbol = this.tokenizer.tokenize(inStrm)) != null) {
//				System.out.println(symbol);
//				
//				Action action = this.data.getAction(currentState, symbol.ordinal());
//				if (action == null)
//					throw new RuntimeException("Invalid syntax");
//				
//				switch (action.getKind()) {
//				case Reduce:
//					symbols.push(this.data.getRuleTable().get(action.getArgumentValue()).compound.apply(stateSymbolStack));
//				case Shift:
//					stateSymbolStack.push(new StateSymbol(currentState, symbol));
//					currentState = action.getArgumentValue();
//					symbol = null;
//				default:
//					break;
//				}
//			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return new StmtNode();
	}
}
