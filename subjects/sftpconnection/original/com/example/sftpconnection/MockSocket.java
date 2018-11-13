package com.example.sftpconnection;

public class MockSocket {

	public MockInputStream getInputStream() {
		return new MockInputStream();
	}

	public MockOutputStream getOutputStream() {
		return new MockOutputStream();
	}

	public void setTcpNoDelay(boolean b) {
	}

	public void setSoTimeout(int connectTimeout) {
	}

}
