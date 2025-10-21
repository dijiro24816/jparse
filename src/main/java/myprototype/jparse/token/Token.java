package myprototype.jparse.token;

public sealed class Token permits IdentifierToken, KeywordToken, LiteralToken {
	public int beg;
	public int end;

	public Token(int beg, int end) {
		super();
		this.beg = beg;
		this.end = end;
	}

	public static void main(String[] args) {
		System.out.println("ok");

		//		Token tok = new IdentifierToken(0, 0, "hello");
		Token tok = new KeywordToken(0, 0, KeywordToken.Type.PRIVATE);

		switch (tok) {
		case IdentifierToken identifier:
			System.out.println(identifier.value);
			break;

		case KeywordToken keyword:
			System.out.println(keyword.value);
			break;

		default:
			break;
		}
	}
}

//public enum Token {
//	IDENTIFIER {
//		public String identifier;
//	},
//	NUMBER {
//		public int number;
//	},
//	KEYWORD {
//		enum Keyword {
//			PUBLIC,
//			PRIVATE;
//		};
//		
//		public Keyword keyword;
//	},
//	LITERAL,
//	SEPARATOR,
//	OPERATOR;
//	
//	public Token createIdentifier(int beg, int end, String identifier) {
//		Token tok = Token.IDENTIFIER;
//		tok.identifier = identifier;
//	}
//	
//	public int beg;
//	public int end;
//}
//
//public class Token {
//	enum Type {
//		IDENTIFIER,
//		NUMBER,
//		KEYWORD,
//		LITERAL,
//		SEPARATOR,
//		OPERATOR
//	}
//	
//	public Type type;
//	
//	
//}
