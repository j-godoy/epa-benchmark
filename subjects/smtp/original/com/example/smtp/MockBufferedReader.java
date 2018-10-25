package com.example.smtp;

import java.util.LinkedList;
import java.util.Queue;

public class MockBufferedReader {

	public MockBufferedReader(MockInputStreamReader mockInputStreamReader) {
		// TODO Auto-generated constructor stub
	}

	private final Queue<String> lines = new LinkedList<String>();

	public String readLine() throws MockIOException {
		if (lines.isEmpty()) {
			throw new MockIOException();
		}
		return lines.poll();
	}

	public void writeLine(String lineStr) {
		if (lineStr == null)
			throw new NullPointerException();
		this.lines.add(lineStr);
	}

}
