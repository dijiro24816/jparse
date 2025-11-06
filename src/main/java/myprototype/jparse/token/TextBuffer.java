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
	
	public int jump(InputStream inStrm, int index) throws IOException {
		if (index < length()) {
			this.index = index;
			return stringBuilder.charAt(index);
		}
		
		for (this.index = length(); this.index <= index; this.index++, this.count++) {
			int ch;
			if ((ch = inStrm.read()) < 0)
				return -1;
			
			stringBuilder.append((char)ch);
		}
		this.index = index;
		
		return stringBuilder.charAt(index);
	}
	
	public int getCharacter(InputStream inStrm) throws IOException {
		int ch = jump(inStrm, index);
		index++;
		return ch;
	}
	
	public int goBack(InputStream inStrm) throws IOException {
		int ch = jump(inStrm, index);
		index--;
		return ch;
	}
	
	// return character or minus index
	public int peek(InputStream inStrm, int nth) throws IOException {
		int index = this.index; // save
		
		int ch = jump(inStrm, getIndex() + nth);
		
		this.index = index; // backup
		
		return ch < 0 ? ch : stringBuilder.charAt(getIndex() + nth);
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

	public int jumpNext(InputStream inStrm, int nth) throws IOException {
		return jump(inStrm, getIndex() + nth);
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
	// ERROR: if some string extracted before call this method, calculations go awry. 
	public int countByteFromEnd(int index) {
		return count - (length() - index);
	}
	
	public int countByteFromEnd() {
		return countByteFromEnd(0);
	}
	
	public StringBuilder delete(int start, int end) {
		return stringBuilder.delete(start, end);
	}
	
	public void erase(int start, int end) {
		stringBuilder.delete(start, end);
		this.index = start; 
	}

	public void erase(int length) {
		erase(0, length);
	}

	public String getHeadString(int length) {
		return stringBuilder.substring(0, length);
	}
	
	public String copy(int start, int end) {
		return stringBuilder.substring(start, end);
	}
	
	public String extract(int start, int end) {
		String s = copy(start, end);
		erase(start, end);
		return s;
	}

	// Extract from stringBuilder, but keep stringBuilder's character after the specified length
	public String extract(int length) {
		return extract(0, length);
	}

	public int length() {
		return stringBuilder.length();
	}
}