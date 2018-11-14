/******************************************************************************
 * This program is a 100% Java Email Server.
 ******************************************************************************
 * Copyright (c) 2001-2009, Eric Daugherty (http://www.ericdaugherty.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the copyright holder nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 ******************************************************************************
 * For current versions and more information, please visit:
 * http://www.ericdaugherty.com/java/mail
 *
 * or contact the authors at:
 * java@ericdaugherty.com
 * andreask1@vivodinet.gr
 *
 ******************************************************************************
 * This program is based on the CSRMail project written by Calvin Smith.
 * http://crsemail.sourceforge.net/
 ******************************************************************************
 *
 * $Rev$
 * $Date: 2011/02/16 17:07:28 $
 *
 ******************************************************************************/

package com.example.smtp;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;

/**
 * Handles an incoming SMTP connection.  See rfc821/2821/5321 for details.
 *
 * @author Eric Daugherty
 * @author Andreas Kyrmegalos (2.x branch)
 */
public class SMTPProcessor {

//    /** Logger Category for this class. */
    private static final MockLog log = MockLogFactory.getLog( SMTPProcessor.class.getName() );

    //***************************************************************
    // Variables
    //***************************************************************

    /** Indicates if this thread should continue to run or shut down */
//    private boolean running = true;

    /** The server socket used to listen for incoming connections */
    private MockServerSocket serverSocket;

    /** Socket connection to the client */
    private MockSocket socket;

    /** The IP address of the client */
    private String clientIp;

    private String declaredHelloHostname;
    
    /** This setting defines the security state of the session.
     *  Please note the following:
     *  A connection over a standard port with the setting standardsecure=true
     *  starts as non-secure.
     *  A connection over a standard port with the setting standardsecure=false
     *  is considered secure for the duration of the session.
     *  A connection over a secure port
     *  is considered secure for the duration of the session.
     */
//    private boolean isSecure;
    
    /** This setting is used to certify the encryption state of the connection */
    private boolean isEncrypted;
    
    /** This setting is used to prevent a shutdown after all DATA have been received */
    private boolean isFinishedData;
    
    /** This setting is used to track whether ESMTP was used during the last session */
    private boolean isESMTP;
    
    private boolean isRejected;

    private boolean is8bitMIMESupported;

    private boolean isHELOEnabled;
    
    /** The number of errors during a given session */
    private int errorcount;
    
    private MockConfigurationManager configurationManager = new MockConfigurationManager();
    
    /** The maximum number of allowed errors */
    private int maxerrorcount = configurationManager.getMaxErrorCount();
    
    /** A temporary fromaddress field */
    private MockEmailAddress fromaddress;
    
    private int allowClearText = configurationManager.allowClearTextSMTP();
    private int NEVER = MockConfigurationManager.NEVER;
    private int ALWAYS = MockConfigurationManager.ALWAYS;

//    private volatile boolean updatingServerSocket;
    
    private boolean useAmavisSMTPDirectory;

    private boolean authenticated;

    private boolean forceExitRCPT;

    private VerifyIP verifyIP;

    private MockStreamHandler smtpSH = new MockStreamHandler();

    private String[] instanceAuthMech;

//    private DigestMd5ServerMode digestMd5ServerMode;
//    private GSSServerMode gssServerMode;

//    private TransferMode transferMode = new TransferMode();
    
	private int lastCommand;
	
	private int maxPassAttempts;
	private int passAttempts;
	private boolean isconnected;

    //***************************************************************
    // Public Interface
    //***************************************************************

    public SMTPProcessor() {
    	setupVerifyIP();
    	this.setSocket(new MockServerSocket());
		this.lastCommand = NONE;
	}

