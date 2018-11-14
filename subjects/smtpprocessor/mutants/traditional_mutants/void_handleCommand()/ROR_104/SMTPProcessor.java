// This is a mutant program.
// Author : ysma

package com.example.smtp;


import java.util.Date;


public class SMTPProcessor
{

    private static com.example.smtp.MockLog log = MockLogFactory.getLog( (com.example.smtp.MockSMTPMessage.class).getName() );

    private static com.example.smtp.MockConfigurationManager configurationManager = MockConfigurationManager.getInstance();

    private boolean running = true;

    private com.example.smtp.MockServerSocket serverSocket;

    private com.example.smtp.MockSocket socket;

    private java.lang.String clientIp;

    private com.example.smtp.MockSMTPMessage message;

    private com.example.smtp.MockPrintWriter out;

    private com.example.smtp.MockBufferedReader in;

    private int lastCommand;

    public SMTPProcessor()
    {
        this.setSocket( new com.example.smtp.MockServerSocket() );
        this.lastCommand = NONE;
    }

    private  void setSocket( com.example.smtp.MockServerSocket serverSocket )
    {
        this.serverSocket = serverSocket;
        try {
            serverSocket.setSoTimeout( 10 * 1000 );
        } catch ( com.example.smtp.MockSocketException se ) {
            log.fatal( "Error initializing Socket Timeout in SMTPProcessor" );
        }
        try {
            socket = serverSocket.accept();
            socket.setSoTimeout( 10 * 1000 );
            out = new com.example.smtp.MockPrintWriter( socket.getOutputStream(), true );
            in = new com.example.smtp.MockBufferedReader( new com.example.smtp.MockInputStreamReader( socket.getInputStream() ) );
            com.example.smtp.MockInetAddress remoteAddress = socket.getInetAddress();
            clientIp = remoteAddress.getHostAddress();
            if (log.isInfoEnabled()) {
                log.info( remoteAddress.getHostName() + "(" + clientIp + ") socket connected via SMTP." );
            }
            write( WELCOME_MESSAGE );
            message = new com.example.smtp.MockSMTPMessage();
        } catch ( com.example.smtp.MockInterruptedIOException iioe ) {
        } catch ( java.lang.Throwable e ) {
            log.debug( "Disconnecting Exception:", e );
            disconnect();
        }
    }

