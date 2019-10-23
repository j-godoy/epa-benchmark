package com.example.resultset;

import java.util.Vector;

public class MockRowSetNavigator {

	private final String[] columnLabels;
	private final Vector<int[]> rows;
	private int currentPos = -1;

	public MockRowSetNavigator(String[] columnLabels, int[][] values) {
		this.columnLabels = columnLabels;
		rows = new Vector<int[]>();
		for (int[] value : values) {
			rows.add(value);
		}
	}

	public boolean next() {
		if (currentPos < rows.size()) {
			currentPos++;
		}
		if (currentPos < rows.size()) {
			return true;
		} else {
			return false;
		}
	}

	public void close() {
		// Do nothing
	}

	public boolean isBeforeFirst() {
		return this.currentPos == -1;
	}

	public boolean isAfterLast() {
		return this.currentPos == this.rows.size();
	}

	public boolean isFirst() {
		return this.currentPos == 0;
	}

	public boolean isLast() {
		return this.currentPos == this.rows.size() - 1;
	}

	public boolean relative(int rows) {
		int position = currentPos + rows;

		if (position < 0) {
			beforeFirst();

			return false;
		}

		return absolute(position);
	}

	public boolean previous() {
		if (currentPos == -1) {
			return false;
		}
		currentPos--;
		return true;
	}

	public boolean beforeFirst() {
		this.currentPos = -1;
		return true;
	}

	public void afterLast() {
		this.currentPos = this.rows.size();
	}

	public boolean first() {
		if (this.rows.isEmpty()) {
			return false;
		} else {
			this.currentPos = 0;
			return true;
		}
	}

	public boolean last() {
		if (this.rows.isEmpty()) {
			return false;
		} else {
			this.currentPos = this.rows.size() - 1;
			return true;
		}
	}

	public int getRowNumber() {
		if (this.currentPos == -1) {
			return -1;
		} else if (this.currentPos == this.rows.size()) {
			return 0;
		} else {
			return this.currentPos;
		}
	}

	public boolean absolute(int position) {
		if (position < 0) {
			position += this.rows.size();
		}

		if (position < 0) {
			beforeFirst();

			return false;
		}

		if (position >= this.rows.size()) {
			afterLast();

			return false;
		}

		if (this.rows.size() == 0) {
			return false;
		}

		if (position < this.currentPos) {
			beforeFirst();
		}

		// go to the tagget row;
		while (position > currentPos) {
			next();
		}

		return true;
	}

	public boolean isEmpty() {
		return this.rows.isEmpty();
	}

	public Object[] getCurrent() {
		int[] rowData = rows.get(this.currentPos);
		int rowId = this.currentPos;
		Object[] rv = createNewArray(rowData, rowId);
		return rv;
	}

	private static Object[] createNewArray(int[] rowData, int rowId) {
		Object[] rv = new Object[rowData.length + 1];
		for (int i = 0; i < rowData.length; i++) {
			rv[i] = rowData[i];
		}
		int rowIdIndex = rowData.length;
		rv[rowIdIndex] = rowId;
		return rv;
	}

	void setCurrent(int[] newRow) {
		this.rows.set(this.currentPos, newRow);
	}

	void deleteRow() {
		this.rows.remove(this.currentPos);
	}

	void insertRow(int[] newRow) {
		this.rows.add(newRow);

	}

}
