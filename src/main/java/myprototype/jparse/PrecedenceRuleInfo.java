package myprototype.jparse;

import java.io.Serializable;
import java.util.HashSet;

public class PrecedenceRuleInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private PrecedenceDirection direction;
	private HashSet<String> highPrioritySymbolsLeft;
	private HashSet<String> highPrioritySymbolsRight;

	public PrecedenceRuleInfo() {
		this(PrecedenceDirection.Left);
	}

	public PrecedenceRuleInfo(PrecedenceDirection direction) {
		this(direction, new HashSet<>(), new HashSet<>());
	}

	public PrecedenceRuleInfo(PrecedenceDirection direction, HashSet<String> highPrioritySymbolsLeft,
			HashSet<String> highPrioritySymbolsRight) {
		this.direction = direction;
		this.highPrioritySymbolsLeft = highPrioritySymbolsLeft;
		this.highPrioritySymbolsRight = highPrioritySymbolsRight;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != getClass())
			return false;

		PrecedenceRuleInfo otherPrecedence = (PrecedenceRuleInfo) obj;

		return this.highPrioritySymbolsLeft.equals(otherPrecedence.getHighPrioritySymbolsLeft())
				&& this.highPrioritySymbolsRight.equals(otherPrecedence.getHighPrioritySymbolsRight())
				&& this.getDirection() == this.direction;
	}

	public PrecedenceDirection getDirection() {
		return direction;
	}

	public HashSet<String> getHighPrioritySymbolsLeft() {
		return highPrioritySymbolsLeft;
	}

	public HashSet<String> getHighPrioritySymbolsRight() {
		return highPrioritySymbolsRight;
	}
	
	@Override
	public int hashCode() {
		return this.highPrioritySymbolsLeft.hashCode() + this.highPrioritySymbolsRight.hashCode() + this.direction.hashCode();
	}
	
	public void setDirection(PrecedenceDirection direction) {
		this.direction = direction;
	}

	public void setHighPrioritySymbolsLeft(HashSet<String> highPrioritySymbolsLeft) {
		this.highPrioritySymbolsLeft = highPrioritySymbolsLeft;
	}

	public void setHighPrioritySymbolsRight(HashSet<String> highPrioritySymbolsRight) {
		this.highPrioritySymbolsRight = highPrioritySymbolsRight;
	}
}
