package com.example.smtp;

import java.io.InputStream;
import java.io.OutputStream;

public class MockSocket {

	private boolean isClosed = false;

	public void setSoTimeout(int i) {
		// Do nothing
	}

	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	public MockInetAddress getInetAddress() {
		return new MockInetAddress();
	}

	public void close() throws MockIOException {
		isClosed = true;
	}
	
	public boolean isClosed() {
		return isClosed;
	}

}
