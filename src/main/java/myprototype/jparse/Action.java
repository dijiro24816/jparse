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
	
	public String toShortString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		switch (kind) {
		case Shift:
			stringBuilder.append('s');
			stringBuilder.append(argumentValue);
			break;
		
		case Reduce:
			stringBuilder.append('r');
			stringBuilder.append(argumentValue);
			break;
			
		case Accept:
			stringBuilder.append('a');
			break;
			
		case Goto:
			stringBuilder.append('g');
			stringBuilder.append(argumentValue);
			break;
		}
		
		
		return stringBuilder.toString();
	}
}
