package jparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Function;

public class Scratchpad {
	public record ErasedString(int index, int length, int resizedLength) {
		public ErasedString(int index, int length) {
			this(index, length, 0);
		}
	}

	protected StringBuilder buffer;
	private int count; // Counting from beginning of InputStream
	private ArrayList<ErasedString> erasedStrings;
	private int index;

	public Scratchpad() {
		this.buffer = new StringBuilder();
		this.count = 0;
		this.erasedStrings = new ArrayList<>();
		this.index = 0;
	}

	public String copy(int begIndex, int endIndex) {
		return buffer.substring(begIndex, endIndex);
	}

	public int countLength(InputStream inStrm, Function<Integer, Boolean> cmp)
			throws IOException, InvalidTokenException {
		int length = 0;
		int ch;
		while ((ch = getCharacter(inStrm)) >= 0 && cmp.apply(ch))
			length++;

		return length;
	}

	public ErasedString erase(int begIndex, int endIndex) {
		return erase(begIndex, endIndex);
	}

	public ErasedString erase(int begIndex, int endIndex, int resizedLength) {
//		System.out.println("Before: \"" + stringBuilder.toString() + "\"");

		buffer.delete(begIndex, endIndex);

		int length = erasedStrings.stream().filter(es -> (es.index > begIndex && es.index < endIndex)).mapToInt(es -> {
			return es.length - es.resizedLength;
		}).sum();
		ErasedString erasedString = new ErasedString(begIndex, endIndex - begIndex + length, resizedLength);

		erasedStrings.removeIf(es -> (es.index > begIndex && es.index < endIndex));

		if (begIndex > 0)
			erasedStrings.add(erasedString);

		index = begIndex;

//		System.out.println(" -> After: \"" + stringBuilder.toString() + "\"");
		return erasedString;
	}

	// Extract from stringBuilder, but keep stringBuilder's character after the
	// specified length
	public String extract(int begIndex, int endIndex) {
		String s = copy(begIndex, endIndex);
		erase(begIndex, endIndex);
		return s;
	}

	// Tokenizer should use this method, before and after call pop() to get token's
	// begginning index and end index.
	// ERROR: if some string extracted before call this method, calculations go
	// awry.
	// Don't use this method after extract String after <index>
	public int getByteCount(int index) {
		return count - getRawLength(index, getLength());
	}

	public int getCharacter(InputStream inStrm) throws IOException {
		int ch = jump(inStrm, this.index);
		this.index++;
		return ch;
	}

	public int getFirstByteCount() {
		return getByteCount(0);
	}

	public int getIndex() {
		return index;
	}

	public int getLength() {
		return buffer.length();
	}

	public int getPreviousCharacter(InputStream inStrm) throws IOException {
		index--;
		int ch = jump(inStrm, index);
		return ch;
	}

	public int getRawLength(int begIndex, int endIndex) {
		return endIndex - begIndex
				+ erasedStrings.stream().filter(es -> (es.index > begIndex && es.index < endIndex)).mapToInt(es -> {
					return es.length - es.resizedLength;
				}).sum();
	}

	public int jump(InputStream inStrm, int index) throws IOException {
		if (index < length()) {
			this.index = index;
			return buffer.charAt(index);
		}

		for (this.index = length(); this.index < index; this.index++) {
			int ch = pullByteCharacter(inStrm);
			if (ch < 0)
				return ch;
			this.count++;
		}

		return pullByteCharacter(inStrm);
	}

	public int length() {
		return buffer.length();
	}

	public int peek(InputStream inStrm) throws IOException {
		return peek(inStrm, 0);
	}

	// return character or minus index
	public int peek(InputStream inStrm, int nth) throws IOException {
		int index = this.index; // save

		int ch = jump(inStrm, getIndex() + nth);

		this.index = index; // backup

		return ch < 0 ? ch : buffer.charAt(getIndex() + nth);
	}

	protected int pullByteCharacter(InputStream inStrm) throws IOException {
		int ch = inStrm.read();
		buffer.append((char) ch);
		count++;
		return ch;
	}

	// Read character without putting on stringBuilder
	public int skip(InputStream inStrm) throws IOException {
		int ch = inStrm.read();
		count++;
		return ch;
	}

	public void unskip(InputStream inStrm, char ch) throws IOException {
		buffer.append(ch);
	}
}
