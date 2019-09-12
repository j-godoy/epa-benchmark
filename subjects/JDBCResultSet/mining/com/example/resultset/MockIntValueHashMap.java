package com.example.resultset;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class MockIntValueHashMap {

	public int get(String columnLabel, int defaultValue) {
		if (columnLabel == null) {
			throw new NoSuchElementException();
		}
		if (this.columnIndexForLabel.containsKey(columnLabel)) {
			return this.columnIndexForLabel.get(columnLabel);
		}
		return defaultValue;
	}

	private final HashMap<String, Integer> columnIndexForLabel = new HashMap<String, Integer>();

	public void put(String columnLabel, int columnIndex) {
		this.columnIndexForLabel.put(columnLabel, columnIndex);
	}

}