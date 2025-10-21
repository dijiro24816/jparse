package myprototype.jparse;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Source {
	public BufferedInputStream inputStream;

	public Source(InputStream inStrm) {
		this.inputStream = new BufferedInputStream(inStrm);
	}

	//	
	//	public String filePath;
	//	
	//	public int lineNumber;
	//	public int columnNumber;
	//	
	//	public Source(InputStream strm, String filePath) {
	//		super();
	//		this.strm = strm;
	//		this.filePath = filePath;
	//	}
	//	
	//	public Source(InputStream strm) {
	//		this(strm, null);
	//	}
	//
	//	public boolean isFile() {
	//		return filePath == null;
	//	}
	//	
	//	public Token tokenize() {
	//		return Token.IDENTIFIER;
	//	}
}
