package myprototype.jparse.symbol.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Function;

public class TextBuffer {
	public StringBuilder stringBuilder = new StringBuilder();
	public int count = 0; // Counting from beginning of InputStream

	class ErasedString {
		public int index;
		public int length;
		public int resizedLength = 0;

		public ErasedString(int index, int length) {
			this.index = index;
			this.length = length;
		}
		
		public void setResizedLength(int resizedLength) {
			this.resizedLength = resizedLength;
		}
		
		public int getResizedLength() {
			return this.resizedLength;
		}
		

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		@Override
		public String toString() {
			return "ErasedString [index=" + index + ", length=" + length + ", resizedLength=" + resizedLength + "]";
		}
	}

	public ArrayList<ErasedString> erasedStrings = new ArrayList<>();

	private int index = 0;

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index; 
	}

	public int countLength(InputStream inStrm, Function<Integer, Boolean> cmp) throws IOException, InvalidTokenException {
		int length = 0;
		int ch;
		while ((ch = getCharacter(inStrm)) >= 0 && cmp.apply(ch))
			length++;

		return length;
	}
	
	public int pullByteCharacter(InputStream inStrm) throws IOException {
		int ch = inStrm.read();
		stringBuilder.append((char)ch);
		count++;
		return ch;
	}
	
	public int pullCharacter(InputStream inStrm) throws IOException {
		int begIndex = length();
		
		int ch = pullByteCharacter(inStrm);
		
		if (ch != '\\')
			return ch;
		
		if (pullByteCharacter(inStrm) != 'u')
			return ch;
		
		for (int i = 0; i < 4; i++)
			pullByteCharacter(inStrm);
		
		ch = (int) Util.parseHexDecimalDigits(copy(begIndex + 2, begIndex + 6));
		replaceAsCharacter(begIndex, begIndex + 6, ch);
		
		return ch;
	}

	public int jump(InputStream inStrm, int index) throws IOException {
		if (index < length()) {
			this.index = index;
			return stringBuilder.charAt(index);
		}
		
		for (this.index = length(); this.index < index; this.index++) {
			int ch = pullCharacter(inStrm);
			if (ch < 0)
				return ch;
			this.count++;
		}
		
		return pullCharacter(inStrm);
	}

	public int getCharacter(InputStream inStrm) throws IOException {
		int ch = jump(inStrm, this.index);
		this.index++;
		return ch;
	}

	public int getPreviousCharacter(InputStream inStrm) throws IOException {
		index--;
		int ch = jump(inStrm, index);
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

//	public int jumpNext(InputStream inStrm, int nth) throws IOException {
//		return jump(inStrm, getIndex() + nth);
//	}

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
	// Don't use this method after extract String after <index>
	public int getByteCount(int index) {
		return count - getRawLength(index, getLength());
	}

	public int getFirstByteCount() {
		return getByteCount(0);
	}
	
	public StringBuilder delete(int begIndex, int endIndex) {
		return stringBuilder.delete(begIndex, endIndex);
	}

	public int getRawLength(int begIndex, int endIndex) {
		return endIndex - begIndex +
				erasedStrings.stream()
						.filter(es -> (es.index > begIndex && es.index < endIndex))
						.mapToInt(es -> {
							return es.length - es.getResizedLength();
						})
						.sum();
	}

	public int getRawLength() {
		return getRawLength(0, getLength());
	}
	
	public int getLength() {
		return stringBuilder.length();
	}
	
	public ErasedString erase(int begIndex, int endIndex) {
//		System.out.println("Before: \"" + stringBuilder.toString() + "\"");
		
		stringBuilder.delete(begIndex, endIndex);
		
		int length = erasedStrings.stream()
				.filter(es -> (es.index > begIndex && es.index < endIndex))
				.mapToInt(es -> { return es.getLength() - es.getResizedLength(); })
				.sum();
		ErasedString erasedString = new ErasedString(begIndex, endIndex - begIndex + length);

		erasedStrings.removeIf(es -> (es.index > begIndex && es.index < endIndex));

		if (begIndex > 0)
			erasedStrings.add(erasedString);
		
		index = begIndex;
		
//		System.out.println(" -> After: \"" + stringBuilder.toString() + "\"");
		return erasedString;
	}

	public ErasedString erase(int length) {
		return erase(0, length);
	}

	public String getHeadString(int length) {
		return stringBuilder.substring(0, length);
	}

	public String copy(int begIndex, int endIndex) {
		return stringBuilder.substring(begIndex, endIndex);
	}

	public String extract(int begIndex, int endIndex) {
		String s = copy(begIndex, endIndex);
		erase(begIndex, endIndex);
		return s;
	}

	// Extract from stringBuilder, but keep stringBuilder's character after the specified length
	public String extract(int length) {
		return extract(0, length);
	}
	
	public void replaceAsCharacter(int begIndex, int endIndex, int ch) {
		erase(begIndex, endIndex).setResizedLength(1);
		stringBuilder.insert(begIndex, (char)ch);
	}

	public int length() {
		return stringBuilder.length();
	}
}