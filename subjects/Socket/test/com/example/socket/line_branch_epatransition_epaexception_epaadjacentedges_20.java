/*
 * This file was automatically generated by EvoSuite
 * Sat Jul 07 10:57:33 GMT 2018
 */

package com.example.socket;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.socket.MockEnvironment;
import com.example.socket.MockInet4Address;
import com.example.socket.MockInet6Address;
import com.example.socket.MockInetAddress;
import com.example.socket.MockInetSocketAddress;
import com.example.socket.MockSocketAddress;
import com.example.socket.Socket;
import java.io.ObjectStreamConstants;
import java.net.SocketException;

public class line_branch_epatransition_epaexception_epaadjacentedges_20 {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Mewp%qSd}1v//2H,m", 1);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress("o", 0);
      int int0 = ObjectStreamConstants.PROTOCOL_VERSION_2;
      socket0.shutdownInput();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket input is already shutdown
         //
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      mockEnvironment0.pushIOException(true);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 950);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 1);
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
  public void test02()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1276));
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2065);
      mockEnvironment0.pushIOException(true);
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
  public void test03()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInet6Address mockInet6Address0 = new MockInet6Address("^m");
      mockEnvironment0.isBlackListed((MockInetAddress) mockInet6Address0, 4083);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-1763));
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 0);
      Socket socket0 = new Socket(mockEnvironment0);
      Socket socket1 = new Socket(mockEnvironment0);
      MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 4083);
      socket0.bind(mockInetSocketAddress1);
      socket1.bind(mockInetSocketAddress1);
      socket0.connect((MockSocketAddress) mockInetSocketAddress1);
      try {
        socket1.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) null, 4141);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 3097);
     //assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet4Address mockInet4Address0 = new MockInet4Address("tQaH5");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 1707);
      MockInetAddress.getLocalHost();
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      mockEnvironment0.shouldThrowIOException();
      MockInetAddress.getLocalHost();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
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
  public void test06()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(true);
      MockInet4Address mockInet4Address0 = new MockInet4Address("connect: timeout can't be negative");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 1718);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      MockInetAddress.anyLocalAddress();
      MockInetAddress.getLocalHost();
      new MockEnvironment();
      mockEnvironment0.shouldThrowIOException();
      mockEnvironment0.pushIOException(true);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-45));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1525);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 712);
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
  public void test08()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetAddress mockInetAddress0 = new MockInetAddress("q5SFvlM1N(|B@A?H");
      mockEnvironment0.isBlackListed(mockInetAddress0, 2224);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("^:oN1i{ARwRZo[", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      mockEnvironment0.pushIOException(true);
      socket0.close();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-3302));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
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
  public void test10()  throws Throwable  {
      Socket socket0 = new Socket();
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
  public void test11()  throws Throwable  {
      Socket socket0 = new Socket();
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

  @Test//(timeout = 4000)
  public void test12()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-3843));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
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
  public void test14()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.close();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 31);
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
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
     //assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.bind((MockSocketAddress) null);
      socket0.close();
      socket0.close();
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      new MockEnvironment();
      MockEnvironment mockEnvironment1 = new MockEnvironment();
      mockEnvironment0.shouldThrowIOException();
      new MockEnvironment();
      new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1160);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 0);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      mockEnvironment1.isBlackListed((MockInetAddress) mockInet6Address0, 0);
      Socket socket1 = new Socket(mockEnvironment0);
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
  public void test17()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("GGBGyiH)", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.getInputStream();
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
  public void test18()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
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
  public void test19()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("yU6xmwkgy", 65535);
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
  public void test20()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Delay should be non negative", 1096);
      mockEnvironment0.addToBlackList(mockInetSocketAddress0);
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
  public void test21()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetAddress mockInetAddress0 = new MockInetAddress("Delay should be non negative");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(mockInetAddress0, 1931);
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
  public void test22()  throws Throwable  {
      Socket socket0 = new Socket();
      String string0 = "";
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
  public void test23()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
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
  public void test24()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("d", 26);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownInput();
      socket0.shutdownOutput();
      MockInet6Address mockInet6Address0 = new MockInet6Address("[~5yBDtS[");
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-263));
      try {
        socket0.bind(mockInetSocketAddress1);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Already bound
         //
      }
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(false);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      socket0.getInputStream();
      socket0.getOutputStream();
      Socket socket1 = new Socket();
      try {
        socket1.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      int int0 = 1192;
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }
}