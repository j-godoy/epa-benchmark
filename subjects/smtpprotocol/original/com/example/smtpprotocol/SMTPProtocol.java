/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Ristretto Mail API.
 *
 * The Initial Developers of the Original Code are
 * Timo Stich and Frederik Dietz.
 * Portions created by the Initial Developers are Copyright (C) 2004
 * All Rights Reserved.
 *
 * Contributor(s):
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */
package com.example.smtpprotocol;

import java.util.LinkedList;

/**
 * Implementation of the client side SMTP protocol.
 * 
 * @author Timo Stich <tstich@users.sourceforge.net>
 */
public class SMTPProtocol implements MockAuthenticationServer {

//	/** JDK 1.4+ logging framework logger, used for logging. */
//	private static final Logger LOG = Logger
//			.getLogger("org.columba.ristretto.smtp");

	private static final byte[] STOPWORD = { '\r', '\n', '.', '\r', '\n' };

	private static final int DEFAULTPORT = 25;

	/**
	 * @deprecated Use NOT_CONNECTED instead
	 */
	public static final int NO_CONNECTION = 0;
	
	/**
	 * Protocol state.
	 */
	public static final int NOT_CONNECTED = 0;
	/**
	 * Protocol state.
	 */
	public static final int PLAIN = 1;
	/**
	 * Protocol state.
	 */
	public static final int AUTHORIZED = 2;

	
	/**
	 * Address type.
	 */
	public static final int TO = 0;
	/**
	 * Address type.
	 */
	public static final int CC = 1;

	private String host;

	private int port;

	private MockSocket socket;

//	protected SMTPInputStream in;

	private MockOutputStream out;

	private int state;
	
	private MockSMTPResponse smtpResponse;

	/**
	 * Constructs the SMTPProtocol.
	 * 
	 * @param host
	 *            the sever name to connect to
	 * @param port
	 *            the port to connect to
	 */
	public SMTPProtocol(String host, int port, MockSMTPResponse serverResponse) {
		this.host = host;
		this.port = port;
		this.smtpResponse = serverResponse;
		out = new MockOutputStream();
	}

	/**
	 * Constructs the SMTPProtocol. Uses the default port 25 to connect to the
	 * server.
	 * 
	 * @param host
	 *            the sever name to connect to
	 */
	public SMTPProtocol(String host) {
		this.host = host;
		this.port = DEFAULTPORT;
		this.smtpResponse = new MockSMTPResponse();
		out = new MockOutputStream();
	}
	
	public SMTPProtocol(String host, MockSMTPResponse serverResponse) {
		this.host = host;
		this.port = DEFAULTPORT;
		this.smtpResponse = serverResponse;
		out = new MockOutputStream();
	}

	/**
	 * Opens the connection to the SMTP server.
	 * 
	 * @return the domain name of the server
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public String openPort() throws MockIOException, SMTPException {
		socket = new MockSocket(host, port);
		socket.setSoTimeout(MockRistrettoConfig.getInstance().getTimeout());

//			createStreams();

			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response.getMessage());
			String domain = response.getDomain();
//
//			// don't care what the server has to say here.
//			if (response.isHasSuccessor()) {
//				response = readSingleLineResponse();
//
//				while (response.isHasSuccessor() && response.isOK()) {
//					response = readSingleLineResponse();
//				}
//			}
		state = PLAIN;
		return domain;
	}

//	private void createStreams() throws MockIOException {
//		if (MockRistrettoLogger.logStream != null) {
//			in = new SMTPInputStream(new MockLogInputStream(
//					socket.getInputStream(), MockRistrettoLogger.logStream));
//			out = new MockLogOutputStream(socket.getOutputStream(),
//					MockRistrettoLogger.logStream);
//		} else {
//			in = new SMTPInputStream(socket.getInputStream());
//			out = socket.getOutputStream();
//
//		}
//	}

	/**
	 * Switches to a SSL connection using the TLS extension.
	 * 
	 * @throws MockIOException
	 * @throws MockSSLException
	 * @throws SMTPException
	 */
	public void startTLS() throws MockIOException, MockSSLException, SMTPException {
		try {
			sendCommand("STARTTLS", null);

			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response.getMessage());

			socket = RistrettoSSLSocketFactory.getInstance().createSocket(
					socket, host, port, true);

			// handshake (which cyper algorithms are used?)
			socket.startHandshake();

//			createStreams();
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}

	}

	protected void sendCommand(String command, String[] parameters)
			throws MockIOException {
		try {
			// write the command
			out.write(command);

			// write optional parameters
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					out.write(" ");
					out.write(parameters[i]);
				}
			}

			// write CRLF
