package com.example.socket;

public class MockSocketImplFactory {

	/**
	 * Creates a new {@code SocketImpl} instance.
	 *
	 * @return a new instance of {@code SocketImpl}.
	 * @see java.net.SocketImpl
	 */
	public MockSocketImpl createSocketImpl(MockEnvironment env) {
		return new MockSocketImpl(env);
	}

}
