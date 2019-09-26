package com.example.smtp;

public class MockSocket {

	private boolean isClosed = false;

	public void setSoTimeout(int i) {
		// Do nothing
	}

	public MockOutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	public MockInputStream getInputStream() {
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
