package com.example.smtp;

import java.util.Date;

/**
 * Handles an incoming SMTP connection. See rfc821 for details.
 *
 * @author Eric Daugherty
 */
public class SMTPProcessor {

	// ***************************************************************
	// Variables
	// ***************************************************************

	/** Logger Category for this class. */
	private static MockLog log = MockLogFactory.getLog(MockSMTPMessage.class.getName());

	/** The ConfigurationManager */
	private static MockConfigurationManager configurationManager = MockConfigurationManager.getInstance();

	/** Indicates if this thread should continue to run or shut down */
	private boolean running = true;

	/** The server socket used to listen for incoming connections */
	private MockServerSocket serverSocket;

	/** Socket connection to the client */
	private MockSocket socket;

	/** The IP address of the client */
	private String clientIp;

	/** The incoming SMTP Message */
	private MockSMTPMessage message;

	/** Writer to sent data to the client */
	private MockPrintWriter out;
	/** Reader to read data from the client */
	private MockBufferedReader in;

	private int lastCommand;

	public SMTPProcessor() {
		this.setSocket(new MockServerSocket());
		this.lastCommand = NONE;

	}
	// ***************************************************************
	// Public Interface
	// ***************************************************************

	/**
	 * Sets the socket used to communicate with the client.
	 */
	private void setSocket(MockServerSocket serverSocket) {

		this.serverSocket = serverSocket;
		try {
			// Set the socket to timeout every 10 seconds so it does not
			// just block forever.
			serverSocket.setSoTimeout(10 * 1000);
		} catch (MockSocketException se) {
			log.fatal("Error initializing Socket Timeout in SMTPProcessor");
		}

		try {

			socket = serverSocket.accept();

			// Set the socket to timeout after 10 seconds
			socket.setSoTimeout(10 * 1000);

			// Prepare the input and output streams.
			out = new MockPrintWriter(socket.getOutputStream(), true);
			in = new MockBufferedReader(new MockInputStreamReader(socket.getInputStream()));

			MockInetAddress remoteAddress = socket.getInetAddress();
			clientIp = remoteAddress.getHostAddress();

			if (log.isInfoEnabled()) {
				log.info(remoteAddress.getHostName() + "(" + clientIp + ") socket connected via SMTP.");
			}

			write(WELCOME_MESSAGE);

			// Initialize the input message.
			message = new MockSMTPMessage();

		} catch (MockInterruptedIOException iioe) {
			// This is fine, it should time out every 10 seconds if
			// a connection is not made.
		} catch (Throwable e) {
			log.debug("Disconnecting Exception:", e);
			disconnect();
		}

	}

	private void disconnect() {
		log.info("Disconnecting");
		try {
			write(MESSAGE_DISCONNECT);
		} catch (Exception e1) {
			log.debug("Error sending disconnect message.", e1);
			// Nothing to do.
		}
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (MockIOException ioe) {
			log.debug("Error disconnecting.", ioe);
			// Nothing to do.
		}
	}

	/**
	 * Entrypoint for the Thread, this method handles the interaction with the
	 * client socket.
	 */
	private void run() {

		// try {
		// // Set the socket to timeout every 10 seconds so it does not
		// // just block forever.
		// serverSocket.setSoTimeout(10 * 1000);
		// } catch (MockSocketException se) {
		// log.fatal("Error initializing Socket Timeout in SMTPProcessor");
		// }

		// while (running) {
		try {
			// socket = serverSocket.accept();
			//
			// // Set the socket to timeout after 10 seconds
			// socket.setSoTimeout(10 * 1000);
			//
			// // Prepare the input and output streams.
			// out = new MockPrintWriter(socket.getOutputStream(), true);
			// in = new MockBufferedReader(new
			// MockInputStreamReader(socket.getInputStream()));
			//
			// MockInetAddress remoteAddress = socket.getInetAddress();
			// clientIp = remoteAddress.getHostAddress();
			//
			// if (log.isInfoEnabled()) {
			// log.info(remoteAddress.getHostName() + "(" + clientIp + ") socket connected
			// via SMTP.");
			// }
			//
			// write(WELCOME_MESSAGE);
			//
			// // Initialize the input message.
			// message = new MockSMTPMessage();

			// Parses the input for commands and delegates to the appropriate methods.
			handleCommand();

			// } catch (MockInterruptedIOException iioe) {
			// // This is fine, it should time out every 10 seconds if
			// // a connection is not made.
			// }
			// If any exception gets to here uncaught, it means we should just disconnect.
		} catch (Throwable e) {
			log.debug("Disconnecting Exception:", e);
			disconnect();
		}
		// }
	}

