package com.example.smtpprotocol;

public class MockRistrettoConfig {

	public static MockRistrettoConfig getInstance() {
		return new MockRistrettoConfig();
	}

	public int getTimeout() {
		return 10;
	}

}