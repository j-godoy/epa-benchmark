package com.example.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.util.HashMap;

public class MockSocketImpl {

	private final boolean supportsUrgentData;
	private Socket socket = null;
	private final HashMap<Integer, Object> options = new HashMap<Integer, Object>();

	// write
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	// read
	private final ByteArrayInputStream in = new ByteArrayInputStream(new byte[256]);
	/**
	 * The IP address of the remote end of this socket.
	 */
	protected MockInetAddress remoteAddress;

	/**
	 * The port number on the remote host to which this socket is connected.
	 */
	protected int remotePort;

	private int localPort;

	private int timeout;

	private boolean inputHasBeenShutdown;
	private boolean outputHasBeenShutdown;
	private MockInetAddress localAddr;
	private final MockEnvironment env;

	public MockSocketImpl(MockEnvironment env) {
		this(env, true);
	}

	public MockSocketImpl(MockEnvironment env, boolean supportsUrgentData) {
		this.env = env;
		this.supportsUrgentData = supportsUrgentData;
	}

	private void checkIOException() throws MockIOException {
		if (this.env.shouldThrowIOException()) {
			throw new MockIOException();
		}
	}

	/**
	 * Closes this socket.
	 *
	 * @exception IOException
	 *                if an I/O error occurs when closing this socket.
	 */
	public void close() throws IOException, MockIOException {
		checkIOException();
	}

	/**
	 * Binds this socket to the specified local IP address and port number.
	 *
	 * @param localAddr
	 *            an IP address that belongs to a local interface.
	 * @param localPort
	 *            the port number.
	 * @exception IOException
	 *                if an I/O error occurs when binding this socket.
	 */
	public void bind(MockInetAddress localAddr, int localPort) throws IOException, MockIOException {
		if (localAddr == null) {
			throw new IllegalArgumentException();
		}
		if (localPort<0 || localPort>65535) {
			throw new IOException();
		}
		if (this.env.isBlackListed(localAddr, localPort)) {
			throw new IOException();
		}
		checkIOException();
		this.localAddr = localAddr;
		this.localPort = localPort;
	}

	/**
	 * Connects this socket to the specified port number on the specified host. A
	 * timeout of zero is interpreted as an infinite timeout. The connection will
	 * then block until established or an error occurs.
	 *
	 * @param remoteSocketAddr
	 *            the Socket address of the remote host.
	 * @param timeout
	 *            the timeout value, in milliseconds, or zero for no timeout.
	 * @exception IOException
	 *                if an I/O error occurs when attempting a connection.
	 * @since 1.4
	 */
	public void connect(MockInetSocketAddress remoteSocketAddr, int timeout) throws IOException, MockIOException {
		if (remoteSocketAddr == null)
			throw new IllegalArgumentException();

		checkIOException();

		if (timeout < 0) {
			throw new IOException("Timeout cannot be null");
		}

		if (this.env.isBlackListed(remoteSocketAddr)) {
			throw new IOException("Connection refused");
		}

		if (this.env.hasDelay(remoteSocketAddr)) {
			if (this.env.getDelay(remoteSocketAddr) > timeout) {
				throw new IOException();
			}
		}

		if (localAddr == null) {
			localAddr = MockInetAddress.getLocalHost();
			localPort = 0;
		}

		this.remoteAddress = remoteSocketAddr.getAddress();
		this.remotePort = remoteSocketAddr.getPort();
		this.timeout = timeout;
	}

	/**
	 * Connects this socket to the specified port on the named host.
	 *
	 * @param remoteHostName
	 *            the name of the remote host.
	 * @param remotePort
	 *            the port number.
	 * @exception IOException
	 *                if an I/O error occurs when connecting to the remote host.
	 */
	public void connect(String remoteHostName, int remotePort) throws IOException, MockIOException {
		if (remoteHostName == null) {
			throw new IllegalArgumentException();
		}
		if (remotePort < 0) {
			throw new IllegalArgumentException();
		}
		checkIOException();
		MockInetSocketAddress remoteSocketAddr = new MockInetSocketAddress(remoteHostName, remotePort);
		connect(remoteSocketAddr, 0);
	}

	/**
	 * Connects this socket to the specified port number on the specified host.
	 *
	 * @param address
	 *            the IP address of the remote host.
	 * @param port
	 *            the port number.
	 * @exception IOException
	 *                if an I/O error occurs when attempting a connection.
	 */
	public void connect(MockInetAddress remoteAddr, int remotePort) throws IOException, MockIOException {
		if (remoteAddr == null)
			throw new IllegalArgumentException();

		if (remotePort < 0)
			throw new IllegalArgumentException();

		checkIOException();
		MockInetSocketAddress remoteSocketAddr = new MockInetSocketAddress(remoteAddr, remotePort);
		connect(remoteSocketAddr, 0);
	}

