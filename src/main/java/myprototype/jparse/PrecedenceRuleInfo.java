package myprototype.jparse;

import java.util.HashSet;

public class PrecedenceRuleInfo {
	private HashSet<String> highPrioritySymbolsLeft;
	private HashSet<String> highPrioritySymbolsRight;
	private PrecedenceDirection direction;

	public HashSet<String> getHighPrioritySymbolsLeft() {
		return highPrioritySymbolsLeft;
	}

	public HashSet<String> getHighPrioritySymbolsRight() {
		return highPrioritySymbolsRight;
	}

	public PrecedenceDirection getDirection() {
		return direction;
	}

	public PrecedenceRuleInfo(PrecedenceDirection direction, HashSet<String> highPrioritySymbolsLeft,
			HashSet<String> highPrioritySymbolsRight) {
		this.direction = direction;
		this.highPrioritySymbolsLeft = highPrioritySymbolsLeft;
		this.highPrioritySymbolsRight = highPrioritySymbolsRight;
	}
	
	public PrecedenceRuleInfo(PrecedenceDirection direction) {
		this(direction, new HashSet<>(), new HashSet<>());
	}
	
	public PrecedenceRuleInfo() {
		this(PrecedenceDirection.Left);
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

	@Override
	public int hashCode() {
		return this.highPrioritySymbolsLeft.hashCode() + this.highPrioritySymbolsRight.hashCode() + this.direction.hashCode();
	}
}
