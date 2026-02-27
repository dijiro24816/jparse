package jparse;

import java.io.Serializable;

public record Action(ActionKind kind, int argumentValue) implements Serializable {
	public Action(ActionKind kind) {
		this(kind, -1);
	}

	public String toShortString() {
		return "" + kind.name().toLowerCase().charAt(0) + (argumentValue > 0 ? argumentValue : "");
	}
}
