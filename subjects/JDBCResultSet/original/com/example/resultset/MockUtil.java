package com.example.resultset;


public class MockUtil {

	public static MockSQLException notUpdatableColumn() {
		return new MockSQLException();
	}

	public static MockSQLException sqlException(String x24501) {
		return new MockSQLException();
	}

	public static MockSQLException sqlException(String jdbcColumnNotFound, String columnLabel) {
		return new MockSQLException();
	}

	public static MockSQLException outOfRangeArgument() {
		return new MockSQLException();
	}

	public static MockSQLException nullArgument() {
		return new MockSQLException();
	}

	public static MockSQLException sqlExceptionSQL(String x24513) {
		return new MockSQLException();
	}

	public static MockSQLException notSupported() {
		return new MockSQLException();
	}

	public static Error throwError(Error error) {
		return new Error();
	}

}