//			out.write('\r');
//			out.write('\n');

			// flush the stream
			out.flush();
		} catch (MockIOException e) {
			state = NOT_CONNECTED;
			throw e;
		}
	}

	/**
	 * Sends the EHLO command to the server. This command can be used to fetch
	 * the capabilities of the server. <br>
	 * Note: Only ESMTP servers understand this comand.
	 * 
	 * @see #helo(InetAddress)
	 * 
	 * @param domain
	 *            the domain name of the client
	 * @return the capabilities of the server
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String[] ehlo(String ipaddress) throws MockIOException, SMTPException {
		ensureState(PLAIN);
		try {

			LinkedList capas = new LinkedList();

			sendCommand("EHLO", new String[] { "[" + ipaddress + "]" });

			// First response should be the greeting or a EHLO not supported
			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR()) {
				throw new SMTPException(response.getMessage());
			}
			
			capas.add(response.getMessage());
//			if (response.isHasSuccessor()) {
//				response = readSingleLineResponse();
//
////				while (response.isHasSuccessor() && response.isOK()) {
////					capas.add(response.getMessage());
////					response = readSingleLineResponse();
////				}
//				capas.add(response.getMessage());
//			}

			return (String[]) capas.toArray(new String[] {});

		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;

			else
				return new String[0];
		}

	}

	/**
	 * Sends the HELO command to the SMTP server. Needed only for non ESMTP
	 * servers. Use #ehlo(InetAddress) instead.
	 * 
	 * @see #ehlo(InetAddress)
	 * 
	 * @param domain
	 * @throws MockIOException
	 * @throws SMTPException
	 */
//	public void helo(InetAddress domain) throws MockIOException, SMTPException {
//		ensureState(PLAIN);
//		try {
//			String ipaddress = domain.getHostAddress();
//
//			sendCommand("HELO", new String[] { "[" + ipaddress + "]" });
//
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR())
//				throw new SMTPException(response);
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//		}
//
//	}

	/**
	 * Authenticates a user. This is done with the Authentication mechanisms
	 * provided by the
	 * @param algorithm 
	 * 
	 * @link{org.columba.ristretto.auth.AuthenticationFactory}. @param
	 *                                                          algorithm the
	 *                                                          algorithm used
	 *                                                          to authenticate
	 *                                                          the user (e.g.
	 *                                                          PLAIN,
	 *                                                          DIGEST-MD5)
	 * @param user
	 *            the user name
	 * @param password
	 *            the password
	 * @throws MockIOException
	 * @throws SMTPException
	 * @throws MockAuthenticationException
	 */
