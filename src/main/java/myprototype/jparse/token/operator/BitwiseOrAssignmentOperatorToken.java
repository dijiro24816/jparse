package myprototype.jparse.token.operator;

import myprototype.jparse.token.OperatorToken;

// '|='
public class BitwiseOrAssignmentOperatorToken extends OperatorToken {
	public BitwiseOrAssignmentOperatorToken(int beg, int end) {
		super(beg, end);
	}
}
