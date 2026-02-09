package myprototype.jparse;

public class Action {
	private ActionKind kind;
	private Object argumentValue;

	public ActionKind getKind() {
		return kind;
	}

	public Object getArgumentValue() {
		return argumentValue;
	}

	public Action(ActionKind kind) {
		this(kind, -1);
	}
	
	public Action(ActionKind kind, Object argumentValue) {
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
