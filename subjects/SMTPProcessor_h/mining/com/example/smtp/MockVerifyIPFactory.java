package com.example.smtp;

public class MockVerifyIPFactory {

	public static synchronized VerifyIP getNewVerifyIPInstance(boolean useDummy) {
		return new Dummy();
	}

	private static class Dummy implements VerifyIP {

		public boolean blockIP(String clientIP) {
			return false;
		}

		public void saveBegin(MockSMTPMessage message, boolean useAmavisSMTPDirectory) throws MockIOException {
		}
	}

	public void saveBegin(MockSMTPMessage message, boolean useAmavisSMTPDirectory) throws MockIOException {
	}

}
