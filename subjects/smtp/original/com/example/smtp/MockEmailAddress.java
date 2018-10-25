package com.example.smtp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockEmailAddress {

	private String emailAddress;

	public MockEmailAddress(String email) throws MockInvalidAddressException {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new MockInvalidAddressException();
		}
		this.emailAddress = email;
	}

	public MockEmailAddress() throws MockInvalidAddressException {
		this("joe@doe.com");
	}

	public String getAddress() {
		return emailAddress;
	}

}
