package jparse.java;

import java.io.IOException;
import java.io.InputStream;

import jparse.Scratchpad;
import jparse.Util;

public class JavaScratchpad extends Scratchpad {
	public int pullCharacter(InputStream inStrm) throws IOException {
		int begIndex = length();
		
		int ch = getCharacter(inStrm);
		
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

	public void replaceAsCharacter(int begIndex, int endIndex, int ch) {
		erase(begIndex, endIndex, 1);
		buffer.insert(begIndex, (char)ch);
	}
}
