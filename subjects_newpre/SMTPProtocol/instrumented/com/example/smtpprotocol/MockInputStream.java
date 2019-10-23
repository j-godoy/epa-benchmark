package com.example.smtpprotocol;

public class MockInputStream {

	public int read(byte[] buffer) throws MockIOException {
		return -1;
	}

	public int read() throws MockIOException {
		return 0;
	}

}
