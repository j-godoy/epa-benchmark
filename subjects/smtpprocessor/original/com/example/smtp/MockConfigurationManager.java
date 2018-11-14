package com.example.smtp;

public class MockConfigurationManager {

	public MockUser getUser(MockEmailAddress address) {
		return new MockUser();
	}

	public int getMaximumMessageSize() {
		return 1;
	}

	public String[] getLocalDomains() {
		return new String[] { "localDomain" };
	}

	public static MockConfigurationManager getInstance() {
		return new MockConfigurationManager();
	}

}
