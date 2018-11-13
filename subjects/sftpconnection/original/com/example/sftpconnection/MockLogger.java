package com.example.sftpconnection;

public class MockLogger {

	public static final int FATAL = 1;
	public static final int INFO = 0;

	public boolean isEnabled(int fatal) {
		return true;
	}

	public void log(int fatal2, String message) {
	}

}
