package myprototype.jparse.token;

import myprototype.jparse.token.keyword.AbstractKeywordToken;
import myprototype.jparse.token.keyword.AssertKeywordToken;
import myprototype.jparse.token.keyword.BooleanKeywordToken;
import myprototype.jparse.token.keyword.BreakKeywordToken;
import myprototype.jparse.token.keyword.ByteKeywordToken;
import myprototype.jparse.token.keyword.CaseKeywordToken;
import myprototype.jparse.token.keyword.CatchKeywordToken;
import myprototype.jparse.token.keyword.CharKeywordToken;
import myprototype.jparse.token.keyword.ClassKeywordToken;
import myprototype.jparse.token.keyword.ConstKeywordToken;
import myprototype.jparse.token.keyword.ContinueKeywordToken;
import myprototype.jparse.token.keyword.DefaultKeywordToken;
import myprototype.jparse.token.keyword.DoKeywordToken;
import myprototype.jparse.token.keyword.DoubleKeywordToken;
import myprototype.jparse.token.keyword.ElseKeywordToken;
import myprototype.jparse.token.keyword.EnumKeywordToken;
import myprototype.jparse.token.keyword.ExtendsKeywordToken;
import myprototype.jparse.token.keyword.FinalKeywordToken;
import myprototype.jparse.token.keyword.FinallyKeywordToken;
import myprototype.jparse.token.keyword.FloatKeywordToken;
import myprototype.jparse.token.keyword.ForKeywordToken;
import myprototype.jparse.token.keyword.GotoKeywordToken;
import myprototype.jparse.token.keyword.IfKeywordToken;
import myprototype.jparse.token.keyword.ImplementsKeywordToken;
import myprototype.jparse.token.keyword.ImportKeywordToken;
import myprototype.jparse.token.keyword.InstanceofKeywordToken;
import myprototype.jparse.token.keyword.IntKeywordToken;
import myprototype.jparse.token.keyword.InterfaceKeywordToken;
import myprototype.jparse.token.keyword.LongKeywordToken;
import myprototype.jparse.token.keyword.NativeKeywordToken;
import myprototype.jparse.token.keyword.NewKeywordToken;
import myprototype.jparse.token.keyword.PackageKeywordToken;
import myprototype.jparse.token.keyword.PrivateKeywordToken;
import myprototype.jparse.token.keyword.ProtectedKeywordToken;
import myprototype.jparse.token.keyword.PublicKeywordToken;
import myprototype.jparse.token.keyword.ReturnKeywordToken;
import myprototype.jparse.token.keyword.ShortKeywordToken;
import myprototype.jparse.token.keyword.StaticKeywordToken;
import myprototype.jparse.token.keyword.StrictfpKeywordToken;
import myprototype.jparse.token.keyword.SuperKeywordToken;
import myprototype.jparse.token.keyword.SwitchKeywordToken;
import myprototype.jparse.token.keyword.SynchronizedKeywordToken;
import myprototype.jparse.token.keyword.ThisKeywordToken;
import myprototype.jparse.token.keyword.ThrowKeywordToken;
import myprototype.jparse.token.keyword.ThrowsKeywordToken;
import myprototype.jparse.token.keyword.TransicentKeywordToken;
import myprototype.jparse.token.keyword.TryKeywordToken;
import myprototype.jparse.token.keyword.VoidKeywordToken;
import myprototype.jparse.token.keyword.VolatileKeywordToken;
import myprototype.jparse.token.keyword.WhileKeywordToken;

public class KeywordToken extends Token {
	public KeywordToken() {
	}

	public KeywordToken(int beg, int end) {
		super(beg, end);
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
		case "transicent":
			return new TransicentKeywordToken(beg, end);
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
		return "KeywordToken [beg=" + beg + " end=" + end + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof KeywordToken tok) {
			return super.equals(obj);
		}
		return false;
	}
}
