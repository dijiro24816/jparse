package jparse.symbol.terminal;

import jparse.Token;
import jparse.symbol.SymbolEnum;
import jparse.symbol.terminal.keyword.AbstractKeywordToken;
import jparse.symbol.terminal.keyword.AssertKeywordToken;
import jparse.symbol.terminal.keyword.BooleanKeywordToken;
import jparse.symbol.terminal.keyword.BreakKeywordToken;
import jparse.symbol.terminal.keyword.ByteKeywordToken;
import jparse.symbol.terminal.keyword.CaseKeywordToken;
import jparse.symbol.terminal.keyword.CatchKeywordToken;
import jparse.symbol.terminal.keyword.CharKeywordToken;
import jparse.symbol.terminal.keyword.ClassKeywordToken;
import jparse.symbol.terminal.keyword.ConstKeywordToken;
import jparse.symbol.terminal.keyword.ContinueKeywordToken;
import jparse.symbol.terminal.keyword.DefaultKeywordToken;
import jparse.symbol.terminal.keyword.DoKeywordToken;
import jparse.symbol.terminal.keyword.DoubleKeywordToken;
import jparse.symbol.terminal.keyword.ElseKeywordToken;
import jparse.symbol.terminal.keyword.EnumKeywordToken;
import jparse.symbol.terminal.keyword.ExtendsKeywordToken;
import jparse.symbol.terminal.keyword.FinalKeywordToken;
import jparse.symbol.terminal.keyword.FinallyKeywordToken;
import jparse.symbol.terminal.keyword.FloatKeywordToken;
import jparse.symbol.terminal.keyword.ForKeywordToken;
import jparse.symbol.terminal.keyword.GotoKeywordToken;
import jparse.symbol.terminal.keyword.IfKeywordToken;
import jparse.symbol.terminal.keyword.ImplementsKeywordToken;
import jparse.symbol.terminal.keyword.ImportKeywordToken;
import jparse.symbol.terminal.keyword.InstanceofKeywordToken;
import jparse.symbol.terminal.keyword.IntKeywordToken;
import jparse.symbol.terminal.keyword.InterfaceKeywordToken;
import jparse.symbol.terminal.keyword.LongKeywordToken;
import jparse.symbol.terminal.keyword.NativeKeywordToken;
import jparse.symbol.terminal.keyword.NewKeywordToken;
import jparse.symbol.terminal.keyword.PackageKeywordToken;
import jparse.symbol.terminal.keyword.PrivateKeywordToken;
import jparse.symbol.terminal.keyword.ProtectedKeywordToken;
import jparse.symbol.terminal.keyword.PublicKeywordToken;
import jparse.symbol.terminal.keyword.ReturnKeywordToken;
import jparse.symbol.terminal.keyword.ShortKeywordToken;
import jparse.symbol.terminal.keyword.StaticKeywordToken;
import jparse.symbol.terminal.keyword.StrictfpKeywordToken;
import jparse.symbol.terminal.keyword.SuperKeywordToken;
import jparse.symbol.terminal.keyword.SwitchKeywordToken;
import jparse.symbol.terminal.keyword.SynchronizedKeywordToken;
import jparse.symbol.terminal.keyword.ThisKeywordToken;
import jparse.symbol.terminal.keyword.ThrowKeywordToken;
import jparse.symbol.terminal.keyword.ThrowsKeywordToken;
import jparse.symbol.terminal.keyword.TransientKeywordToken;
import jparse.symbol.terminal.keyword.TryKeywordToken;
import jparse.symbol.terminal.keyword.VoidKeywordToken;
import jparse.symbol.terminal.keyword.VolatileKeywordToken;
import jparse.symbol.terminal.keyword.WhileKeywordToken;

public class KeywordToken extends Terminal {
	public KeywordToken(SymbolEnum kind) {
		super(kind);
	}

	public KeywordToken(SymbolEnum kind, int beg, int end) {
		super(kind, beg, end);
	}

	public static Token capture(int beg, int end, String s) {
		switch (s) {

 		case "abstract":
			return new Token("abstract", beg, end);
		case "assert":
			return new Token("assert", beg, end);
		case "boolean":
			return new Token("boolean", beg, end);
		case "break":
			return new Token("break", beg, end);
		case "byte":
			return new Token("byte", beg, end);
		case "case":
			return new Token("case", beg, end);
		case "catch":
			return new Token("catch", beg, end);
		case "char":
			return new Token("char", beg, end);
		case "class":
			return new Token("class", beg, end);
		case "const":
			return new Token("const", beg, end);
		case "continue":
			return new Token("continue", beg, end);
		case "default":
			return new Token("default", beg, end);
		case "do":
			return new Token("do", beg, end);
		case "double":
			return new Token("double", beg, end);
		case "else":
			return new Token("else", beg, end);
		case "enum":
			return new Token("enum", beg, end);
		case "extends":
			return new Token("extends", beg, end);
		case "final":
			return new Token("final", beg, end);
		case "finally":
			return new Token("finally", beg, end);
		case "float":
			return new Token("float", beg, end);
		case "for":
			return new Token("for", beg, end);
		case "if":
			return new Token("if", beg, end);
		case "goto":
			return new Token("goto", beg, end);
		case "implements":
			return new Token("implements", beg, end);
		case "import":
			return new Token("import", beg, end);
		case "instanceof":
			return new Token("instanceof", beg, end);
		case "int":
			return new Token("int", beg, end);
		case "interface":
			return new Token("interface", beg, end);
		case "long":
			return new Token("long", beg, end);
		case "native":
			return new Token("native", beg, end);
		case "new":
			return new Token("new", beg, end);
		case "package":
			return new Token("package", beg, end);
		case "private":
			return new Token("private", beg, end);
		case "protected":
			return new Token("protected", beg, end);
		case "public":
			return new Token("public", beg, end);
		case "return":
			return new Token("return", beg, end);
		case "short":
			return new Token("short", beg, end);
		case "static":
			return new Token("static", beg, end);
		case "strictfp":
			return new Token("strictfp", beg, end);
		case "super":
			return new Token("super", beg, end);
		case "switch":
			return new Token("switch", beg, end);
		case "synchronized":
			return new Token("synchronized", beg, end);
		case "this":
			return new Token("this", beg, end);
		case "throw":
			return new Token("throw", beg, end);
		case "throws":
			return new Token("throws", beg, end);
		case "transient":
			return new Token("transient", beg, end);
		case "try":
			return new Token("try", beg, end);
		case "void":
			return new Token("void", beg, end);
		case "volatile":
			return new Token("volatile", beg, end);
		case "while":
			return new Token("while", beg, end);
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