//	public void auth(String algorithm, String user, char[] password)
//			throws MockIOException, SMTPException, MockAuthenticationException {
//		ensureState(PLAIN);
//		try {
//			try {
//				AuthenticationMechanism auth = AuthenticationFactory
//						.getInstance().getAuthentication(algorithm);
//				sendCommand("AUTH", new String[] { algorithm });
//
//				auth.authenticate(this, user, password);
//			} catch (NoSuchAuthenticationException e) {
//				throw new SMTPException(e);
//			}
//
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR())
//				throw new SMTPException(response);
//
//			state = AUTHORIZED;
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//		}
//
//	}

	/**
	 * Sends a MAIL command which specifies the sender's email address and
	 * starts a new mail.
	 * 
	 * @see #rcpt(Address)
	 * @see #data(InputStream)
	 * 
	 * @param from
	 *            the email address of the sender
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public void mail(String from) throws MockIOException, SMTPException {
		ensureState(PLAIN);
		try {
			sendCommand("MAIL", new String[] { "FROM:" + from});

			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response);
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}

	}

	/**
	 * Sends a RCPT TO: command which specifies a recipient of the mail started
	 * by the MAIL command. This command can be called repeatedly to add more
	 * recipients.
	 * 
	 * @see #mail(Address)
	 * @see #data(InputStream)
	 * 
	 * @param address
	 *            the email address of a recipient.
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public void rcpt(String address) throws MockIOException, SMTPException {
		try {
			sendCommand("RCPT", new String[] { "TO:" + address});
			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response);
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}

	}

	/**
	 * Sends a RCPT command which specifies a recipient of the mail started by
	 * the MAIL command. This command can be called repeatedly to add more
	 * recipients. You can pass the type parameter to either send a RCPT TO or
	 * CC.
	 * @param type 
	 * 
	 * @see #mail(Address)
	 * @see #data(InputStream)
	 * @see #TO
	 * @see #CC
	 * 
	 * @param address
	 *            the email address of a recipient.
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public void rcpt(int type, String address) throws MockIOException, SMTPException {
		try {
			switch (type) {
			case TO:
				{
					sendCommand("RCPT", new String[] { "TO:" + address});
					break;
				}
			case CC:
				{
					sendCommand("RCPT", new String[] { "CC:" + address});
					break;
				}
			}
			MockSMTPResponse response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response);
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}
	}

	/**
	 * Sends a DATA command which sends the mail to the recipients specified by
	 * the RCPT command. Can be cancelled with #dropConnection().
	 * 
	 * @see #mail(Address)
	 * @see #rcpt(Address)
	 * 
	 * @param data
	 *            the mail
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public void data(String data) throws MockIOException, SMTPException {
		ensureState(PLAIN);

		try {
			sendCommand("DATA", null);

			MockSMTPResponse response = readSingleLineResponse();
			if (response.getCode() == 354) {
				try {
//					copyStream(new StopWordSafeInputStream(data), out);
					out.write(STOPWORD);
					out.flush();
				} catch (MockIOException e) {
					state = NOT_CONNECTED;
					throw e;
				}
			} else {
				throw new SMTPException(response);
			}

			response = readSingleLineResponse();
			if (response.isERR())
				throw new SMTPException(response);
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}
	}

	/**
	 * Sends the QUIT command and closes the socket.
	 * 
	 * @throws MockIOException
	 * @throws SMTPException
	 */
	public void quit() throws MockIOException, SMTPException {
		try {
			sendCommand("QUIT", null);

			socket.close();
			smtpResponse = null;
			out = null;
			socket = null;

			state = NOT_CONNECTED;
		} catch (MockSocketException e) {
			// Catch the exception if it was caused by
			// dropping the connection
			if (state != NOT_CONNECTED)
				throw e;
		}
	}

	/**
	 * Sends a RSET command which resets the current session.
	 * 
	 * @throws MockIOException
	 * @throws SMTPException
	 */
//	public void reset() throws MockIOException, SMTPException {
//		try {
//			sendCommand("RSET", null);
//
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR())
//				throw new SMTPException(response);
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//		}
//
//	}

	/**
	 * Sends a VRFY command which verifies the given email address.
	 * 
	 * @param address
	 *            email address to verify
	 * @throws MockIOException
	 * @throws SMTPException
	 */
//	public void verify(String address) throws MockIOException, SMTPException {
//		try {
//			sendCommand("VRFY", new String[] { address });
//
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR())
//				throw new SMTPException(response);
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//		}
//
//	}

	/**
	 * Expands a given mailinglist address to all members of that list.
	 * 
	 * @param mailinglist
	 *            the mailinglist address
	 * @return the members of the mailinglist
	 * @throws MockIOException
	 * @throws SMTPException
	 */
