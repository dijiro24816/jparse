package myprototype.jparse.token;

public final class KeywordToken extends Token {
	public enum Type {
		ABSTRACT, ASSERT, BOOLEAN, BREAK, BYTE, CASE, CATCH, CHAR, CLASS, CONST, CONTINUE, DEFAULT, DO, DOUBLE, ELSE, ENUM, EXTENDS, FINAL, FINALLY, FLOAT, FOR, IF, GOTO, IMPLEMENTS, IMPORT, INSTANCEOF, INT, INTERFACE, LONG, NATIVE, NEW, PACKAGE, PRIVATE, PROTECTED, PUBLIC, RETURN, SHORT, STATIC, STRICTFP, SUPER, SWITCH, SYNCHRONIZED, THIS, THROW, THROWS, TRANSICENT, TRY, VOID, VOLATILE, WHILE;
	};

	public Type value;

	public KeywordToken(int beg, int end, Type value) {
		super(beg, end);
		this.value = value;
	}

	public static KeywordToken capture(int beg, int end, String s) {
		switch (s) {

		case "abstract":
			return new KeywordToken(beg, end, KeywordToken.Type.ABSTRACT);
		case "assert":
			return new KeywordToken(beg, end, KeywordToken.Type.ASSERT);
		case "boolean":
			return new KeywordToken(beg, end, KeywordToken.Type.BOOLEAN);
		case "break":
			return new KeywordToken(beg, end, KeywordToken.Type.BREAK);
		case "byte":
			return new KeywordToken(beg, end, KeywordToken.Type.BYTE);
		case "case":
			return new KeywordToken(beg, end, KeywordToken.Type.CASE);
		case "catch":
			return new KeywordToken(beg, end, KeywordToken.Type.CATCH);
		case "char":
			return new KeywordToken(beg, end, KeywordToken.Type.CHAR);
		case "class":
			return new KeywordToken(beg, end, KeywordToken.Type.CLASS);
		case "const":
			return new KeywordToken(beg, end, KeywordToken.Type.CONST);
		case "continue":
			return new KeywordToken(beg, end, KeywordToken.Type.CONTINUE);
		case "default":
			return new KeywordToken(beg, end, KeywordToken.Type.DEFAULT);
		case "do":
			return new KeywordToken(beg, end, KeywordToken.Type.DO);
		case "double":
			return new KeywordToken(beg, end, KeywordToken.Type.DOUBLE);
		case "else":
			return new KeywordToken(beg, end, KeywordToken.Type.ELSE);
		case "enum":
			return new KeywordToken(beg, end, KeywordToken.Type.ENUM);
		case "extends":
			return new KeywordToken(beg, end, KeywordToken.Type.EXTENDS);
		case "final":
			return new KeywordToken(beg, end, KeywordToken.Type.FINAL);
		case "finally":
			return new KeywordToken(beg, end, KeywordToken.Type.FINALLY);
		case "float":
			return new KeywordToken(beg, end, KeywordToken.Type.FLOAT);
		case "for":
			return new KeywordToken(beg, end, KeywordToken.Type.FOR);
		case "if":
			return new KeywordToken(beg, end, KeywordToken.Type.IF);
		case "goto":
			return new KeywordToken(beg, end, KeywordToken.Type.GOTO);
		case "implements":
			return new KeywordToken(beg, end, KeywordToken.Type.IMPLEMENTS);
		case "import":
			return new KeywordToken(beg, end, KeywordToken.Type.IMPORT);
		case "instanceof":
			return new KeywordToken(beg, end, KeywordToken.Type.INSTANCEOF);
		case "int":
			return new KeywordToken(beg, end, KeywordToken.Type.INT);
		case "interface":
			return new KeywordToken(beg, end, KeywordToken.Type.INTERFACE);
		case "long":
			return new KeywordToken(beg, end, KeywordToken.Type.LONG);
		case "native":
			return new KeywordToken(beg, end, KeywordToken.Type.NATIVE);
		case "new":
			return new KeywordToken(beg, end, KeywordToken.Type.NEW);
		case "package":
			return new KeywordToken(beg, end, KeywordToken.Type.PACKAGE);
		case "private":
			return new KeywordToken(beg, end, KeywordToken.Type.PRIVATE);
		case "protected":
			return new KeywordToken(beg, end, KeywordToken.Type.PROTECTED);
		case "public":
			return new KeywordToken(beg, end, KeywordToken.Type.PUBLIC);
		case "return":
			return new KeywordToken(beg, end, KeywordToken.Type.RETURN);
		case "short":
			return new KeywordToken(beg, end, KeywordToken.Type.SHORT);
		case "static":
			return new KeywordToken(beg, end, KeywordToken.Type.STATIC);
		case "strictfp":
			return new KeywordToken(beg, end, KeywordToken.Type.STRICTFP);
		case "super":
			return new KeywordToken(beg, end, KeywordToken.Type.SUPER);
		case "switch":
			return new KeywordToken(beg, end, KeywordToken.Type.SWITCH);
		case "synchronized":
			return new KeywordToken(beg, end, KeywordToken.Type.SYNCHRONIZED);
		case "this":
			return new KeywordToken(beg, end, KeywordToken.Type.THIS);
		case "throw":
			return new KeywordToken(beg, end, KeywordToken.Type.THROW);
		case "throws":
			return new KeywordToken(beg, end, KeywordToken.Type.THROWS);
		case "transicent":
			return new KeywordToken(beg, end, KeywordToken.Type.TRANSICENT);
		case "try":
			return new KeywordToken(beg, end, KeywordToken.Type.TRY);
		case "void":
			return new KeywordToken(beg, end, KeywordToken.Type.VOID);
		case "volatile":
			return new KeywordToken(beg, end, KeywordToken.Type.VOLATILE);
		case "while":
			return new KeywordToken(beg, end, KeywordToken.Type.WHILE);
		}
		return null;
	}

	@Override
	public String toString() {
		return "KeywordToken [beg=" + beg + " end=" + end + " value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof KeywordToken tok) {
			return super.equals(obj) && this.value == tok.value;
		}
		return false;
	}
}