	/**
	 * Returns the value of this socket's {@code localport} field.
	 *
	 * @return the value of this socket's {@code localport} field.
	 * @see java.net.SocketImpl#localport
	 */
	public int getLocalPort() {
		return localPort;
	}

	/**
	 * Returns the value of this socket's {@code address} field.
	 *
	 * @return the value of this socket's {@code address} field.
	 * @see java.net.SocketImpl#address
	 */
	public MockInetAddress getInetAddress() {
		return remoteAddress;
	}

	/**
	 * Returns the value of this socket's {@code port} field.
	 *
	 * @return the value of this socket's {@code port} field.
	 * @see java.net.SocketImpl#port
	 */
	public int getPort() {
		return this.remotePort;
	}

	/**
	 * Disables the output stream for this socket. For a TCP socket, any previously
	 * written data will be sent followed by TCP's normal connection termination
	 * sequence.
	 *
	 * If you write to a socket output stream after invoking shutdownOutput() on the
	 * socket, the stream will throw an IOException.
	 *
	 * @exception IOException
	 *                if an I/O error occurs when shutting down this socket.
	 * @see java.net.Socket#shutdownInput()
	 * @see java.net.Socket#close()
	 * @see java.net.Socket#setSoLinger(boolean, int)
	 * @since 1.3
	 */
	public void shutdownOutput() throws IOException, MockIOException {
		checkIOException();
		outputHasBeenShutdown = true;

	}

	public void setSocket(Socket soc) {
		this.socket = soc;

	}

	/**
	 * Creates either a stream or a datagram socket.
	 *
	 * @param stream
	 *            if {@code true}, create a stream socket; otherwise, create a
	 *            datagram socket.
	 * @exception IOException
	 *                if an I/O error occurs while creating the socket.
	 */
	public void create(boolean stream) throws IOException {
		// TODO Auto-generated method stub

	}

	public Object getOption(int key) {
		return options.get(key);
	}

	public void setOption(int key, Object value) {
		this.options.put(key, value);

	}

	/**
	 * Returns whether or not this SocketImpl supports sending urgent data. By
	 * default, false is returned unless the method is overridden in a sub-class
	 *
	 * @return true if urgent data supported
	 * @see java.net.SocketImpl#address
	 * @since 1.4
	 */
	public boolean supportsUrgentData() {
		return this.supportsUrgentData;
	}

	/**
	 * Send one byte of urgent data on the socket. The byte to be sent is the low
	 * eight bits of the parameter
	 * 
	 * @param data
	 *            The byte of data to send
	 * @exception IOException
	 *                if there is an error sending the data.
	 * @since 1.4
	 */
	public void sendUrgentData(int data) throws IOException, MockIOException {
		checkIOException();
		// TODO Auto-generated method stub
	}

	/**
	 * Places the input stream for this socket at "end of stream". Any data sent to
	 * this socket is acknowledged and then silently discarded.
	 *
	 * If you read from a socket input stream after invoking this method on the
	 * socket, the stream's {@code available} method will return 0, and its
	 * {@code read} methods will return {@code -1} (end of stream).
	 *
	 * @exception IOException
	 *                if an I/O error occurs when shutting down this socket.
	 * @see java.net.Socket#shutdownOutput()
	 * @see java.net.Socket#close()
	 * @see java.net.Socket#setSoLinger(boolean, int)
	 * @since 1.3
	 */
	public void shutdownInput() throws IOException, MockIOException {
		checkIOException();
		inputHasBeenShutdown = true;
	}

	/**
	 * Returns an output stream for this socket.
	 *
	 * @return an output stream for writing to this socket.
	 * @exception IOException
	 *                if an I/O error occurs when creating the output stream.
	 */
	public OutputStream getOutputStream() throws IOException, MockIOException {
		checkIOException();
		if (outputHasBeenShutdown) {
			throw new IOException();
		}
		return out;
	}

	/**
	 * Returns an input stream for this socket.
	 *
	 * @return a stream for reading from this socket.
	 * @exception IOException
	 *                if an I/O error occurs when creating the input stream.
	 */
	public InputStream getInputStream() throws IOException, MockIOException {
		checkIOException();
		if (inputHasBeenShutdown) {
			throw new IOException();
		}
		return in;
	}

}
