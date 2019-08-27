package com.example.sftpconnection;

public class MockSocketFactory {

	public MockSocket createSocket(String host, int port) {
		return new MockSocket();
	}

	public MockInputStream getInputStream(MockSocket socket) {
		return socket.getInputStream();
	}

	public MockOutputStream getOutputStream(MockSocket socket) {
		return socket.getOutputStream();
	}

}
