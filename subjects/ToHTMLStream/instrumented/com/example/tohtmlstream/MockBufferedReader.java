package com.example.tohtmlstream;


public class MockBufferedReader {

	public MockBufferedReader(MockInputStreamReader in) {
	}

	public String readLine() throws MockIOException {
		return "";
	}

	public void close() throws MockIOException {
	}

}
