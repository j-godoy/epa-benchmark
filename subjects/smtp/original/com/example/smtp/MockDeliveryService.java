package com.example.smtp;

public class MockDeliveryService {

	public static MockDeliveryService getDeliveryService() {
		return new MockDeliveryService();
	}

	public boolean acceptAddress(MockEmailAddress address, String clientIp, MockEmailAddress fromAddress) {
		return true;
	}

}
