package myprototype.jparse.symbol.terminal;

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

	public static KeywordToken capture(int beg, int end, String s) {
		switch (s) {

		case "abstract":
			return new AbstractKeywordToken(beg, end);
		case "assert":
			return new AssertKeywordToken(beg, end);
		case "boolean":
			return new BooleanKeywordToken(beg, end);
		case "break":
			return new BreakKeywordToken(beg, end);
		case "byte":
			return new ByteKeywordToken(beg, end);
		case "case":
			return new CaseKeywordToken(beg, end);
		case "catch":
			return new CatchKeywordToken(beg, end);
		case "char":
			return new CharKeywordToken(beg, end);
		case "class":
			return new ClassKeywordToken(beg, end);
		case "const":
			return new ConstKeywordToken(beg, end);
		case "continue":
			return new ContinueKeywordToken(beg, end);
		case "default":
			return new DefaultKeywordToken(beg, end);
		case "do":
			return new DoKeywordToken(beg, end);
		case "double":
			return new DoubleKeywordToken(beg, end);
		case "else":
			return new ElseKeywordToken(beg, end);
		case "enum":
			return new EnumKeywordToken(beg, end);
		case "extends":
			return new ExtendsKeywordToken(beg, end);
		case "final":
			return new FinalKeywordToken(beg, end);
		case "finally":
			return new FinallyKeywordToken(beg, end);
		case "float":
			return new FloatKeywordToken(beg, end);
		case "for":
			return new ForKeywordToken(beg, end);
		case "if":
			return new IfKeywordToken(beg, end);
		case "goto":
			return new GotoKeywordToken(beg, end);
		case "implements":
			return new ImplementsKeywordToken(beg, end);
		case "import":
			return new ImportKeywordToken(beg, end);
		case "instanceof":
			return new InstanceofKeywordToken(beg, end);
		case "int":
			return new IntKeywordToken(beg, end);
		case "interface":
			return new InterfaceKeywordToken(beg, end);
		case "long":
			return new LongKeywordToken(beg, end);
		case "native":
			return new NativeKeywordToken(beg, end);
		case "new":
			return new NewKeywordToken(beg, end);
		case "package":
			return new PackageKeywordToken(beg, end);
		case "private":
			return new PrivateKeywordToken(beg, end);
		case "protected":
			return new ProtectedKeywordToken(beg, end);
		case "public":
			return new PublicKeywordToken(beg, end);
		case "return":
			return new ReturnKeywordToken(beg, end);
		case "short":
			return new ShortKeywordToken(beg, end);
		case "static":
			return new StaticKeywordToken(beg, end);
		case "strictfp":
			return new StrictfpKeywordToken(beg, end);
		case "super":
			return new SuperKeywordToken(beg, end);
		case "switch":
			return new SwitchKeywordToken(beg, end);
		case "synchronized":
			return new SynchronizedKeywordToken(beg, end);
		case "this":
			return new ThisKeywordToken(beg, end);
		case "throw":
			return new ThrowKeywordToken(beg, end);
		case "throws":
			return new ThrowsKeywordToken(beg, end);
		case "transient":
			return new TransientKeywordToken(beg, end);
		case "try":
			return new TryKeywordToken(beg, end);
		case "void":
			return new VoidKeywordToken(beg, end);
		case "volatile":
			return new VolatileKeywordToken(beg, end);
		case "while":
			return new WhileKeywordToken(beg, end);
		}
		return null;
	}

	@Override
	public String toString() {
		return "KeywordToken [beg=" + getBeg() + " end=" + getEnd() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof KeywordToken tok) {
			return super.equals(obj);
		}
		return false;
	}
}
