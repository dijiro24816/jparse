package myprototype.jparse;

import java.io.Serializable;

public class Action implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int argumentValue;
	private ActionKind kind;

	public Action() {
		this.argumentValue = 0;
		this.kind = ActionKind.Beginning;
	}
	
	public Action(ActionKind kind) {
		this(kind, -1);
	}

	public Action(ActionKind kind, int argumentValue) {
		this.kind = kind;
		this.argumentValue = argumentValue;
	}

	public int getArgumentValue() {
		return argumentValue;
	}

	public ActionKind getKind() {
		return kind;
	}

	public void setArgumentValue(int argumentValue) {
		this.argumentValue = argumentValue;
	}
	
	public void setKind(ActionKind kind) {
		this.kind = kind;
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
		
		case Beginning:
			stringBuilder.append('b');
		}
		
		
		return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + " " + this.kind + "(" + this.argumentValue + ")]";
	}
}
