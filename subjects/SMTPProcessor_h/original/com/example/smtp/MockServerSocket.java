package com.example.smtp;

public class MockServerSocket {

	public void setSoTimeout(int i) throws MockSocketException {
		// TODO Auto-generated method stub
	}

	public MockSocket accept() throws MockIOException {
		return new MockSocket();
	}

	public int getLocalPort() {
		return 0;
	}

}
