package com.example.socket;

public class MockInetSocketAddress extends MockSocketAddress {

	private final int port;
	private final MockInetAddress addr;

	public MockInetSocketAddress(String remoteHostName, int remotePort) {
		this(new MockInet6Address(remoteHostName), remotePort);
	}

	public MockInetSocketAddress(MockInetAddress remoteAddr, int remotePort) {
		this.addr = remoteAddr;
		this.port = remotePort;
	}

	public MockInetSocketAddress(int localPort) {
		this(MockInetAddress.getLocalHost(), localPort);
	}

	public MockInetAddress getAddress() {
		return this.addr;
	}

	public int getPort() {
		return port;
	}

	public boolean isUnresolved() {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockInetSocketAddress other = (MockInetSocketAddress) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (port != other.port)
			return false;
		return true;
	}



}
