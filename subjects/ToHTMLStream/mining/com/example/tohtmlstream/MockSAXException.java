package com.example.tohtmlstream;

import java.io.IOException;

public class MockSAXException extends Exception {

	private static final long serialVersionUID = 1L;

	public MockSAXException(IOException e) {
	}

	public MockSAXException(String createMessage, MockIOException ioe) {
	}

	public MockSAXException(MockIOException e) {
	}

}
