package com.example.resultset;

public class MockType {

	public static final MockType SQL_INTEGER = new MockType();

	public static final MockType SQL_ALL_TYPES = new MockType();

	public int typeCode;

	public Object convertToTypeJDBC(MockSessionInterface session, Object value, MockType sourceType) {
		return value;
	}

	public String getNameString() {
		return "nameString";
	}

	public String getJDBCClassName() {
		return "jdbcClassName";
	}

}
