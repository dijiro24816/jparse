package myprototype.jparse.token;

import java.io.IOException;
import java.io.InputStream;

public class TextBuffer {
	public StringBuilder stringBuilder = new StringBuilder();
	public int count = 0; // Counting from beginning of InputStream
	private int index = 0;
	
	public int getIndex() {
		return index;
	}
	
	public int setIndex(InputStream inStrm, int index) throws IOException {
		if (index < length())
			return index;
		
		for (this.index = length(); this.index <= index; this.index++) {
			int ch;
			if ((ch = inStrm.read()) < 0)
				return this.index - index;
			
			stringBuilder.append((char)ch);
		}
		this.index = index;
		
		return index;
	}
	
	// return character or minus index
	public int peek(InputStream inStrm, int nth) throws IOException {
		int index = this.index; // save
		
		int idx = setIndex(inStrm, getIndex() + nth);
		
		this.index = index; // backup
		
		return idx < 0 ? idx : stringBuilder.charAt(getIndex() + nth);
	}
	
	public int peek(InputStream inStrm) throws IOException {
		return peek(inStrm, 0);
	}
	
//	public int peek(InputStream inStrm) throws IOException {
//		// If stringBuilder stocks next character, return stringBuilder's character
//		if (index < length())
//			return stringBuilder.charAt(index);
//
//		int ch = inStrm.read();
//		count++;
//
//		// (new StringBuilder()).append((int)v);
//		// (new StringBuilder()).append((char)v);
//		// behave differ
//		stringBuilder.append((char) ch);
//		return ch;
//	}

	// Peek character after the specified count
//	public int peek(InputStream inStrm, int nth) throws IOException {
//		int index = this.index;
//		for (int i = 0; i < nth; i++)
//			read(inStrm);
//
//		int ch = peek(inStrm);
//		this.index = index;
//		return ch;
//	}

	public int read(InputStream inStrm) throws IOException {
		int ch = peek(inStrm);
		index++;
		return ch;
	}

	public int read(InputStream inStrm, int nth) throws IOException {
		int ch = peek(inStrm, nth);
		this.index = nth + 1;
		return ch;
	}

	// Read character without putting on stringBuilder
	public int skip(InputStream inStrm) throws IOException {
		int ch = inStrm.read();
		count++;
		return ch;
	}

	public void unskip(InputStream inStrm, char ch) throws IOException {
		stringBuilder.append(ch);
	}

	// Tokenizer should use this method, before and after call pop() to get token's begginning index and end index. 
	public int getFirstByteCount() {
		return count - length();
	}

	public void erase(int length) {
		stringBuilder.delete(0, length);
		index = 0; // reset index
	}

	public String getHeadString(int length) {
		return stringBuilder.substring(0, length);
	}
	
	public String substring(int start, int end) {
		return stringBuilder.substring(start, end);
	}

	// Extract from stringBuilder, but keep stringBuilder's character after the specified length
	public String extract(int length) {
		String string = getHeadString(length);
		erase(length);
		return string;
	}

	public int length() {
		return stringBuilder.length();
	}
}