    private  void disconnect()
    {
        log.info( "Disconnecting" );
        try {
            write( MESSAGE_DISCONNECT );
        } catch ( java.lang.Exception e1 ) {
            log.debug( "Error sending disconnect message.", e1 );
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch ( com.example.smtp.MockIOException ioe ) {
            log.debug( "Error disconnecting.", ioe );
        }
    }

    private  void run()
    {
        try {
            handleCommand();
        } catch ( java.lang.Throwable e ) {
            log.debug( "Disconnecting Exception:", e );
            disconnect();
        }
    }

    private  void shutdown()
    {
        log.warn( "Shutting down SMTPProcessor." );
        running = false;
    }

    private  void checkQuit( java.lang.String command )
    {
        if (command.equals( COMMAND_QUIT )) {
            log.debug( "User has QUIT the session." );
            throw new java.lang.RuntimeException();
        }
    }

    public  void HELO( java.lang.String argument )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            helo0( argument );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void helo0( java.lang.String argument )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!HELO_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_HELO + " " + argument );
        run();
    }

    public  void NOOP()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            noop0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void noop0()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!NOOP_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_NOOP );
        run();
    }

    public  void RSET()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            rset0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void rset0()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!RSET_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_RSET );
        run();
    }

    public  void MAIL( java.lang.String emailAddress )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            mail0( emailAddress );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void mail0( java.lang.String emailAddress )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!MAIL_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_MAIL_FROM + " FROM:" + emailAddress );
        run();
    }

    public  void RCPT_TO( java.lang.String emailAddress )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            rcptTo0( emailAddress );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void rcptTo0( java.lang.String emailAddress )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!RCPT_TO_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_RCPT_TO + " TO:" + emailAddress );
        run();
    }

    public  void DATA( java.lang.String data )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            data0( data );
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void data0( java.lang.String data )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!DATA_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_DATA );
        java.lang.String[] lines = data.split( "\n" );
        for (int i = 0; i < lines.length; ++i) {
            java.lang.String line = lines[i];
            enqueueLineToInputStream( line );
        }
        enqueueLineToInputStream( "." );
        run();
    }

    private  void enqueueLineToInputStream( java.lang.String lineStr )
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (this.socket.isClosed()) {
            throw new com.example.smtp.MockSocketException( "Connection is closed" );
        }
        in.writeLine( lineStr );
    }

    public  void QUIT()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        try {
            quit0();
        } catch ( java.lang.Exception ex ) {
            throw ex;
        }
    }

    private  void quit0()
        throws com.example.smtp.MockSocketException, java.lang.Exception
    {
        if (!QUIT_pre()) {
            throw new java.lang.IllegalStateException();
        }
        enqueueLineToInputStream( COMMAND_QUIT );
        run();
    }

    private  boolean HELO_pre()
    {
        return socket != null && !socket.isClosed();
    }

    private  boolean NOOP_pre()
    {
        return socket != null && !socket.isClosed();
    }

    private  boolean RSET_pre()
    {
        return socket != null && !socket.isClosed();
    }

    private  boolean QUIT_pre()
    {
        return socket != null && !socket.isClosed();
    }

    private  boolean MAIL_pre()
    {
        return socket != null && !socket.isClosed() && (lastCommand == HELO || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO);
    }

    private  boolean RCPT_TO_pre()
    {
        return socket != null && !socket.isClosed() && (lastCommand == MAIL_FROM || lastCommand == RCPT_TO);
    }

    private  boolean DATA_pre()
    {
        return socket != null && !socket.isClosed() && (lastCommand == RCPT_TO && message.getToAddresses().size() > 0);
    }

    private  void handleCommand()
    {
        java.lang.String inputString;
        java.lang.String command;
        java.lang.String argument;
        inputString = read();
        command = parseCommand( inputString );
        argument = parseArgument( inputString );
        if (command.equals( COMMAND_HELO )) {
            write( "250 Hello " + argument );
            lastCommand = HELO;
        } else {
            if (command.equals( COMMAND_NOOP )) {
                write( MESSAGE_OK );
            } else {
                if (command.equals( COMMAND_RSET )) {
                    message = new com.example.smtp.MockSMTPMessage();
                    write( MESSAGE_OK );
                    lastCommand = RSET;
                } else {
                    if (command.equals( COMMAND_MAIL_FROM ) && inputString.toUpperCase().startsWith( "MAIL FROM:" )) {
                        if (lastCommand == HELO || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO) {
                            if (handleMailFrom( inputString )) {
                                lastCommand = MAIL_FROM;
                            }
                        } else {
                            write( MESSAGE_COMMAND_ORDER_INVALID );
                        }
                    } else {
                        if (command.equals( COMMAND_RCPT_TO ) && inputString.toUpperCase().startsWith( "RCPT TO:" )) {
                            if (lastCommand != MAIL_FROM || lastCommand == RCPT_TO) {
                                handleRcptTo( inputString );
                                lastCommand = RCPT_TO;
                            } else {
                                write( MESSAGE_COMMAND_ORDER_INVALID );
                            }
                        } else {
                            if (command.equals( COMMAND_DATA )) {
                                if (lastCommand == RCPT_TO && message.getToAddresses().size() > 0) {
                                    handleData();
                                    message = new com.example.smtp.MockSMTPMessage();
                                    lastCommand = RSET;
                                } else {
                                    write( MESSAGE_COMMAND_ORDER_INVALID );
                                }
                            } else {
                                write( MESSAGE_INVALID_COMMAND + command );
                            }
                        }
                    }
                }
            }
        }
    }

    private  boolean handleMailFrom( java.lang.String inputString )
    {
        java.lang.String fromAddress = parseAddress( inputString.substring( 10 ) );
        try {
            if (fromAddress == null || fromAddress.trim().equals( "" )) {
                message.setFromAddress( new com.example.smtp.MockEmailAddress() );
                message.setFromAddress( new com.example.smtp.MockEmailAddress( "unknown@example.com" ) );
                log.debug( "MAIL FROM is empty, using unknown@example.com" );
            } else {
                com.example.smtp.MockEmailAddress address = new com.example.smtp.MockEmailAddress( fromAddress );
                message.setFromAddress( address );
                if (log.isDebugEnabled()) {
                    log.debug( "MAIL FROM: " + fromAddress );
                }
            }
            write( MESSAGE_OK );
            return true;
        } catch ( com.example.smtp.MockInvalidAddressException iae ) {
            log.debug( "Unable to parse From Address: " + fromAddress );
            write( MESSAGE_USER_INVALID );
            return false;
        }
    }

    private  void handleRcptTo( java.lang.String inputString )
    {
        java.lang.String toAddress = parseAddress( inputString.substring( 8 ) );
        try {
            com.example.smtp.MockEmailAddress address = new com.example.smtp.MockEmailAddress( toAddress );
            com.example.smtp.MockDeliveryService deliveryService = MockDeliveryService.getDeliveryService();
            if (deliveryService.acceptAddress( address, clientIp, message.getFromAddress() )) {
                com.example.smtp.MockUser localUser = configurationManager.getUser( address );
                if (localUser != null) {
                    com.example.smtp.MockEmailAddress[] addresses = localUser.getDeliveryAddresses();
                    for (int index = 0; index < addresses.length; index++) {
                        message.addToAddress( addresses[index] );
                    }
                } else {
                    message.addToAddress( address );
                }
                write( MESSAGE_OK );
                if (log.isDebugEnabled()) {
                    log.debug( "RCTP TO: " + address.getAddress() + " accepted." );
                }
            } else {
                if (log.isInfoEnabled()) {
                    log.info( "Invalid delivery address for incoming mail: " + toAddress + " from client: " + clientIp + " / " + message.getFromAddress() );
                }
                throw new com.example.smtp.MockInvalidAddressException();
            }
        } catch ( com.example.smtp.MockInvalidAddressException iae ) {
            write( MESSAGE_USER_NOT_LOCAL );
            log.debug( "RCTP TO: " + toAddress + " rejected." );
            return;
        }
    }

    private  void handleData()
    {
        long maxSize = configurationManager.getMaximumMessageSize() * 1024 * 1024;
        write( MESSAGE_SEND_DATA );
        message.addDataLine( "X-RecievedDate: " + new java.util.Date() );
        message.addDataLine( "Received: by EricDaugherty's JES SMTP " + configurationManager.getLocalDomains()[0] + " from client: " + clientIp );
        try {
            java.lang.String inputString = in.readLine();
            while (!inputString.equals( "." )) {
                if (log.isDebugEnabled()) {
                    log.debug( "Read Data: " + inputString );
                }
                message.addDataLine( inputString );
                inputString = in.readLine();
                if (message.getSize() > maxSize) {
                    log.warn( "Message Rejected.  Message larger than max allowed size (" + configurationManager.getMaximumMessageSize() + " MB)" );
                    write( MESSAGE_MESSAGE_TOO_LARGE );
                    throw new java.lang.RuntimeException( "Aborting Connection.  Message size too large." );
                }
            }
            log.debug( "Data Input Complete." );
        } catch ( com.example.smtp.MockIOException ioe ) {
            log.error( "An error occured while retrieving the message data.", ioe );
            throw new java.lang.RuntimeException();
        }
        try {
            message.save();
            write( MESSAGE_OK );
        } catch ( java.lang.Exception se ) {
            write( MESSAGE_SAVE_MESSAGE_ERROR );
            throw new java.lang.RuntimeException( se.getMessage() );
        }
        if (log.isInfoEnabled()) {
            log.info( "Message " + message.getMessageLocation().getName() + " accepted for delivery." );
        }
    }

    private  java.lang.String read()
    {
        try {
            java.lang.String inputLine = in.readLine().trim();
            if (log.isDebugEnabled()) {
                log.debug( "Read Input: " + inputLine );
            }
            return inputLine;
        } catch ( com.example.smtp.MockIOException ioe ) {
            log.error( "Error reading from socket.", ioe );
            throw new java.lang.RuntimeException();
        }
    }

    private  void write( java.lang.String message )
    {
        if (log.isDebugEnabled()) {
            log.debug( "Writing: " + message );
        }
        out.print( message + "\r\n" );
        out.flush();
    }

    private  java.lang.String parseCommand( java.lang.String inputString )
    {
        int index = inputString.indexOf( " " );
        if (index == -1) {
            java.lang.String command = inputString.toUpperCase();
            checkQuit( command );
            return command;
        } else {
            java.lang.String command = inputString.substring( 0, index ).toUpperCase();
            checkQuit( command );
            return command;
        }
    }

    private  java.lang.String parseArgument( java.lang.String inputString )
    {
        int index = inputString.indexOf( " " );
        if (index == -1) {
            return "";
        } else {
            return inputString.substring( index + 1 );
        }
    }

    private  java.lang.String parseAddress( java.lang.String address )
    {
        int index = address.indexOf( "<" );
        if (index != -1) {
            address = address.substring( index + 1 );
        }
        index = address.indexOf( ">" );
        if (index != -1) {
            address = address.substring( 0, index );
        }
        return address;
    }

    private static final java.lang.String WELCOME_MESSAGE = "220 Welcome to EricDaugherty's Java SMTP Server.";

    private static final java.lang.String MESSAGE_DISCONNECT = "221 SMTP server signing off.";

    private static final java.lang.String MESSAGE_OK = "250 OK";

    private static final java.lang.String MESSAGE_COMMAND_ORDER_INVALID = "503 Command not allowed here.";

    private static final java.lang.String MESSAGE_USER_NOT_LOCAL = "550 User does not exist.";

    private static final java.lang.String MESSAGE_USER_INVALID = "451 Address is invalid.";

    private static final java.lang.String MESSAGE_SEND_DATA = "354 Start mail input; end with <CRLF>.<CRLF>";

    private static final java.lang.String MESSAGE_SAVE_MESSAGE_ERROR = "500 Error handling message.";

    private static final java.lang.String MESSAGE_INVALID_COMMAND = "500 Command Unrecognized: ";

    private static final java.lang.String MESSAGE_MESSAGE_TOO_LARGE = "552 Message size exceeds fixed maximum message size.";

    private static final java.lang.String COMMAND_HELO = "HELO";

    private static final java.lang.String COMMAND_RSET = "RSET";

    private static final java.lang.String COMMAND_NOOP = "NOOP";

    private static final java.lang.String COMMAND_QUIT = "QUIT";

    private static final java.lang.String COMMAND_MAIL_FROM = "MAIL";

    private static final java.lang.String COMMAND_RCPT_TO = "RCPT";

    private static final java.lang.String COMMAND_DATA = "DATA";

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
