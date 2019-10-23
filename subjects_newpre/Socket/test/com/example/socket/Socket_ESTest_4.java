package com.example.socket;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.socket.MockEnvironment;
import com.example.socket.MockInet4Address;
import com.example.socket.MockInet6Address;
import com.example.socket.MockInetAddress;
import com.example.socket.MockInetSocketAddress;
import com.example.socket.MockSocketAddress;
import com.example.socket.Pair;
import com.example.socket.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class Socket_ESTest_4 {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      // Undeclared exception!
      try {
        socket0.getOutputStream();
        fail("Expecting exception: ClassCastException");

      } catch(Exception e) {
         //
         // com.example.socket.MockIOException cannot be cast to java.io.IOException
         //
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      // Undeclared exception!
      try {
        socket0.getInputStream();
        fail("Expecting exception: ClassCastException");

      } catch(Exception e) {
         //
         // com.example.socket.MockIOException cannot be cast to java.io.IOException
         //
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      int int0 = socket0.close();
     //assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 123);
      Socket socket0 = new Socket();
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      int int1 = socket0.shutdownOutput();
     //assertEquals(0, int1);

      int int2 = socket0.shutdownInput();
     //assertTrue(int2 == int0);
     //assertEquals(0, int2);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 123);
      Socket socket0 = new Socket();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownOutput();
      Pair<Integer, InputStream> pair0 = socket0.getInputStream();
     //assertNotNull(pair0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLocalHost();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-17));
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
     //assertEquals(0, int0);

      int int1 = socket0.shutdownOutput();
      int int2 = socket0.close();
     //assertTrue(int2 == int1);
     //assertEquals(0, int2);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4127);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 4127);
     //assertEquals(0, int0);

      int int1 = socket0.shutdownInput();
      int int2 = socket0.shutdownOutput();
     //assertTrue(int2 == int1);
     //assertEquals(0, int2);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      socket0.close();
      socket0.shutdownInput();
      Pair<Integer, OutputStream> pair0 = socket0.getOutputStream();
     //assertNotNull(pair0);
    // Invalid EPA Transition: [S3,close,S3]
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("V%e9\u0004", 0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      int int1 = socket0.shutdownOutput();
      int int2 = socket0.shutdownInput();
     //assertFalse(int2 == int1);

      int int3 = socket0.close();
     //assertTrue(int3 == int0);
     //assertEquals(0, int3);
    // Invalid EPA Transition: [S3,shutdownOutput,S3]
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Ve9\u0004", 65535);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 65535);
      mockEnvironment0.pushIOException(true);
      int int1 = socket0.shutdownInput();
     //assertEquals(1, int1);

      int int2 = socket0.close();
     //assertTrue(int2 == int0);
     //assertEquals(0, int2);
    // Invalid EPA Transition: [S3,shutdownInput,S3]
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(true);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2197));
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      mockEnvironment0.pushIOException(true);
      int int1 = socket0.close();
     //assertTrue(int1 == int0);
     //assertEquals(1, int1);
    // Invalid EPA Transition: [S1,close,S1]
    // Invalid EPA Transition: [S1,connect,S1]
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("B", 0);
      int int0 = socket0.bind(mockInetSocketAddress0);
     //assertEquals(1, int0);
    // Invalid EPA Transition: [S1,bind,S1]
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      socket0.close();
      socket0.shutdownInput();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket input is already shutdown
         //
      }
    // Invalid EPA Transition: [S3,close,S3]
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4127);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 4127);
      socket0.shutdownOutput();
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket output is shutdown
         //
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.close();
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.close();
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) null, 66);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 66);
      socket0.shutdownOutput();
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket output is already shutdown
         //
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Socket socket0 = new Socket();
      int int0 = socket0.bind((MockSocketAddress) null);
      int int1 = socket0.close();
     //assertTrue(int1 == int0);
     //assertEquals(0, int1);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet4Address mockInet4Address0 = new MockInet4Address("@5=<f*+[JL(-q^");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 4127);
      socket0.bind(mockInetSocketAddress0);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Already bound
         //
      }
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.bind((MockSocketAddress) null);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 123);
      Socket socket0 = new Socket();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // already connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("dQxy", 295);
      mockEnvironment0.pushIOException(true);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 295);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLocalHost();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-17));
      // Undeclared exception!
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-17));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      Socket socket0 = new Socket();
      // Undeclared exception!
      try {
        socket0.connect((MockSocketAddress) null);
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: The address can't be null
         //
      }
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet4Address mockInet4Address0 = new MockInet4Address("@5=<f*+[JL(-q^");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 4127);
      int int0 = socket0.bind(mockInetSocketAddress0);
      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 4127);
     //assertTrue(int1 == int0);
     //assertEquals(0, int1);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      socket0.shutdownInput();
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket input is shutdown
         //
      }
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      Socket socket0 = new Socket();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetAddress mockInetAddress0 = new MockInetAddress("5C$ds`/Fv|O>6d");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(mockInetAddress0, 7);
      Socket socket0 = new Socket(mockEnvironment0);
      // Undeclared exception!
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // bind: invalid address type
         //
      }
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      Pair<Integer, OutputStream> pair0 = socket0.getOutputStream();
     //assertNotNull(pair0);
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("$e+\u0004", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      Pair<Integer, InputStream> pair0 = socket0.getInputStream();
     //assertNotNull(pair0);
  }
}
