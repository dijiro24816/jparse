package myprototype.jparse;

public class Action {
	private ActionKind kind;
	private int argumentValue;

	public ActionKind getKind() {
		return kind;
	}

	public int getArgumentValue() {
		return argumentValue;
	}

	public Action(ActionKind kind, int argumentValue) {
		this.kind = kind;
		this.argumentValue = argumentValue;
	}

	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + " " + this.kind + "(" + this.argumentValue + ")]";
	}
}