	/**
	 * Notifies this thread to stop processing and exit.
	 */
	private void shutdown() {
		log.warn("Shutting down SMTPProcessor.");
		running = false;
	}

	// ***************************************************************
	// Private Interface
	// ***************************************************************

	/**
	 * Checks to make sure that the incoming command is not a quit. If so, the
	 * connection is terminated.
	 */
	private void checkQuit(String command) {

		if (command.equals(COMMAND_QUIT)) {
			log.debug("User has QUIT the session.");
			throw new RuntimeException();
		}
	}

	public void HELO(String argument) throws MockSocketException, Exception {
		try {
			helo0(argument);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void helo0(String argument) throws MockSocketException, Exception {
		if (!HELO_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_HELO + " " + argument);
		run();
	}

	public void NOOP() throws MockSocketException, Exception {
		try {
			noop0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void noop0() throws MockSocketException, Exception {
		if (!NOOP_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_NOOP);
		run();
	}

	public void RSET() throws MockSocketException, Exception {
		try {
			rset0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void rset0() throws MockSocketException, Exception {
		if (!RSET_pre()) {
			throw new IllegalStateException();
		}

		enqueueLineToInputStream(COMMAND_RSET);
		run();
	}

	public void MAIL(String emailAddress) throws MockSocketException, Exception {
		try {
			mail0(emailAddress);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void mail0(String emailAddress) throws MockSocketException, Exception {
		if (!MAIL_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_MAIL_FROM + " FROM:" + emailAddress);
		run();
	}

	public void RCPT_TO(String emailAddress) throws MockSocketException, Exception {
		try {
			rcptTo0(emailAddress);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void rcptTo0(String emailAddress) throws MockSocketException, Exception {
		if (!RCPT_TO_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_RCPT_TO + " TO:" + emailAddress);
		run();
	}

	public void DATA(String data) throws MockSocketException, Exception {
		try {
			data0(data);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void data0(String data) throws MockSocketException, Exception {
		if (!DATA_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_DATA);
		String[] lines = data.split("\n");
		for (int i = 0; i < lines.length; ++i) {
			String line = lines[i];
			enqueueLineToInputStream(line);
		}
		enqueueLineToInputStream(".");
		run();
	}

	private void enqueueLineToInputStream(String lineStr) throws MockSocketException, Exception {
		if (this.socket.isClosed()) {
			throw new MockSocketException("Connection is closed");
		}
		in.writeLine(lineStr);
	}

	public void QUIT() throws MockSocketException, Exception {
		try {
			quit0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void quit0() throws MockSocketException, Exception {
		if (!QUIT_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_QUIT);
		run();
	}

	private boolean HELO_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean NOOP_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean RSET_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean QUIT_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean MAIL_pre() {
		return socket != null && !socket.isClosed()
				&& (lastCommand == HELO || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO);
	}

	private boolean RCPT_TO_pre() {
		return socket != null && !socket.isClosed() && (lastCommand == MAIL_FROM || lastCommand == RCPT_TO);
	}

	private boolean DATA_pre() {
		return socket != null && !socket.isClosed() && (lastCommand == RCPT_TO && message.getToAddresses().size() > 0);
	}

	/**
	 * Handles all the commands related the the sending of mail.
	 */
	private void handleCommand() {

		// Reusable Variables.
		String inputString;
		String command;
		String argument;

		// This just runs until a SystemException is thrown, which
		// signals us to disconnect.

		inputString = read();

		command = parseCommand(inputString);
		argument = parseArgument(inputString);

		if (command.equals(COMMAND_HELO)) {
			write("250 Hello " + argument);
			lastCommand = HELO;
		}
		// NOOP - Do Nothing.
		else if (command.equals(COMMAND_NOOP)) {
			write(MESSAGE_OK);
		}
		// Resets the state of the server back to the initial
		// state.
		else if (command.equals(COMMAND_RSET)) {
			message = new MockSMTPMessage();
			write(MESSAGE_OK);
			lastCommand = RSET;
		}
		// Not only check the command, but the full string, since the prepare command
		// method only returns the text before the first string, and this is a two
		// word command.
		else if (command.equals(COMMAND_MAIL_FROM) && inputString.toUpperCase().startsWith("MAIL FROM:")) {

			if (lastCommand == HELO || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO) {
				if (handleMailFrom(inputString)) {
					lastCommand = MAIL_FROM;
				}
			} else {
				write(MESSAGE_COMMAND_ORDER_INVALID);
			}
		}
		// Not only check the command, but the full string, since the prepare command
		// method only returns the text before the first string, and this is a two
		// word command.
		else if (command.equals(COMMAND_RCPT_TO) && inputString.toUpperCase().startsWith("RCPT TO:")) {

			if (lastCommand == MAIL_FROM || lastCommand == RCPT_TO) {
				handleRcptTo(inputString);
				lastCommand = RCPT_TO;
			} else {
				write(MESSAGE_COMMAND_ORDER_INVALID);
			}
		} else if (command.equals(COMMAND_DATA)) {

			if (lastCommand == RCPT_TO && message.getToAddresses().size() > 0) {
				handleData();
				// Reset for another message
				message = new MockSMTPMessage();
				lastCommand = RSET;
			} else {
				write(MESSAGE_COMMAND_ORDER_INVALID);
			}
		} else {
			write(MESSAGE_INVALID_COMMAND + command);
		}
	}

	/**
	 * Handle the "MAIL FROM:" command, which defines the sending address for this
	 * message.
	 */
	private boolean handleMailFrom(String inputString) {

		String fromAddress = parseAddress(inputString.substring(10));

		try {
			// It is legal for the MAIL FROM address to be empty.
			if (fromAddress == null || fromAddress.trim().equals("")) {
				message.setFromAddress(new MockEmailAddress());
				message.setFromAddress(new MockEmailAddress("unknown@example.com"));
				log.debug("MAIL FROM is empty, using unknown@example.com");
			}
			// Although this is the normal case...
			else {
				MockEmailAddress address = new MockEmailAddress(fromAddress);
				message.setFromAddress(address);
				if (log.isDebugEnabled()) {
					log.debug("MAIL FROM: " + fromAddress);
				}
			}
			write(MESSAGE_OK);
			return true;
		} catch (MockInvalidAddressException iae) {
			log.debug("Unable to parse From Address: " + fromAddress);
			write(MESSAGE_USER_INVALID);
			return false;
		}
	}

	/**
	 * Handle the "RCPT TO:" command, which defines one of the recieving addresses.
	 */
	private void handleRcptTo(String inputString) {

		String toAddress = parseAddress(inputString.substring(8));

		try {
			MockEmailAddress address = new MockEmailAddress(toAddress);
			// Check the address to see if we can deliver it.
			MockDeliveryService deliveryService = MockDeliveryService.getDeliveryService();
			if (deliveryService.acceptAddress(address, clientIp, message.getFromAddress())) {
				// Check to see if it is a local user. If so, ask to
				// user object for the delivery addresses.
				MockUser localUser = configurationManager.getUser(address);
				if (localUser != null) {
					MockEmailAddress[] addresses = localUser.getDeliveryAddresses();
					for (int index = 0; index < addresses.length; index++) {
						message.addToAddress(addresses[index]);
					}
				}
				// Otherwise, just add the address.
				else {
					message.addToAddress(address);
				}
				write(MESSAGE_OK);
				if (log.isDebugEnabled()) {
					log.debug("RCTP TO: " + address.getAddress() + " accepted.");
				}
			} else {
				if (log.isInfoEnabled())
					log.info("Invalid delivery address for incoming mail: " + toAddress + " from client: " + clientIp
							+ " / " + message.getFromAddress());
				throw new MockInvalidAddressException();
			}
		} catch (MockInvalidAddressException iae) {
			write(MESSAGE_USER_NOT_LOCAL);
			log.debug("RCTP TO: " + toAddress + " rejected.");
			return;
		}
	}

	/**
	 * Accepts the data being written to the socket.
	 */
	private void handleData() {

		// Get the current maxSize setting and convert to bytes.
		long maxSize = configurationManager.getMaximumMessageSize() * 1024 * 1024;

		write(MESSAGE_SEND_DATA);

		// Add a datestamp to the message to track when the message arrived.
		message.addDataLine("X-RecievedDate: " + new Date());
		// Add a line to the message to track that the message when through this server.
		message.addDataLine("Received: by EricDaugherty's JES SMTP " + configurationManager.getLocalDomains()[0]
				+ " from client: " + clientIp);

		try {
			String inputString = in.readLine();

			while (!inputString.equals(".")) {
				if (log.isDebugEnabled()) {
					log.debug("Read Data: " + inputString);
				}
				message.addDataLine(inputString);
				inputString = in.readLine();

				// Check message size
				if (message.getSize() > maxSize) {
					log.warn("Message Rejected.  Message larger than max allowed size ("
							+ configurationManager.getMaximumMessageSize() + " MB)");
					write(MESSAGE_MESSAGE_TOO_LARGE);
					throw new RuntimeException("Aborting Connection.  Message size too large.");
				}
			}
			log.debug("Data Input Complete.");
		} catch (MockIOException ioe) {
			log.error("An error occured while retrieving the message data.", ioe);
			throw new RuntimeException();
		}

		// Write the message to disk.

		try {
			message.save();
			write(MESSAGE_OK);
		} catch (Exception se) {
			write(MESSAGE_SAVE_MESSAGE_ERROR);
			throw new RuntimeException(se.getMessage());
		}

		if (log.isInfoEnabled())
			log.info("Message " + message.getMessageLocation().getName() + " accepted for delivery.");
	}

	/**
	 * Reads a line from the input stream and returns it.
	 */
	private String read() {
		try {
			String inputLine = in.readLine().trim();
			if (log.isDebugEnabled()) {
				log.debug("Read Input: " + inputLine);
			}
			return inputLine;
		} catch (MockIOException ioe) {
			log.error("Error reading from socket.", ioe);
			throw new RuntimeException();
		}
	}

	/**
	 * Writes the specified output message to the client.
	 */
	private void write(String message) {

		if (log.isDebugEnabled()) {
			log.debug("Writing: " + message);
		}
		out.print(message + "\r\n");
		out.flush();
	}

	/**
	 * Parses the input stream for the command. The command is the begining of the
	 * input stream to the first space. If there is space found, the entire input
	 * string is returned.
	 * <p>
	 * This method converts the returned command to uppercase to allow for easier
	 * comparison.
	 * <p>
	 * Additinally, this method checks to verify that the quit command was not
	 * issued. If it was, a SystemException is thrown to terminate the connection.
	 */
	private String parseCommand(String inputString) {

		int index = inputString.indexOf(" ");

		if (index == -1) {
			String command = inputString.toUpperCase();
			checkQuit(command);
			return command;
		} else {
			String command = inputString.substring(0, index).toUpperCase();
			checkQuit(command);
			return command;
		}
	}

	/**
	 * Parses the input stream for the argument. The argument is the text starting
	 * afer the first space until the end of the inputstring. If there is no space
	 * found, an empty string is returned.
	 * <p>
	 * This method does not convert the case of the argument.
	 */
	private String parseArgument(String inputString) {

		int index = inputString.indexOf(" ");

		if (index == -1) {
			return "";
		} else {
			return inputString.substring(index + 1);
		}
	}

	/**
	 * Parses an address argument into a real email address. This method strips off
	 * any &gt; or &lt; symbols.
	 */
	private String parseAddress(String address) {

		int index = address.indexOf("<");
		if (index != -1) {
			address = address.substring(index + 1);
		}
		index = address.indexOf(">");
		if (index != -1) {
			address = address.substring(0, index);
		}
		return address;
	}

	// ***************************************************************
	// Constants
	// ***************************************************************

	// Message Constants
	// General Message
	private static final String WELCOME_MESSAGE = "220 Welcome to EricDaugherty's Java SMTP Server.";
	private static final String MESSAGE_DISCONNECT = "221 SMTP server signing off.";
	private static final String MESSAGE_OK = "250 OK";
	private static final String MESSAGE_COMMAND_ORDER_INVALID = "503 Command not allowed here.";
	private static final String MESSAGE_USER_NOT_LOCAL = "550 User does not exist.";
	private static final String MESSAGE_USER_INVALID = "451 Address is invalid.";
	private static final String MESSAGE_SEND_DATA = "354 Start mail input; end with <CRLF>.<CRLF>";
	private static final String MESSAGE_SAVE_MESSAGE_ERROR = "500 Error handling message.";
	private static final String MESSAGE_INVALID_COMMAND = "500 Command Unrecognized: ";
	private static final String MESSAGE_MESSAGE_TOO_LARGE = "552 Message size exceeds fixed maximum message size.";

	// Commands
	private static final String COMMAND_HELO = "HELO";
	private static final String COMMAND_RSET = "RSET";
	private static final String COMMAND_NOOP = "NOOP";
	private static final String COMMAND_QUIT = "QUIT";
	private static final String COMMAND_MAIL_FROM = "MAIL";
	private static final String COMMAND_RCPT_TO = "RCPT";
	private static final String COMMAND_DATA = "DATA";

	// SMTP Commands
	private final int NONE = 0;
	private final int HELO = 1;
	private final int QUIT = 2;
	private final int MAIL_FROM = 3;
	private final int RCPT_TO = 4;
	private final int DATA = 5;
	private final int DATA_FINISHED = 6;
	private final int RSET = 7;
	private final int EHLO = 8;

}