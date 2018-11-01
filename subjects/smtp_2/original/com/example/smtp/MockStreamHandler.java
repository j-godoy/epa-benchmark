package com.example.smtp;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Each smtp/pop3 session hands control of its streams and socket to an instance
 * of this class.
 *
 * @author Andreas Kyrmegalos
 */
public class MockStreamHandler {

   private OutputStream outputStream;
   private BufferedInputStream inputStream;
   private BufferedReader inputReader;
   
   private final Queue<String> lines = new LinkedList<String>();

   private OutputStream activeOutputStream;
   private InputStream activeInputStream;


   public MockStreamHandler() {
   }

   public void setStreams(MockSocket socket) throws IOException {
      if (outputStream==null) {
         outputStream = socket.getOutputStream();
         activeOutputStream = new BufferedOutputStream(outputStream,4096);
      }
      if (inputStream==null) {
         inputStream = new BufferedInputStream(socket.getInputStream());
         inputReader = new BufferedReader(new InputStreamReader( inputStream, US_ASCII ));
         activeInputStream = inputStream;
      }
   }


   public String readLine() throws MockIOException {
	   if (lines.isEmpty()) {
			throw new MockIOException();
		}
		return lines.poll();
   }

   public void write(byte[] line) throws IOException{
      if (line == null)
    	  throw new NullPointerException();
		this.lines.add(new String(line));
   }

   private static final String US_ASCII = "US-ASCII";

	public void print(String message) {
		// TODO Auto-generated method stub
	}
}
