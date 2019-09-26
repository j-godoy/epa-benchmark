package com.example.smtp;

public class MockLogFactory {

	public static MockLog getLog(String name) {
		return new MockLog();
	}

}
