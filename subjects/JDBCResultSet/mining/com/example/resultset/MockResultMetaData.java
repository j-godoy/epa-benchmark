package com.example.resultset;


public class MockResultMetaData {

	public int[] colIndexes;
	public MockColumnBase[] columns;
	public MockType[] columnTypes;
	public String[] columnLabels;

	private final int columnCount;

	public int getColumnCount() {
		return columnCount;
	}
	
	public MockResultMetaData(String[] columnLabels, int[] baseColumnIndexes, int colCount, int extColCount) {
		this.columnLabels = new String[colCount];
		this.columns = new MockColumnBase[colCount];
		for (int i = 0; i < colCount; i++) {
			this.columns[i] = new MockColumnBase("SCHEMA", "TABLE", columnLabels[i], true);
		}
		this.columnCount = colCount;
		this.columnTypes = new MockType[extColCount];
		for (int i = 0; i < colCount; i++) {
			this.columnTypes[i] = MockType.SQL_INTEGER;
		}
		for (int i = colCount; i < extColCount; i++) {
			this.columnTypes[i] = MockType.SQL_INTEGER;
		}
		this.colIndexes = baseColumnIndexes;
	}


}
