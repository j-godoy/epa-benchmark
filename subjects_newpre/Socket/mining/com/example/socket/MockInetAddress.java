package com.example.socket;

/**
 * This class represents an Internet Protocol (IP) address. 
 * 
 * @author jgaleotti
 *
 */
public class MockInetAddress {

	private final String ipAddress;

	protected MockInetAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/*
	 * Returns the InetAddress representing anyLocalAddress (typically 0.0.0.0 or
	 * ::0)
	 */
	public static MockInetAddress anyLocalAddress() {
		return MockInetAddress.getByName("0.0.0.0");
	}

	private static MockInetAddress getByName(String ipAddress) {
		return new MockInet6Address(ipAddress);
	}

	/**
	 * Returns the loopback address.
	 * <p>
	 * The InetAddress returned will represent the IPv4 loopback address, 127.0.0.1,
	 * or the IPv6 loopback address, ::1. The IPv4 loopback address returned is only
	 * one of many in the form 127.*.*.*
	 *
	 * @return the InetAddress loopback instance.
	 * @since 1.7
	 */
	public static MockInetAddress getLoopbackAddress() {
		return MockInetAddress.getByName("127.0.0.1");
	}

	public static MockInetAddress getLocalHost() {
		return MockInetAddress.getByName("127.0.0.1");
	}

	public String getHostName() {
		return "hostname";
	}

}
