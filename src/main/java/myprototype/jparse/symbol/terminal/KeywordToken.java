package myprototype.jparse.symbol.terminal;

import myprototype.jparse.Symbol;
import myprototype.jparse.symbol.SymbolEnum;
import myprototype.jparse.symbol.terminal.keyword.AbstractKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.AssertKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BooleanKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.BreakKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ByteKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CaseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CatchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.CharKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ClassKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ConstKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ContinueKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DefaultKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.DoubleKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ElseKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.EnumKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ExtendsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinalKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FinallyKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.FloatKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ForKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.GotoKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IfKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImplementsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ImportKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InstanceofKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.IntKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.InterfaceKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.LongKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NativeKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.NewKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PackageKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PrivateKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ProtectedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.PublicKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ReturnKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ShortKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StaticKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.StrictfpKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SuperKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SwitchKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.SynchronizedKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThisKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.ThrowsKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TransientKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.TryKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VoidKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.VolatileKeywordToken;
import myprototype.jparse.symbol.terminal.keyword.WhileKeywordToken;

public class KeywordToken extends Terminal {
	public KeywordToken(SymbolEnum kind) {
		super(kind);
	}

	public KeywordToken(SymbolEnum kind, int beg, int end) {
		super(kind, beg, end);
	}

	public static Symbol capture(int beg, int end, String s) {
		switch (s) {

 		case "abstract":
			return new Symbol("abstract", beg, end);
		case "assert":
			return new Symbol("assert", beg, end);
		case "boolean":
			return new Symbol("boolean", beg, end);
		case "break":
			return new Symbol("break", beg, end);
		case "byte":
			return new Symbol("byte", beg, end);
		case "case":
			return new Symbol("case", beg, end);
		case "catch":
			return new Symbol("catch", beg, end);
		case "char":
			return new Symbol("char", beg, end);
		case "class":
			return new Symbol("class", beg, end);
		case "const":
			return new Symbol("const", beg, end);
		case "continue":
			return new Symbol("continue", beg, end);
		case "default":
			return new Symbol("default", beg, end);
		case "do":
			return new Symbol("do", beg, end);
		case "double":
			return new Symbol("double", beg, end);
		case "else":
			return new Symbol("else", beg, end);
		case "enum":
			return new Symbol("enum", beg, end);
		case "extends":
			return new Symbol("extends", beg, end);
		case "final":
			return new Symbol("final", beg, end);
		case "finally":
			return new Symbol("finally", beg, end);
		case "float":
			return new Symbol("float", beg, end);
		case "for":
			return new Symbol("for", beg, end);
		case "if":
			return new Symbol("if", beg, end);
		case "goto":
			return new Symbol("goto", beg, end);
		case "implements":
			return new Symbol("implements", beg, end);
		case "import":
			return new Symbol("import", beg, end);
		case "instanceof":
			return new Symbol("instanceof", beg, end);
		case "int":
			return new Symbol("int", beg, end);
		case "interface":
			return new Symbol("interface", beg, end);
		case "long":
			return new Symbol("long", beg, end);
		case "native":
			return new Symbol("native", beg, end);
		case "new":
			return new Symbol("new", beg, end);
		case "package":
			return new Symbol("package", beg, end);
		case "private":
			return new Symbol("private", beg, end);
		case "protected":
			return new Symbol("protected", beg, end);
		case "public":
			return new Symbol("public", beg, end);
		case "return":
			return new Symbol("return", beg, end);
		case "short":
			return new Symbol("short", beg, end);
		case "static":
			return new Symbol("static", beg, end);
		case "strictfp":
			return new Symbol("strictfp", beg, end);
		case "super":
			return new Symbol("super", beg, end);
		case "switch":
			return new Symbol("switch", beg, end);
		case "synchronized":
			return new Symbol("synchronized", beg, end);
		case "this":
			return new Symbol("this", beg, end);
		case "throw":
			return new Symbol("throw", beg, end);
		case "throws":
			return new Symbol("throws", beg, end);
		case "transient":
			return new Symbol("transient", beg, end);
		case "try":
			return new Symbol("try", beg, end);
		case "void":
			return new Symbol("void", beg, end);
		case "volatile":
			return new Symbol("volatile", beg, end);
		case "while":
			return new Symbol("while", beg, end);
		}
		return null;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [beg=" + getBeg() + " end=" + getEnd() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof KeywordToken tok) {
			return super.equals(obj);
		}
		return false;
	}
}
