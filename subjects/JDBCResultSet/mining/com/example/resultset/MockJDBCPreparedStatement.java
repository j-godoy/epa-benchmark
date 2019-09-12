package com.example.resultset;

public class MockJDBCPreparedStatement {

	public Boolean[] parameterSet;
	public boolean[] parameterStream;
	public Object[] parameterValues;
	public MockResult resultOut;
	public MockType[] parameterTypes;
	private MockSQLWarning rootWarning;

	public MockJDBCPreparedStatement(MockSessionInterface connection, MockResult result) {
		this.resultOut = result;
		int columnCount = this.resultOut.metaData.getColumnCount();
		this.parameterTypes = new MockType[columnCount + 1];
		for (int i = 0; i < columnCount; i++) {
			this.parameterTypes[i] = this.resultOut.metaData.columnTypes[i];
		}
		this.parameterValues = new Object[columnCount + 1]; // extra field for
															// rowID
		int rowIdIndex = columnCount;
		this.parameterTypes[rowIdIndex] = MockType.SQL_INTEGER;

		this.parameterStream = new boolean[columnCount];
		this.parameterSet = new Boolean[columnCount];
		fillArray(this.parameterValues, null);
		fillArray(this.parameterSet, false);
	}

	public void setIntParameter(int i, int value) throws MockSQLException {
		checkSetParameterIndex(i);

		Object o = Integer.valueOf(value);

		parameterValues[i - 1] = o;
		parameterSet[i - 1] = Boolean.TRUE;

	}

	private void checkSetParameterIndex(int i) throws MockSQLException {
		if (i < 1 || i > parameterValues.length) {
			throw new MockSQLException();
		}
	}

	public void clearParameters() {
		fillArray(parameterValues, null);
		fillArray(parameterSet, Boolean.FALSE);
	}

	/**
	 * Fills the array with a value.
	 */
	private static void fillArray(Object[] array, Object value) {

		int to = array.length;

		while (--to >= 0) {
			array[to] = value;
		}
	}

	public void fetchResult() {

		switch (this.resultOut.getActionType()) {
		case MockResultConstants.UPDATE_CURSOR: {
			updateRow();
			break;
		}
		case MockResultConstants.DELETE_CURSOR: {
			deleteRow();
			break;
		}
		case MockResultConstants.INSERT_CURSOR: {
			insertRow();
			break;
		}
		default: {
			throw new IllegalStateException();
		}
		}

	}

	private void insertRow() {
		MockRowSetNavigator navigator = this.resultOut.getNavigator();
		final int columnCount = this.resultOut.metaData.getColumnCount();

		int[] newRow = new int[columnCount + 1];

		for (int i = 0; i < columnCount; i++) {
			if (this.parameterSet[i] != null && this.parameterSet[i].equals(Boolean.TRUE)) {
				newRow[i] = ((Integer) this.parameterValues[i]).intValue();
			} else {
				newRow[i] = (int) 0;
			}
		}
		final int rowId = columnCount;
		newRow[rowId] = (int) 0;
		navigator.insertRow(newRow);

	}

	private void deleteRow() {
		MockRowSetNavigator navigator = this.resultOut.getNavigator();
		navigator.deleteRow();
	}

	private void updateRow() {
		MockRowSetNavigator navigator = this.resultOut.getNavigator();
		Object[] currentRow = navigator.getCurrent();
		int[] newRow = new int[currentRow.length];

		final int columnCount = this.resultOut.metaData.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			if (this.parameterSet[i] != null && this.parameterSet[i].equals(Boolean.TRUE)) {
				newRow[i] = ((Integer) this.parameterValues[i]).intValue();
			} else {
				newRow[i] = ((Integer) currentRow[i]).intValue();
			}
		}
		final int rowId = columnCount;
		newRow[rowId] = ((Integer) currentRow[rowId]).intValue();
		navigator.setCurrent(newRow);
	}

	public MockSQLWarning getWarnings() {
		return rootWarning;
	}

	public void clearWarnings() {
		rootWarning = null;

	}

}
