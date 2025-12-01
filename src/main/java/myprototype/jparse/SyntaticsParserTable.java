package myprototype.jparse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyntaticsParserTable {
	private List<int[]> data;
	
	private int columnLength;
	
	public SyntaticsParserTable(int columnLength) {
		this.columnLength = columnLength;
		this.data = new ArrayList<int[]>();
	}
	
	private List<int[]> getData() {
		return data;
	}

	public List<int[]> getSyntaticsParserTable() {
		return Collections.unmodifiableList(this.data);
	}
	
	public int getColumnLength() {
		return this.columnLength;
	}
	
	public int getRowLength() {
		return data.size();
	}

	// Insert new row and return the index number as the state number
	public int getNewState() {
		int newState = getRowLength();
		
		data.add(new int[getColumnLength()]);
		
		return newState;
	}
	
	public void put(int row, int column, int value) {
		getData().get(row)[column] = value;
	}
}