//	public Address[] expand(Address mailinglist) throws MockIOException,
//			SMTPException {
//		ensureState(PLAIN);
//		try {
//			LinkedList addresses = new LinkedList();
//			sendCommand("VRFY", new String[] { mailinglist
//					.getCanonicalMailAddress() });
//
//			// First response should be the greeting or a EHLO not supported
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR()) {
//				throw new SMTPException(response);
//			}
//
//			if (response.isHasSuccessor()) {
//				response = readSingleLineResponse();
//
//				while (response.isHasSuccessor() && response.isOK()) {
//					try {
//						addresses.add(AddressParser.parseAddress(response
//								.getMessage()));
//					} catch (MockParserException e) {
//						LOG.severe(e.getLocalizedMessage());
//					}
//					response = readSingleLineResponse();
//				}
//				try {
//					addresses.add(AddressParser.parseAddress(response
//							.getMessage()));
//				} catch (MockParserException e) {
//					LOG.severe(e.getMessage());
//				}
//			}
//
//			return (Address[]) addresses.toArray(new Address[] {});
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//
//			else
//				return new Address[0];
//		}
//	}

	/**
	 * Sends a NOOP command to the server.
	 * 
	 * @throws MockIOException
	 * @throws SMTPException
	 */
//	public void noop() throws MockIOException, SMTPException {
//		ensureState(PLAIN);
//		try {
//			sendCommand("NOOP", null);
//			SMTPResponse response = readSingleLineResponse();
//			if (response.isERR())
//				throw new SMTPException(response.getMessage());
//		} catch (SocketException e) {
//			// Catch the exception if it was caused by
//			// dropping the connection
//			if (state != NOT_CONNECTED)
//				throw e;
//		}
//
//	}

//	private void copyStream(MockInputStream in, MockOutputStream out)
//			throws MockIOException {
//		byte[] buffer = new byte[10240];
//		int copied = 0;
//		int read;
//
//		read = in.read(buffer);
//		while (read != -1) {
//			out.write(buffer, 0, read);
//			copied += read;
//			read = in.read(buffer);
//		}
//	}

	/**
	 * @see org.MockAuthenticationServer.ristretto.auth.AuthenticationServer#authReceive()
	 */
//	public byte[] authReceive() throws MockAuthenticationException, MockIOException {
//
//		try {
//			SMTPResponse response = in.readSingleLineResponse();
//
//			if (response.isOK()) {
//				if (response.getMessage() != null) {
//					return Base64.decodeToArray(response.getMessage());
//				} else {
//					return new byte[0];
//				}
//
//			} else {
//				throw new MockAuthenticationException(new SMTPException(response));
//			}
//		} catch (SMTPException e) {
//			throw new MockAuthenticationException(e);
//		}
//	}

	/**
	 * @see org.MockAuthenticationServer.ristretto.auth.AuthenticationServer#authSend(byte[])
	 */
//	public void authSend(byte[] call) throws MockIOException {
//		sendCommand(Base64.encode(ByteBuffer.wrap(call), false).toString(),
//				null);
//	}

	/**
	 * @return Returns the state.
	 */
	public int getState() {
		return state;
	}

	/**
	 * @see org.MockAuthenticationServer.ristretto.auth.AuthenticationServer#getHostName()
	 */
	public String getHostName() {
		return host;
	}

	/**
	 * @see org.MockAuthenticationServer.ristretto.auth.AuthenticationServer#getService()
	 */
	public String getService() {
		return "smtp";
	}

	/**
	 * Drops the connection.
	 * 
	 * @throws MockIOException
	 *  
	 */
//	public void dropConnection() throws MockIOException {
//		if (state != NOT_CONNECTED) {
//			state = NOT_CONNECTED;
//
//			socket.close();
//			in = null;
//			out = null;
//			socket = null;
//		}
//	}

	private void ensureState(int s) throws SMTPException {
		if (state < s)
			throw new SMTPException("Wrong state!");
	}
	
	protected MockSMTPResponse readSingleLineResponse() throws MockIOException, SMTPException{
		if (this.smtpResponse == null)
			return new MockSMTPResponse();
		return this.smtpResponse;
	}
		
}