    /**
     * Sets the socket used to communicate with the client.
     */
    private void setSocket( MockServerSocket serverSocket ) {

        this.serverSocket = serverSocket;
        
//        while( running ) {
        isconnected = false;
        authenticated = false;
        is8bitMIMESupported = configurationManager.is8bitMIME();
        isHELOEnabled = configurationManager.isHELOEnabled();
        maxPassAttempts = configurationManager.getMaxPassAttempts();
        isESMTP = true;
        isRejected = false;
        errorcount = 0;
//            digestMd5ServerMode = new DigestMd5ServerMode(true);
        StringBuilder sb = new StringBuilder(30);
        if (configurationManager.isGSSEnabled()) {
           sb.append(AUTH_MECH[0]);
           sb.append(",");
        }
        if (configurationManager.isDigestMD5Enabled()) {
           sb.append(AUTH_MECH[1]);
           sb.append(",");
        }
        sb.append(AUTH_MECH[2]);sb.append(",");sb.append(AUTH_MECH[3]);
        instanceAuthMech = sb.toString().split(",");
        
        try {
            socket = serverSocket.accept();
//                isSecure = serverSocket.getLocalPort()==configurationManager.getSecureSMTPPort() ? true :
//                	configurationManager.isStandardSMTPSecure() ? false : true;
            isEncrypted = serverSocket.getLocalPort()==configurationManager.getSecureSMTPPort()?true:false;
            isconnected = true;

            //Prepare the input and output streams.
            MockInetAddress remoteAddress = null;
            smtpSH.setStreams(socket);

            remoteAddress = socket.getInetAddress();
            
            clientIp = remoteAddress.getHostAddress();

            //Output the welcome/reject message.
            if (verifyIP.blockIP(clientIp)) {
               write( REJECT_MESSAGE, 0);
               isRejected = true;
            }
            else {
               write( WELCOME_MESSAGE , 0);
            }

            //Initialize the input message.
            resetMessage();

        } catch (MockIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MockTooManyErrorsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

//    private boolean isUpdatingServerSocket() {
//        return updatingServerSocket;
//    }
//
//    public void setUpdatingServerSocket(boolean value) {
//    	updatingServerSocket = value;
//    }

//    public void setUseAmavisSMTPDirectory() {
//      useAmavisSMTPDirectory = false;
//    }

    private void setupVerifyIP() {
       verifyIP = MockVerifyIPFactory.getNewVerifyIPInstance(!configurationManager.isVerifyIP());
    }

    /**
     * Entrypoint for the Thread, this method handles the interaction with
     * the client socket.
     * @throws MockIOException 
     */
    private void run() throws MockIOException {
    	boolean forcedexit = false;
        try
        {
	        //Parses the input for commands and delegates to the appropriate methods.
	        forcedexit = handleCommands();
        }
        catch (MockTooManyErrorsException e) {
        }
        catch ( SocketTimeoutException e ) {
           if (isconnected) {
              forcedexit = true;
           }
        }
        catch ( MockIOException e ) {
//               if (running) {
//               }
           if (socket!=null) {
              if (!socket.isClosed()) {
                 forcedexit = true;
              }
              else {
                 isconnected = false;
              }
           }
        }
        //If any exception gets to here uncaught, it means we should just disconnect.
        catch( Throwable e ) {
           e.printStackTrace();
           isconnected = false;
        }
//        finally {
////               digestMd5ServerMode.conclude();
//           if( socket != null && isconnected) {
//                 try {
//                    if (!forcedexit) {
//                       write( MESSAGE_DISCONNECT , 0 );
//                    }
//                    else {
//                       if (!forceExitRCPT) {
//                          write ( FORCED_EXIT_MESSAGE, 0 );
//                       }
//                       else {
//                          write ( MESSAGE_EXCESS_FAIL_RCPT_DISCONNECT, 0);
//                       }
//                    }
//                 }
//                 catch (MockTooManyErrorsException ex) {}
//                 catch (MockIOException ioe) {}
//           }
////           try {
////              if( socket != null ) {
////                 socket.close();
////              }
////           }
////           catch( MockIOException e ) {
////              //Nothing to do.
////           }
////           finally {
////              socket = null;
////           }
////           message = null;
////           smtpSH = new MockStreamHandler();
////               if (gssServerMode!=null) gssServerMode.conclude();
//        }
//            while (isUpdatingServerSocket()) {
//               try {
//                  Thread.sleep(750);
//               }
//               catch (InterruptedException e) {}
//               if (!running) break;
//            }
//        }
    }

    /**
     * Notifies this thread to stop processing and exit.
     * @throws MockIOException 
     */
    private void shutdown() throws MockIOException {
//        running = false;
        if (!isFinishedData) {
           if (socket!=null) {
              try {
                 socket.close();
              } catch (MockIOException ex) {}
           }
        }
    }

   /** Message specific variables */
   private MockSMTPMessage message;
   private int validRCPT, failedRCPT;
   private boolean tooManyRCPT, excessRCPT;
   private boolean receivedMAILFROM;
   private boolean receivedRCPTTO;
   private boolean singleRCPT;
//   private byte[] output;
//   private int finalBufferSize;
   private boolean endOfMessage;
   private boolean isMessage8bitMIME;
   private MockAddDataLineDefault addDataLine;
   
   private void setAddDataLine(MockAddDataLineDefault addDataLine) {
      this.addDataLine = addDataLine;
   }

   private void resetMessage() {
      message = new MockSMTPMessage();
//      output = null;
//      finalBufferSize = 32;
//      mimeBody = new MIMEBody(null);
      endOfMessage = false;
      isMessage8bitMIME = false;
      receivedMAILFROM = false;
      receivedRCPTTO = false;
      singleRCPT = false;
      validRCPT = 0;
      failedRCPT = 0;
      tooManyRCPT = false;
      excessRCPT = false;
      forceExitRCPT = false;
      isFinishedData = false;
      addDataLine = new MockAddDataLineDefault(message);
      passAttempts = 0;
      setAddDataLine(addDataLine);
   }

    /**
     * Checks to make sure that the incoming command is not a quit.  If so,
     * the connection is terminated.
     */
    private boolean checkQuit( String command ) {
        if( command.equals( COMMAND_QUIT ) ) {
            return true;
        }
        return false;
    }
    
    public void verify(String argument) throws MockSocketException, Exception {
		try {
			verify0(argument);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void verify0(String argument) throws MockSocketException, Exception {
		if (!verifiy_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_VRFY + " " + argument);
		run();
	}
	
	public void auth(String argument) throws MockSocketException, Exception {
		try {
			auth0(argument);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void auth0(String argument) throws MockSocketException, Exception {
		if (!auth_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_AUTH + " " + argument);
		run();
	}
	
    public void helo(String argument) throws MockSocketException, Exception {
		try {
			helo0(argument);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void helo0(String argument) throws MockSocketException, Exception {
		if (!helo_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_HELO + " " + argument);
		run();
	}
	
	public void ehlo(String argument) throws MockSocketException, Exception {
		try {
			ehlo0(argument);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void ehlo0(String argument) throws MockSocketException, Exception {
		if (!ehlo_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_EHLO + " " + argument);
		run();
	}
	
	public void noop() throws MockSocketException, Exception {
		try {
			noop0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void noop0() throws MockSocketException, Exception {
		if (!noop_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_NOOP);
		run();
	}
	
	public void reset() throws MockSocketException, Exception {
		try {
			reset0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void reset0() throws MockSocketException, Exception {
		if (!reset_pre()) {
			throw new IllegalStateException();
		}

		enqueueLineToInputStream(COMMAND_RSET);
		run();
	}

	public void mail(String emailAddress) throws MockSocketException, Exception {
		try {
			mail0(emailAddress);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void mail0(String emailAddress) throws MockSocketException, Exception {
		if (!mail_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_MAIL_FROM + " FROM:" + emailAddress);
		run();
	}

	public void rcpt(String emailAddress) throws MockSocketException, Exception {
		try {
			rcpt0(emailAddress);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void rcpt0(String emailAddress) throws MockSocketException, Exception {
		if (!rcpt_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_RCPT_TO + " TO:" + emailAddress);
		run();
	}

	public void data(String data) throws MockSocketException, Exception {
		try {
			data0(data);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void data0(String data) throws MockSocketException, Exception {
		if (!data_pre()) {
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
	
	public void quit() throws MockSocketException, Exception {
		try {
			quit0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void quit0() throws MockSocketException, Exception {
		if (!quit_pre()) {
			throw new IllegalStateException();
		}
		enqueueLineToInputStream(COMMAND_QUIT);
		run();
	}
	
	private void enqueueLineToInputStream(String lineStr) throws MockSocketException, Exception {
		if (this.socket.isClosed()) {
			throw new MockSocketException("Connection is closed");
		}
		smtpSH.write(lineStr.getBytes());
	}
	
	private boolean verifiy_pre() {
		return socket != null && !socket.isClosed();
	}
	
	private boolean auth_pre() {
		return socket != null && !socket.isClosed() && !authenticated && passAttempts<maxPassAttempts ;
	}
	
	private boolean ehlo_pre() {
		return socket != null && !socket.isClosed();
	}
	
	private boolean helo_pre() {
		return socket != null && !socket.isClosed() && isHELOEnabled;
	}
	
	private boolean noop_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean reset_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean quit_pre() {
		return socket != null && !socket.isClosed();
	}

	private boolean mail_pre() {
		return socket != null && !socket.isClosed()
				&& ((lastCommand == HELO && isHELOEnabled) || lastCommand == EHLO || lastCommand == NONE || lastCommand == RSET || lastCommand == VRFY || lastCommand == NOOP);
	}

	private boolean rcpt_pre() {
		return socket != null && !socket.isClosed() && receivedMAILFROM && ( lastCommand == MAIL_FROM || lastCommand == RCPT_TO || lastCommand == NONE || lastCommand == EHLO || lastCommand == VRFY || lastCommand == NOOP);
	}

	private boolean data_pre() {
		return socket != null && !socket.isClosed() && (lastCommand == RCPT_TO && receivedRCPTTO && receivedMAILFROM/* && message.getToAddresses().size() > 0*/);
	}

    /**
     * Handles all the commands related the the sending of mail.
     * @throws MockInvalidAddressException 
     */
    private boolean handleCommands() throws MockTooManyErrorsException,
          SocketTimeoutException, SocketException, MockIOException, MockInvalidAddressException {

        //Reusable Variables.
        String inputString;
        String command;
        String argument;

//        int lastCommand = NONE;
//        passAttempts = 0;

//        while( true ) {
        inputString = read();

        if (forceExitRCPT) return true;

        command = parseCommand( inputString );
        argument = parseArgument( inputString );
        if (command == null) {
           write ( MESSAGE_INVALID_COMMAND, 1 );
        }
        else if (checkQuit(command)) {
           return false;
        }
        else if (isRejected) {
           write( MESSAGE_COMMAND_ORDER_INVALID, 1);
           
        }
        else if (command.equals( COMMAND_VRFY ) ) {
            write( MESSAGE_NO_VRFY_SUPPORT, 0 );
            lastCommand = VRFY;
        }
        //NOOP - Do Nothing.
        else if( command.equals( COMMAND_NOOP ) ) {
            write( MESSAGE_OK, 0 );
            lastCommand = NOOP;
        }
        //Resets the state of the server back to the initial state.
        else if( command.equals( COMMAND_RSET ) ) {
          write( MESSAGE_OK, 0 );
            lastCommand = RSET;
            resetMessage();
        }
        else if( command.equals( COMMAND_AUTH ) ) {
           if (authenticated) {
              write( MESSAGE_ALREADY_AUTHENTICATED, 1 );
           }
           else {
              if (maxPassAttempts!=0) {
                 passAttempts++;
                 if (passAttempts>maxPassAttempts) {
                    write( MESSAGE_AUTH_FAILED, 1 );
                 }
              }
              String[] authBreakDown = argument.split(" ");
              String mechanism = authBreakDown[0].toUpperCase();
              String clientResponse = null;
              if (authBreakDown.length>1) {
                 clientResponse = authBreakDown[1];
              }
              boolean contains = false;
              int mechCount = instanceAuthMech.length-(allowClearText==ALWAYS?0:(allowClearText==NEVER?2:(isEncrypted?0:2)));
              for (int i=0;i<mechCount;i++) {
                 if (instanceAuthMech[i].contains(mechanism)) {
                    contains = true;
                    break;
                 }
              }
              if (contains) {
                 if (mechanism.equals("PLAIN")) {
                    MockPlainServerMode plainServerMode = new MockPlainServerMode(true);
                    if (clientResponse==null) {
                       write ( MESSAGE_INTERMEDIATE, 0 );
                       clientResponse = read();
                    }
                    try {
                       if (clientResponse.equals("*")) throw new MockAuthenticationException("Client cancelled authentication process");
                       byte[] rspauth = plainServerMode.evaluateResponse(clientResponse.getBytes());
                       if (rspauth == null) {
                          write ( MESSAGE_AUTH_FAILED, 1 );
                       }
                       else {
                          authenticated = true;
                          write ( MESSAGE_AUTH_SUCCESS+new String(rspauth), 0 );
                       }
                    }
                    catch (MockAuthenticationException ae) {
                       write ( MESSAGE_AUTH_CANCELLED+"Authentication cancelled", 1 );
                    }
                    catch (MockSaslException ex) {
                       if (ex.getCause()!=null) {
//                          if (ex.getCause() instanceof MalformedBase64ContentException) {
//                             write ( MESSAGE_AUTH_CANCELLED+ex.getMessage(), 1 );
//                          }
//                          else {
                             write ( MESSAGE_AUTH_FAILED, 1 );
//                          }
                       }
                       else {
                          write ( MESSAGE_AUTH_FAILED, 1 );
                       }
                    }
                    plainServerMode.conclude();
                 }
              }
              else {
                 write ( MESSAGE_UNRECOGNIZED_AUTH_MECH, 1 );
              }
           }
        }
        else if( command.equals( COMMAND_EHLO ) ) {
           declaredHelloHostname = argument;
            write( "250-Hello " + declaredHelloHostname, 0 );
//                if (!isSecure) {
//                   write( "250-STARTTLS", 0 );
//                }
            if (is8bitMIMESupported) {
               write( "250-8BITMIME ", 0 );
            }
                StringBuilder auth_mech = new StringBuilder();
                int mechCount = instanceAuthMech.length-(allowClearText==ALWAYS?0:(allowClearText==NEVER?2:(isEncrypted?0:2)));
                if (mechCount>0) {
                   for (int i=0;i<mechCount;i++) {
                      auth_mech.append(" ").append(instanceAuthMech[i]);
                   }
                   write ("250-AUTH"+auth_mech.toString(), 0 );
                }
            write( "250 SIZE "+(configurationManager.getMaximumMessageSize() * 1024 * 1024), 0  );
            lastCommand = EHLO;
        }
        else if( command.equals( COMMAND_HELO ) ) {
           if (isHELOEnabled) {
               isESMTP = false;
               write( "250 Hello " + argument, 0 );
           }
           else {
               write ( MESSAGE_NO_HELO_ACCEPTED, 1 );
           }
           lastCommand = HELO;
        }
        //Not only check the command, but the full string, since the prepare command
        //method only returns the text before the first string, and this is a two
        //word command.
        else if( command.equals( COMMAND_MAIL_FROM ) && inputString.toUpperCase().startsWith( "MAIL FROM:" ) ) {

            if( (lastCommand == HELO && isHELOEnabled) || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO || lastCommand == VRFY || lastCommand == NOOP) {
                try {
					if( handleMailFrom( inputString ) ) {
					    lastCommand = MAIL_FROM;
					    receivedMAILFROM = true;
					}
                } catch (MockInvalidAddressException iae) {
//            			log.debug("Unable to parse From Address: " + fromAddress);
        			write(MESSAGE_USER_INVALID, 1);
        			return false;
        		}
            }
            else {
                write( MESSAGE_COMMAND_ORDER_INVALID, 1 );
            }
        }
        //Not only check the command, but the full string, since the prepare command
        //method only returns the text before the first string, and this is a two
        //word command.
        else if( command.equals( COMMAND_RCPT_TO ) && inputString.toUpperCase().startsWith( "RCPT TO:" ) ) {

            if(receivedMAILFROM && ( lastCommand == MAIL_FROM || lastCommand == RCPT_TO || lastCommand == NONE || lastCommand == EHLO || lastCommand == VRFY || lastCommand == NOOP)) {
               if (excessRCPT) {
                  write( MESSAGE_EXCESS_RCPT , 0 );
               }
               else if (tooManyRCPT) {
                  write( MESSAGE_TOO_MANY_RCPT , 0 );
               }
               else {
                  handleRcptTo( inputString );
                  lastCommand = RCPT_TO;
                  receivedRCPTTO = true;
                  if(!singleRCPT) {
                     singleRCPT = true;
                  }
                  else {
                     singleRCPT = false;
                  }
               }
            }
            else {
                write( MESSAGE_COMMAND_ORDER_INVALID, 1 );
            }
        }
        else if( command.equals( COMMAND_DATA ) ) {

            if( receivedMAILFROM && receivedRCPTTO && lastCommand == RCPT_TO && message.getToAddresses().size() > 0) {
                handleData();
                lastCommand = DATA;
                // Reset for another message
                resetMessage();
            }
            else if (message.getToAddresses().size() == 0) {
               write( MESSAGE_NO_VALID_RCPT, 1 );
            }
            else {
                write( MESSAGE_COMMAND_ORDER_INVALID, 1 );
            }
        }
        else {
           if (isFinishedData && !isESMTP && message.getSize()>configurationManager.getMaximumMessageSize() * 1024 * 1024) {
              return !checkQuit(command);
           }
           write( MESSAGE_INVALID_COMMAND + command, 1 );
        }
        return false;
//        }
    }

   private void handleRcptTo(String inputString) throws MockTooManyErrorsException, MockIOException, MockInvalidAddressException {

      String toAddress = parseAddress( inputString.substring( inputString.indexOf(":") ).trim() );

      try {
         MockEmailAddress address = new MockEmailAddress( toAddress );
         handleRcptTo(address);
      }
      catch( MockInvalidAddressException iae ) {
         write( MESSAGE_USER_INVALID, 1 );
         failedRCPT++;
         rcptPolicyActions((MockEmailAddress)null);
         return;
      }
   }
    
    private boolean handleMailFrom(String inputString) throws MockTooManyErrorsException, MockIOException, MockInvalidAddressException {

        handleMailFrom0(inputString);
        return handleMailFrom1(inputString);
     }

    private void handleMailFrom0(String fromAddress) throws MockInvalidAddressException{

        //It is legal for the MAIL FROM address to be empty.
        if( fromAddress == null || fromAddress.trim().equals( "" ) ) {
           fromaddress = new MockEmailAddress();
        }
        //Although this is the normal case...
        else {
           fromaddress = new MockEmailAddress( fromAddress );
        }
     }

    private boolean handleMailFrom1(String inputString) throws MockTooManyErrorsException, MockIOException {

        String[] list = inputString.toUpperCase().substring(inputString.lastIndexOf(">")+1).split(" ");
        for (int i=0;i<list.length;i++) {
           if (list[i].contains("SIZE")) {
              try {
                 int size = Integer.parseInt(list[i].substring(list[i].indexOf("=")+1));
                 if (size > (configurationManager.getMaximumMessageSize() * 1024 * 1024)) {
                    write( MESSAGE_MESSAGE_TOO_LARGE, 0 );
                    return false;
                 }
              }
              catch(NumberFormatException nfe){}
           }
           else if (is8bitMIMESupported && list[i].indexOf("8BITMIME")!=-1) {
              message.set8bitMIME(isMessage8bitMIME = true);
           }
        }
        message.setFromAddress( fromaddress );
        write( MESSAGE_OK, 0 );
        return true;
     }

     private void handleRcptTo( MockEmailAddress address ) throws MockTooManyErrorsException, MockIOException, MockInvalidAddressException {

        handleRcptTo0(address);
     }

     private void handleRcptTo0( MockEmailAddress address ) throws MockTooManyErrorsException, MockIOException, MockInvalidAddressException{

        //Check the address to see if we can deliver it.
        MockDeliveryService deliveryService = MockDeliveryService.getDeliveryService();
        if( authenticated || deliveryService.acceptAddress( address, clientIp, message.getFromAddress() ) ) {

           // Check to see if it is a local user.
           if( configurationManager.getUser( address ) == null ) {

              if ( configurationManager.isLocalDomain( address.getDomain() ) &&
              	configurationManager.isNonExistentLocalRejected() ) {                	
                 throw new MockInvalidAddressException();
              }
           }

           //Perhaps someone is faking a local user in the MAIL FROM command.
           //If so, do not offer a permanent negative reply when checking the MAIL FROM
           //commands but instead reject the RCPT TO commands.
           //If the faked user is a true registered user then the recipient is rejected if:
           //a)there was no successful smtp authentication or
           //b)POPBeforeSMTP is inactive or
           //c)POPBeforeSMTP is active and the user didn't successfully logon using POP3.
           //If the faked local user doesn't exist reject the RCPT TO commands forthwith.
           //This way no information about local users is offered directly since it is
           //impossible to relate the rejection to the existence of a local user.
           //Complemented by the proper rejection of credentials during a POP or SMTP
           //authentication process (that is reject the credentials as a whole and not
           //individually), this approach constitutes a considerable obstacle to anyone
           //attempting to use JES as a spam relay or trying to take control of user
           //accounts.
           if (configurationManager.isLocalDomain(fromaddress.getDomain())) {
              if (configurationManager.getUser( fromaddress )==null ||
                    (!deliveryService.acceptAddress( null, clientIp, message.getFromAddress()) && !authenticated)) {
                 throw new MockInvalidAddressException();
              }
           }
           write( MESSAGE_OK, 0 );
           validRCPT++;
           if (rcptPolicyActions(address)) {

              message.addToAddress( address );

           }
        }
        else {
           write ( MESSAGE_USER_INVALID, 1 );
           failedRCPT++;
           rcptPolicyActions(address);
        }
     }


    private boolean rcptPolicyActions(MockEmailAddress toAddress) throws MockIOException, MockTooManyErrorsException{
       if (validRCPT>configurationManager.getMaxValidRCPT()) {
          tooManyRCPT = true;
          if (((validRCPT+failedRCPT)*100/validRCPT)>100+configurationManager.getAddPctRCPT()) {
             excessRCPT = true;
          }
       }
       if (failedRCPT>configurationManager.getMinTotFailRCPT() && failedRCPT*100/(failedRCPT+validRCPT)>configurationManager.getMinPctFailRCPT()) {
          forceExitRCPT = true;
       }
       if (toAddress==null) return true;
       String senderDomain = message.getFromAddress().getDomain().toLowerCase();
       if(!configurationManager.isLocalDomain(senderDomain)) return true;
//       String rcptDomain = toAddress.getDomain().toLowerCase();
//       RcptPolicyArray<?> domain = (RcptPolicyArray<?>)configurationManager.getRcptPolicyMap().get(senderDomain);
//       RcptPolicyArray<?> global = (RcptPolicyArray<?>)configurationManager.getRcptPolicyMap().get("#####");
//       if (domain!=null&&(domain.get(0).equals("ALL")||domain.contains(rcptDomain))) {
//          if (!domain.isAllow()) {
//             //Reject the rcpt domain
//             write( MESSAGE_RCPT_DOMAIN_REJECTED, 1 );
//             return false;
//          }
//       }
//       else {
//          if (global.get(0).equals("ALL")||global.contains(rcptDomain)) {
//             if (!global.isAllow()) {
//                //Reject the rcpt domain
//                write( MESSAGE_RCPT_DOMAIN_REJECTED, 1 );
//                return false;
//             }
//          }
//       }
       return true;

    }

    /**
     * Accepts the data being written to the socket.
     */
    private void handleData() throws MockTooManyErrorsException, SocketTimeoutException,
          SocketException, MockIOException{
       
        // Get the current maxSize setting and convert to bytes.
        long maxSize = configurationManager.getMaximumMessageSize() * 1024 * 1024;

        write( MESSAGE_SEND_DATA, 0 );

        verifyIP.saveBegin(message, useAmavisSMTPDirectory);

        // Add a "Received:" line as trace information per rfc 2821/4.4
        addDataLine.addDataLine(("Received: from " + declaredHelloHostname + " (["+ clientIp + "])").getBytes());
//        if (isSecure &&
//              ((configurationManager.isSecureActive() && serverSocket.getLocalPort()==configurationManager.getSecureSMTPPort()) ||
//                 (configurationManager.isStandardSMTPSecure() && serverSocket.getLocalPort()==configurationManager.getSMTPPort()))){
//           StringBuilder secureString = new StringBuilder("        (using ");
////           secureString.append(socket).getSession().getProtocol().append(" protocol with ");
////           secureString.append(socket).getSession().getCipherSuite().append(" ciphersuite.)");
//           addDataLine.addDataLine(secureString.toString().getBytes());
//        }

        String domain = ((MockEmailAddress)message.getToAddresses().get(0)).getDomain();
//        setDomain(domain);

        addDataLine.addDataLine(("        by " + domain + " with " +
              (isESMTP?"E":"")+"SMTP id <REPLACE-ID>"+(singleRCPT?"":";")).getBytes() );

        addDataLine.addDataLine(((singleRCPT?"        for <REPLACE-RCPT>; ":"        ")+
              new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(message.getTimeReceived())).getBytes());
                
        readOnceInputStream();
        if (!endOfMessage) {
           for (;;) {
              readInputStream();
              if (stopIfTooBig(maxSize)) break;
              if (endOfMessage)break;
           }
        }
        isFinishedData = true;
        try {
            if (endOfMessage) {
               addDataLine.flush();
               if (!message.getSMTPPersistenceProccessor().saveFinish()) {
                  throw new Exception();
               }
               write( MESSAGE_OK, 0 );
               
            }
            else {
               if (isESMTP || message.getSize() <= maxSize) {
                  throw new Exception();
               }
            }
        }
        catch ( Exception e ) {
           e.printStackTrace();
            write( MESSAGE_SAVE_MESSAGE_ERROR, 0 );
        }
    }

    private boolean stopIfTooBig(long maxSize) throws MockTooManyErrorsException, MockIOException{
        // Check message size
        if( message.getSize() > maxSize ) {
           if (isESMTP) {
              write( MESSAGE_MESSAGE_TOO_LARGE, 0 );
           }
           return true;
        }
        return false;
    }

   private void readOnceInputStream() {
//      int currentRead = -1;
//      byte[] buffer = new byte[finalBufferSize+1];
//      currentRead = smtpSH.read(buffer, 0, finalBufferSize);
//      if (currentRead == -1 ) return;
//      currentRead = removeInitialNull(buffer, currentRead);
//      
//      constructLineOfText(currentRead, buffer);
   }

   private void readInputStream() throws MockIOException{
	   this.endOfMessage = true;
   }

//   private void constructLineOfText(int currentRead, byte[] buffer) throws MockIOException {
//
//      int nextByte = -1, previousRead = 0;
//      for (int i=0;i<currentRead;i++) {
//         if (buffer[i] == 0x0d || buffer[i] == 0x0a) {
//            if (i+1 == currentRead) {
//               nextByte = smtpSH.read();
//               if (nextByte != -1) {
//                  buffer[i+1] = (byte)nextByte;
//                   i = constructLineOfText3(buffer, true, previousRead, i);
//                   if (endOfMessage) return;
//               }
//               //Perhaps a truncated end of DATA transmission. Check if it so.
//               else if (output==null && i-previousRead == 1 && buffer[previousRead]==0x2E) {
//                  endOfMessage = true;
//                  return;
//               }
//               break;
//            }
//            else {
//               i = constructLineOfText3(buffer, false, previousRead, i);
//               if (endOfMessage) return;
//               previousRead = i+1;
//            }
//         }
//         if (i==currentRead-1) {
//            output = constructLineOfText2(output,buffer,previousRead,currentRead);
//            finalBufferSize*=2;
//         }
//      }
//   }
//
//   private byte[] constructLineOfText2(byte[] output, byte[] buffer, int startSegmentCount, int currentSegmentCount) {
//
//      if (output!=null) {
//         int tempOutputLength;
//         byte[] tempOutput;
//         tempOutputLength = output.length;
//         tempOutput = new byte[tempOutputLength];
//         System.arraycopy(output, 0, tempOutput, 0, tempOutputLength);
//         output = new byte[tempOutputLength+currentSegmentCount-startSegmentCount];
//         System.arraycopy(tempOutput, 0, output, 0, tempOutputLength);
//         System.arraycopy(buffer, startSegmentCount, output, tempOutputLength, currentSegmentCount-startSegmentCount);
//      }
//      else {
//         output = new byte[currentSegmentCount-startSegmentCount];
//         System.arraycopy(buffer, startSegmentCount, output, 0, currentSegmentCount-startSegmentCount);
//      }
//      return output;
//   }
//
//   private int constructLineOfText3(byte[] buffer, boolean increment, int previousRead, int i) throws MockIOException {
//      
//      if (buffer[i+1] == 0x0a) {
//         i++;
//         output = constructLineOfText2(output,buffer,previousRead,i-1);
//         int outputLength = output.length;
//         if (outputLength>16&&outputLength<=128) finalBufferSize = outputLength+2;
//         if (endOfMessage = checkEndOfDATA(output)) return -1;
////         processDATA(output);
//         if (buffer[i-1] == 0x0a) {
////            processDATA(new byte[0]);
//         }
//         output = null;
//      }
//      else {
//         if (increment) i++;
//         if (buffer[i] == 0x0a && output!=null && output[output.length-1] == 0x0d) {
//            output = constructLineOfText2(output,output,0,output.length);
////            processDATA(output);
//            output = null;
//         }
//         else {
//            output = constructLineOfText2(output,buffer,previousRead,i+1);
//         }
//      }
//      return i;
//   }
//   
//   protected boolean checkEndOfDATA(byte[] output) throws MockIOException{
//	      if (output.length==1 && output[0]==0x2E) {
////	         if (mimeBody.mime==MIME_TEXT && mimeBody.base64EncodeText) {
////	            mimeBody.base64EncodeText = false;
////	            encodeBase64();
////	         }
//	         return true;
//	      }
//	      return false;
//	   }
//
//   private int removeInitialNull(byte[] input, int currentRead) {
//
//      if (input[0]>=0x20) return currentRead;
//      int length = input.length-1, count = 1;
//      for (;count<length;count++) {
//         if (input[count]>=0x20) break;
//      }
//      int newCount=count;
//      for (;newCount<length;newCount++) {
//         input[newCount-count] = input[newCount];
//      }
//      newCount = length-count;
//      for (;newCount<length;newCount++) {
//         input[newCount] = 0x00;
//      }
//
//      return length-count;
//   }

    /**
     * Reads a line from the input stream and returns it.
     */
    private String read() throws SocketException, SocketTimeoutException, MockIOException{

       try {
          socket.setSoTimeout( 5 * 60 * 1000);
          String inputLine = smtpSH.readLine();
          return inputLine;
       }
       catch (NullPointerException npe) {
          return null;
       }
    }

    /**
     * Writes the specified output message to the client.
     */
    private void write( String message, int errorIncrement) throws MockTooManyErrorsException, MockIOException {
       errorcount += errorIncrement;
       if (errorcount>=maxerrorcount) throw new MockTooManyErrorsException();
       if (message!=null) {
          smtpSH.print(message);
       }
    }

    /**
     * Parses the input stream for the command.  The command is the
     * begining of the input stream to the first space.  If there is
     * space found, the entire input string is returned.
     * <p>
     * This method converts the returned command to uppercase to allow
     * for easier comparison.
     * <p>
     * Additinally, this method checks to verify that the quit command
     * was not issued.  If it was, a null String is returned to terminate
     * the connection.
     */
    private String parseCommand( String inputString ) {

        if (inputString == null) return null;
        int index = inputString.indexOf( " " );
        String command = index == -1 ?
              inputString.toUpperCase():inputString.substring( 0, index ).toUpperCase();
        return command;
    }

    /**
     * Parses the input stream for the argument.  The argument is the
     * text starting afer the first space until the end of the inputstring.
     * If there is no space found, an empty string is returned.
     * <p>
     * This method does not convert the case of the argument.
     */
    private String parseArgument( String inputString ) {

        if (inputString == null) return null;
        int index = inputString.indexOf( " " );
        return index == -1 ? "":inputString.substring( index + 1 );
    }

    /**
     * Parses an address argument into a real email address.  This
     * method strips off any &gt; or &lt; symbols.
     */
    private String parseAddress( String address ) {

        int index = address.indexOf( "<" );
        if( index != -1 ) {
            address = address.substring( index + 1 );
        }
        index = address.indexOf( ">" );
        if( index != -1 ) {
            address = address.substring( 0, index );
        }
        address = address.substring(address.indexOf(':')+1);
        return address;
    }

    //***************************************************************
    // Constants
    //***************************************************************

    //Message Constants
    //General Message
    private static final String WELCOME_MESSAGE = "220 ESMTP Server";
    private static final String REJECT_MESSAGE = "554 The connecting IP has been identified as a source of unsolicited mail";
    private static final String FORCED_EXIT_MESSAGE = "421 Service not available, closing transmission channel";
    private static final String MESSAGE_DISCONNECT = "221 ESMTP server signing off";
    private static final String MESSAGE_OK = "250 OK";
    private static final String MESSAGE_COMMAND_ORDER_INVALID = "503 Bad sequence of commands";
    private static final String MESSAGE_USER_INVALID = "550 Requested action not taken: mailbox unavailable";
    private static final String MESSAGE_RCPT_DOMAIN_REJECTED = "550 Recipient Domain not accepted; a policy restriction is in place";
    private static final String MESSAGE_SEND_DATA = "354 Start mail input; end with <CRLF>.<CRLF>";
    private static final String MESSAGE_SAVE_MESSAGE_ERROR = "451 Requested action aborted: local error in processing";
    private static final String MESSAGE_INVALID_COMMAND = "500 Command Unrecognized: ";
    private static final String MESSAGE_MESSAGE_TOO_LARGE = "552 Message size exceeds fixed maximum message size";
    private static final String MESSAGE_NO_VRFY_SUPPORT = "252 Cannot VRFY user, but will accept message and attempt delivery";
    private static final String MESSAGE_NO_VALID_RCPT = "554 no valid recipients given";
    private static final String MESSAGE_FAILED_TRANSACTION = "554 Transaction Failed. ";
    private static final String MESSAGE_NO_HELO_ACCEPTED = "554 Active Security Context rejects the HELO command";

    private static final String MESSAGE_AUTH_SUCCESS = "235 Authentication successful";
    private static final String MESSAGE_INTERMEDIATE = "334 ";
    private static final String MESSAGE_ALREADY_AUTHENTICATED = "503 Already authenticated";
    private static final String MESSAGE_UNRECOGNIZED_AUTH_MECH = "504 Unrecognized authentication type";
    private static final String MESSAGE_AUTH_FAILED = "535 Authentication credentials invalid";
    private static final String MESSAGE_AUTH_CANCELLED = "501 ";
    private static final String MESSAGE_AUTH_FAILED_CUSTOM = "535 ";

    private static final String MESSAGE_TOO_MANY_RCPT = "452 Too many recipients";
    private static final String MESSAGE_EXCESS_RCPT = "503 Security policy: Excessive number of recipients";
    private static final String MESSAGE_EXCESS_FAIL_RCPT_DISCONNECT = "421 Security policy: Excessive number of failed recipients";

    //Commands
    private static final String COMMAND_EHLO = "EHLO";
    private static final String COMMAND_HELO = "HELO";
    private static final String COMMAND_RSET = "RSET";
    private static final String COMMAND_NOOP = "NOOP";
    private static final String COMMAND_QUIT = "QUIT";
    private static final String COMMAND_MAIL_FROM = "MAIL";
    private static final String COMMAND_RCPT_TO = "RCPT";
    private static final String COMMAND_DATA = "DATA";
    private static final String COMMAND_VRFY = "VRFY";
    private static final String COMMAND_STLS = "STARTTLS";
    private static final String COMMAND_AUTH = "AUTH";

    //SMTP Commands
    private static final int NONE = 0;
    private static final int EHLO = 1;
    private static final int HELO = 2;
    private static final int QUIT = 3;
    private static final int MAIL_FROM = 4;
    private static final int RCPT_TO = 5;
    private static final int DATA = 6;
    private static final int DATA_FINISHED = 7;
    private static final int RSET = 8;
    private static final int STLS = 9;
    private static final int VRFY = 10;
    private static final int NOOP = 11;
    private static final int AUTH = 12;

    private static final String[] AUTH_MECH = new String[] {"GSSAPI", "DIGEST-MD5", "LOGIN", "PLAIN"};
}