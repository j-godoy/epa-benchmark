package com.example.smtp;

import java.util.ArrayList;
import java.util.List;

public class MockSMTPMessage {

	private MockEmailAddress fromAddress;
	private List<MockEmailAddress> toAddresses = new ArrayList<MockEmailAddress>();
	private List<String> dataLines = new ArrayList<String>();
	private int size = 0;

	public void setFromAddress(MockEmailAddress emailAddress) {
		this.fromAddress = emailAddress;
	}

	public void addToAddress(MockEmailAddress emailAddress) {
		toAddresses.add(emailAddress);
	}

	public MockEmailAddress getFromAddress() {
		return this.fromAddress;
	}

	public List<MockEmailAddress> getToAddresses() {
		return this.toAddresses;
	}

	public void addDataLine(String dataLine) {
		this.dataLines.add(dataLine);
		this.size += dataLine.length();
	}

	public long getSize() {
		return size;
	}

	public void save() throws Exception {
		// stub
	}

	public MockFile getMessageLocation() {
		return new MockFile();
	}

}
