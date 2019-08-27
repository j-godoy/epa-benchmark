package com.example.sftpconnection;

public class Proxy {

	public void connect(MockSocketFactory socket_factory, String host, int port, int connectTimeout) {
	}

	public MockInputStream getInputStream() {
		return null;
	}

	public MockOutputStream getOutputStream() {
		return null;
	}

	public MockSocket getSocket() {
		return new MockSocket();
	}

}
