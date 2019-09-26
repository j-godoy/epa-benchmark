package com.example.smtpprotocol;

public class RistrettoSSLSocketFactory {

	public static RistrettoSSLSocketFactory getInstance() {
		return new RistrettoSSLSocketFactory();
	}

	public MockSocket createSocket(MockSocket socket, String host, int port, boolean b) {
		return new MockSocket();
	}

}
