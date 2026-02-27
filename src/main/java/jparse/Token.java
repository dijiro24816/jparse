package jparse;

import java.io.Serializable;

public record Token(String label, int beg, int end, Object value) implements Serializable {
	public Token(String label, int beg, int end) {
		this(label, beg, end, null);
	}
}
