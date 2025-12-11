package myprototype.jparse.lr1;

import java.io.IOException;
import java.io.InputStream;

import myprototype.jparse.Action;
import myprototype.jparse.ParserData;
import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.StmtNode;
import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.Lexer;

public class Parser {
	
	public BufferedLexer lexer;
	
	private ParserData data;

	public Parser(Lexer lexer, ParserData data) {
		super();
		this.lexer = new BufferedLexer(lexer);
		this.data = data;
	}
	
	public Parser(BufferedLexer source, ParserData data) {
		super();
		this.lexer = source;
		this.data = data;
		
	}
	
	public StmtNode parse(InputStream inStrm) throws IOException {
		
		int currentState = 0;
//		Stack<Integer> states = new Stack<>();
//		Stack<Symbol> symbols = new Stack<>();
//		
//		Stack<StateSymbol> stateSymbolStack = new Stack<>();
//		
//		Symbol symbol = null;
		
		Symbol symbol;
		
		try {
		while ((symbol = this.lexer.peek(inStrm, 0)) != null) {
				Action action = this.data.getAction(currentState, symbol.ordinal());
				if (action == null)
					throw new RuntimeException("Invalid syntax");
				
				switch (action.getKind()) {
				
				}
//				this.lexer.getSymbol(inStrm);
				System.out.println(this.lexer.getSymbol(inStrm));
			}
			
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
