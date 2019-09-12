package com.example.resultset;

public class MockJDBCStatementBase implements MockStatement {

	public MockSessionInterface connection;

	public void close() {
		// do nothing
	}

}
