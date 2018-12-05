package com.example.smtpprotocol;

public class MockSocket {

	private boolean isClosed = false;

	public MockSocket(String host, int port) {
		// TODO Auto-generated constructor stub
	}

	public MockSocket() {
	}

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

	public void close() throws MockIOException {
		isClosed = true;
	}
	
	public boolean isClosed() {
		return isClosed;
	}

	public void startHandshake() {
	}

}
