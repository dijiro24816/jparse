package myprototype.jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import myprototype.jparse.symbol.Symbol;
import myprototype.jparse.symbol.nonterminal.CompilationUnit;
import myprototype.jparse.symbol.nonterminal.SNode;
import myprototype.jparse.symbol.nonterminal.StmtNode;
import myprototype.jparse.symbol.terminal.InvalidTokenException;
import myprototype.jparse.symbol.terminal.Lexer;
import myprototype.jparse.symbol.terminal.Terminal;

public class Parser {
	
	public Lexer tokenizer;
	
	private ParserData data;

	public Parser(Lexer tokenizer, ParserData data) {
		super();
		this.tokenizer = tokenizer;
		this.data = data;
	}
	
	public StmtNode parse(InputStream inStrm) throws IOException {
		
		int currentState = 0;
		Stack<Integer> states = new Stack<>();
		Stack<Symbol> symbols = new Stack<>();
		
		Stack<StateSymbol> stateSymbolStack = new Stack<>();
		
		Symbol symbol = null;
		
		try {
			while (symbol != null || (symbol = this.tokenizer.tokenize(inStrm)) != null) {
				System.out.println(symbol);
				
				Action action = this.data.getAction(currentState, symbol.ordinal());
				if (action == null)
					throw new RuntimeException("Invalid syntax");
				
				switch (action.getKind()) {
				case Reduce:
					symbols.push(this.data.getRuleTable().get(action.getArgumentValue()).compound.apply(stateSymbolStack));
				case Shift:
					stateSymbolStack.push(new StateSymbol(currentState, symbol));
					currentState = action.getArgumentValue();
					symbol = null;
				default:
					break;
				}
			}
		} catch (InvalidTokenException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return new StmtNode();
	}
}
