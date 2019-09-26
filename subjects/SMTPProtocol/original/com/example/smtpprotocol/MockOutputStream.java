package com.example.smtpprotocol;

import java.util.LinkedList;
import java.util.Queue;

public class MockOutputStream {

	private final Queue<String> lines = new LinkedList<String>();

	public void flush() throws MockIOException {
	}

	public void write(String command) {
		if (command == null)
			throw new NullPointerException();
		this.lines.add(command);
	}
	
	public String readLine() throws MockIOException {
		if (lines.isEmpty()) {
			throw new MockIOException();
		}
		return lines.poll();
	}

	public void write(byte[] stopword) {
	}

}
