package myprototype.jparse.token;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class TokenizerTest {
	
	Token tokenize(String src) {
		try {
			Tokenizer tokenizer = new Tokenizer();
			InputStream inStrm = new ByteArrayInputStream(src.getBytes());
			return tokenizer.tokenize(inStrm);
		} catch (IOException | InvalidTokenException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Test
	void test() {
//		fail("まだ実装されていません");
	}
	
	@Test
	void tokenizeIdentifer() {
		String src = """
				hello
				""";
		assertEquals(tokenize(src), new IdentifierToken(0, 5, "hello"));
	}
	
	@Test
	void tokenizeBooleanLiteral() {
		
	}

}
