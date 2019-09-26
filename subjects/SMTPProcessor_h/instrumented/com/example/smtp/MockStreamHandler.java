package com.example.smtp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Each smtp/pop3 session hands control of its streams and socket to an instance
 * of this class.
 *
 * @author Andreas Kyrmegalos
 */
public class MockStreamHandler {

	private MockOutputStream outputStream;
	private MockBufferedInputStream inputStream;
	private MockBufferedReader inputReader;

	private final Queue<String> lines = new LinkedList<String>();

	private MockOutputStream activeOutputStream;
	private MockInputStream activeInputStream;

	public MockStreamHandler() {
	}

	public void setStreams(MockSocket socket) throws MockIOException {
		if (outputStream == null) {
			outputStream = socket.getOutputStream();
			activeOutputStream = new MockBufferedOutputStream(outputStream, 4096);
		}
		if (inputStream == null) {
			inputStream = new MockBufferedInputStream(socket.getInputStream());
			inputReader = new MockBufferedReader(new MockInputStreamReader(inputStream, US_ASCII));
			activeInputStream = inputStream;
		}
	}

	public String readLine() throws MockIOException {
		if (lines.isEmpty()) {
			throw new MockIOException();
		}
		return lines.poll();
	}

	public void write(byte[] line) throws MockIOException {
		if (line == null)
			throw new NullPointerException();
		this.lines.add(new String(line));
	}

	private static final String US_ASCII = "US-ASCII";

	public void print(String message) {
	}
}
