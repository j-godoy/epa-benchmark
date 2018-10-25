package com.example.socket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MockEnvironment {

	public MockEnvironment() {

	}

	private final Stack<Boolean> ioExceptions = new Stack<Boolean>();

	private final Set<MockSocketAddress> blackListedSocketAddresses = new HashSet<MockSocketAddress>();

	private final Map<MockSocketAddress, Integer> delayToConnectAddress = new HashMap<MockSocketAddress, Integer>();

	public void pushIOException(boolean shouldThrowIOException) {
		ioExceptions.push(shouldThrowIOException);
	}

	public boolean shouldThrowIOException() {
		if (ioExceptions.isEmpty()) {
			return false;
		} else {
			boolean b = ioExceptions.pop();
			return b;
		}
	}

	public void addToBlackList(MockSocketAddress socketAddr) {
		blackListedSocketAddresses.add(socketAddr);
	}

	public boolean isBlackListed(MockInetSocketAddress remoteSocketAddr) {
		return blackListedSocketAddresses.contains(remoteSocketAddr);
	}

	public boolean isBlackListed(MockInetAddress addr, int port) {
		MockInetSocketAddress socketAddr = new MockInetSocketAddress(addr, port);
		return this.isBlackListed(socketAddr);
	}

	public void setDelayToConnect(MockSocketAddress socketAddr, int delay) {
		if (delay < 0) {
			throw new IllegalArgumentException("Delay should be non negative");
		} else if (delay > 0) {
			this.delayToConnectAddress.put(socketAddr, delay);
		} else {
			// delay=0 means no delay at all
		}
	}

	public boolean hasDelay(MockInetSocketAddress remoteSocketAddr) {
		return delayToConnectAddress.containsKey(remoteSocketAddr);
	}

	public int getDelay(MockInetSocketAddress remoteSocketAddr) {
		return delayToConnectAddress.get(remoteSocketAddr);
	}

}
