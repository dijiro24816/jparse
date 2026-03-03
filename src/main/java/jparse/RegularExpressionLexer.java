package jparse;

import java.io.IOException;
import java.io.InputStream;

public class RegularExpressionLexer implements Lexer {
	// https://pubs.opengroup.org/onlinepubs/007908799/xbd/re.html
	
	private boolean isInsideBracketExpression = false;
	
	public Scratchpad textBuffer;
	
	public RegularExpressionLexer() {
		this.textBuffer = new Scratchpad();
	}
	
	@Override
	public Token tokenize(InputStream inStrm) throws IOException, InvalidTokenException {
		// SPEC_CHAR
		int ch = textBuffer.getCharacter(inStrm);
		if (!isInsideBracketExpression) {
			
		}
		
		
		
		// COLL_ELEM a-z
		// BACKREF \1
		// DUP_COUNT {1}
		// META_CHAR
		// L_ANCHOR ^
		// ORD_CHAR abc
 		// QOUTED_CHAR \^      \.      \[      \$      \(      \)    \|     \*      \+     \?      \{      \\

		// R_ANCHOR $
		
		
		
		return null;
	}

}
