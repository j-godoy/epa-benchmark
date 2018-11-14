package com.example.smtp;

public class MockUser {

	public MockEmailAddress[] getDeliveryAddresses() throws MockInvalidAddressException {
		return new MockEmailAddress[] { new MockEmailAddress() };
	}

}
