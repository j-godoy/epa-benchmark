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
import java.io.IOException;
import java.io.ObjectStreamConstants;
import java.net.SocketException;

public class Line_Branch_Epatransition_Epaexception_EpaAdjacentEdges_0 {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      mockEnvironment0.pushIOException(false);
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.shouldThrowIOException();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress(211);
      mockEnvironment0.isBlackListed(mockInetSocketAddress1);
      Socket socket0 = new Socket(mockEnvironment0);
      Socket socket1 = new Socket(mockEnvironment0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
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
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(17);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownInput();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress("SocketImpl.connect(addr, timeout)", 3162);
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
  public void test02()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.shouldThrowIOException();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(398);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet6Address mockInet6Address0 = new MockInet6Address("q w!K=c2_!#ei~");
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 65514);
      socket0.bind(mockInetSocketAddress1);
      socket0.close();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      mockEnvironment0.pushIOException(false);
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.shouldThrowIOException();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress(31);
      mockEnvironment0.isBlackListed(mockInetSocketAddress1);
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      Integer integer0 = new Integer(0);
      socket0.shutdownInput();
      Socket socket1 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(true);
      Socket socket2 = new Socket(mockEnvironment0);
      socket0.close();
      socket0.close();
      Socket socket3 = new Socket(mockEnvironment0);
      Socket socket4 = new Socket(mockEnvironment0);
      socket1.close();
      Socket socket5 = new Socket(mockEnvironment0);
      MockEnvironment mockEnvironment1 = new MockEnvironment();
     //assertFalse(mockEnvironment1.equals((Object)mockEnvironment0));
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet4Address mockInet4Address0 = new MockInet4Address("Socket input is shutdown");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 4009);
      socket0.bind(mockInetSocketAddress0);
      socket0.close();
      new MockEnvironment();
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
  public void test05()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress("pr*WHrE;<QM", (-3556));
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      MockInetSocketAddress mockInetSocketAddress2 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 0);
      mockEnvironment0.addToBlackList(mockInetSocketAddress2);
      mockEnvironment0.isBlackListed(mockInetSocketAddress1);
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
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 1507);
      int int0 = socket0.bind(mockInetSocketAddress0);
     //assertEquals(0, int0);

      mockEnvironment0.pushIOException(true);
      int int1 = socket0.close();
      Socket socket1 = new Socket(mockEnvironment0);
      Socket socket2 = new Socket(mockEnvironment0);
      int int2 = socket0.close();
     //assertFalse(int2 == int1);
     //assertEquals(0, int2);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(256);
      MockInet6Address mockInet6Address0 = new MockInet6Address((String) null);
      MockInetAddress.anyLocalAddress();
      mockEnvironment0.isBlackListed((MockInetAddress) mockInet6Address0, 2922);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.shouldThrowIOException();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 256);
      socket0.shutdownOutput();
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
  public void test08()  throws Throwable  {
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
  public void test09()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
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
  public void test10()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet6Address mockInet6Address0 = new MockInet6Address("YSVX%UJ`#za}!-/q");
      int int0 = (-36);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-36));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownOutput();
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
  public void test11()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet4Address mockInet4Address0 = new MockInet4Address("|fOh(");
      mockEnvironment0.pushIOException(true);
      MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 2139);
      socket0.bind(mockInetSocketAddress0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
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
  public void test12()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      mockEnvironment0.pushIOException(false);
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.shouldThrowIOException();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress(31);
      mockEnvironment0.isBlackListed(mockInetSocketAddress1);
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.close();
      socket0.shutdownInput();
      Socket socket1 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(true);
      Socket socket2 = new Socket(mockEnvironment0);
      socket0.close();
      socket0.close();
      Socket socket3 = new Socket(mockEnvironment0);
      Socket socket4 = new Socket(mockEnvironment0);
      socket1.close();
      Socket socket5 = new Socket(mockEnvironment0);
      MockEnvironment mockEnvironment1 = new MockEnvironment();
     //assertFalse(mockEnvironment1.equals((Object)mockEnvironment0));
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 1);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.anyLocalAddress();
      mockEnvironment0.isBlackListed((MockInetAddress) mockInet6Address0, 2782);
      new MockEnvironment();
      socket0.close();
      Socket socket1 = new Socket(mockEnvironment0);
      socket1.bind(mockInetSocketAddress0);
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
  public void test14()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet4Address mockInet4Address0 = new MockInet4Address("57JE");
      MockInetAddress.getLocalHost();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 0);
      MockInetAddress.anyLocalAddress();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.close();
      MockInetAddress.anyLocalAddress();
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
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.shouldThrowIOException();
      mockEnvironment0.pushIOException(false);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      Socket socket1 = new Socket(mockEnvironment0);
      socket0.getInputStream();
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
  public void test16()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      Socket socket1 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("3h9=#", (-155));
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      int int1 = socket0.close();
     //assertFalse(int1 == int0);
     //assertEquals(0, int1);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 679);
      socket0.getInputStream();
      socket0.getOutputStream();
      int int1 = socket0.shutdownInput();
      Socket socket1 = new Socket();
      int int2 = socket0.shutdownOutput();
     //assertTrue(int2 == int0);

      int int3 = socket0.close();
     //assertTrue(int3 == int1);
     //assertEquals(0, int3);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.shouldThrowIOException();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(144);
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.bind(mockInetSocketAddress0);
      socket0.close();
      MockEnvironment mockEnvironment1 = new MockEnvironment();
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
  public void test19()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetAddress.getLoopbackAddress();
      int int0 = socket0.bind((MockSocketAddress) null);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1277));
      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
     //assertEquals(0, int1);

      int int2 = socket0.close();
     //assertTrue(int2 == int0);
     //assertEquals(0, int2);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 800);
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 800);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: IOException");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
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
  public void test22()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
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
  public void test23()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) null, (-426));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      MockSocketAddress mockSocketAddress0 = null;
      int int0 = 274;
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
  public void test24()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.addToBlackList((MockSocketAddress) null);
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.bind((MockSocketAddress) null);
      Socket socket1 = new Socket(mockEnvironment0);
      socket0.close();
      try {
        socket1.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      MockInetAddress.anyLocalAddress();
      MockInetAddress.getLocalHost();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(883);
      socket0.bind(mockInetSocketAddress0);
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 883);
      MockInetAddress.getLocalHost();
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
  public void test26()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, (-2246));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      new MockEnvironment();
      int int0 = ObjectStreamConstants.PROTOCOL_VERSION_1;
      mockEnvironment0.pushIOException(true);
      socket0.shutdownOutput();
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
  public void test27()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      int int0 = 2994;
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2994);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2994);
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
  public void test28()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Unresolved address", 0);
      MockInet6Address mockInet6Address0 = new MockInet6Address("Unresolved address");
      MockInetAddress.getLocalHost();
      mockEnvironment0.isBlackListed((MockInetAddress) mockInet6Address0, (-736));
      Socket socket1 = new Socket(mockEnvironment0);
      Socket socket2 = new Socket(mockEnvironment0);
      socket1.connect((MockSocketAddress) mockInetSocketAddress0, 83);
      socket1.shutdownInput();
      socket1.getOutputStream();
      socket1.close();
      Socket socket3 = new Socket(mockEnvironment0);
      try {
        socket1.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 1);
      socket0.bind(mockInetSocketAddress0);
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
  public void test30()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Already bound", 3654);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      try {
        socket0.bind((MockSocketAddress) null);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Already bound
         //
      }
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Unsupported address type", 17);
      socket0.bind(mockInetSocketAddress0);
      try {
        socket0.connect((MockSocketAddress) null, 17);
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: The address can't be null
         //
      }
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      int int0 = ObjectStreamConstants.baseWireHandle;
      socket0.close();
      socket0.close();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      Socket socket0 = new Socket();
      MockSocketAddress mockSocketAddress0 = null;
      socket0.bind((MockSocketAddress) null);
      try {
        socket0.bind((MockSocketAddress) null);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Already bound
         //
      }
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("connect: The address can't be null", 1825);
      socket0.bind(mockInetSocketAddress0);
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
  public void test35()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("connect: The address can't be null", 65535);
      socket0.bind(mockInetSocketAddress0);
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
  public void test36()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetAddress.getLocalHost();
      MockEnvironment mockEnvironment1 = new MockEnvironment();
      mockEnvironment0.addToBlackList((MockSocketAddress) null);
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.close();
      mockEnvironment1.shouldThrowIOException();
      mockEnvironment1.addToBlackList((MockSocketAddress) null);
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
  public void test37()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetAddress mockInetAddress0 = new MockInetAddress("(c\fjH|L>M?Oxh:");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(mockInetAddress0, (-548));
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLocalHost();
      MockInetAddress.getLocalHost();
      mockEnvironment0.isBlackListed((MockInetAddress) mockInet6Address0, (-580));
      mockEnvironment0.pushIOException(true);
      MockEnvironment mockEnvironment1 = new MockEnvironment();
      mockEnvironment0.addToBlackList((MockSocketAddress) null);
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(true);
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress("(c\fjH|L>M?Oxh:", (-580));
      mockEnvironment1.shouldThrowIOException();
      mockEnvironment1.addToBlackList((MockSocketAddress) null);
      mockEnvironment1.isBlackListed(mockInetSocketAddress1);
      socket0.bind((MockSocketAddress) null);
      socket0.close();
      Socket socket1 = new Socket(mockEnvironment1);
      socket1.connect((MockSocketAddress) mockInetSocketAddress1, 1);
      socket1.close();
      socket0.close();
      Socket socket2 = new Socket(mockEnvironment1);
      socket1.close();
      try {
        socket2.bind(mockInetSocketAddress0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // bind: invalid address type
         //
      }
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-6341));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
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
  public void test39()  throws Throwable  {
      MockEnvironment mockEnvironment0 = null;
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) null, 65545);
      Socket socket0 = new Socket((MockEnvironment) null);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: NullPointerException");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test40()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
      socket0.close();
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test41()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = null;
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.connect((MockSocketAddress) null, 0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: The address can't be null
         //
      }
  }

  @Test(timeout = 4000)
  public void test42()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(845);
      socket0.bind(mockInetSocketAddress0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2139);
      socket0.shutdownInput();
      socket0.close();
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket1 = new Socket(mockEnvironment0);
      socket0.close();
      socket1.bind(mockInetSocketAddress0);
      mockEnvironment0.pushIOException(true);
      socket1.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      try {
        socket1.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 4000)
  public void test43()  throws Throwable  {
      MockEnvironment mockEnvironment0 = null;
      Socket socket0 = new Socket((MockEnvironment) null);
      socket0.close();
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
  public void test44()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(65514);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 65514);
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
  public void test45()  throws Throwable  {
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

  @Test(timeout = 4000)
  public void test46()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1528));
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: IOException");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test47()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(256);
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 256);
      int int0 = ObjectStreamConstants.PROTOCOL_VERSION_2;
      Socket socket0 = new Socket(mockEnvironment0);
      new MockEnvironment();
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      mockEnvironment0.pushIOException(true);
      socket0.close();
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
  public void test48()  throws Throwable  {
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
  public void test49()  throws Throwable  {
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
  public void test50()  throws Throwable  {
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
  public void test51()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      socket0.bind(mockInetSocketAddress0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 679);
      socket0.getInputStream();
      socket0.getOutputStream();
      socket0.shutdownOutput();
      socket0.shutdownInput();
      Socket socket1 = new Socket();
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
  public void test52()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("@.J>R", (-459));
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-459));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 4000)
  public void test53()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet4Address mockInet4Address0 = new MockInet4Address("|fOh(");
      MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 2139);
      int int0 = socket0.bind(mockInetSocketAddress0);
     //assertEquals(0, int0);

      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      socket0.getOutputStream();
      socket0.getOutputStream();
      int int2 = ObjectStreamConstants.PROTOCOL_VERSION_2;
      int int3 = socket0.close();
     //assertTrue(int3 == int1);
     //assertEquals(0, int3);
  }

  @Test(timeout = 4000)
  public void test54()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(777);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      mockEnvironment0.shouldThrowIOException();
      Socket socket0 = new Socket(mockEnvironment0);
      socket0.bind(mockInetSocketAddress0);
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
  public void test55()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      socket0.shutdownInput();
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress("", 0);
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
  public void test56()  throws Throwable  {
      Socket socket0 = new Socket();
      int int0 = (-1345);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("J3ASg`kBA!)S", (-1345));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.getOutputStream();
      socket0.getInputStream();
      socket0.getInputStream();
      socket0.shutdownInput();
      socket0.getOutputStream();
      socket0.shutdownOutput();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // already connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test57()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("Socket output is already shutdown", 0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      socket0.getOutputStream();
      Integer integer0 = new Integer(0);
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
  public void test58()  throws Throwable  {
      Socket socket0 = new Socket();
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
  public void test59()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(700);
      socket0.bind(mockInetSocketAddress0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 700);
      socket0.shutdownOutput();
      socket0.getInputStream();
      socket0.getInputStream();
      int int0 = 2235;
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: SocketException");

      } catch(Exception e) {
         //
         // already connected
         //
      }
  }

  @Test(timeout = 4000)
  public void test60()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-9));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.getInputStream();
      Socket socket1 = new Socket((MockEnvironment) null);
      socket0.shutdownOutput();
      socket0.getInputStream();
      socket0.close();
      socket0.close();
      try {
        socket1.bind((MockSocketAddress) null);
        fail("Expecting exception: NullPointerException");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }
  
  @Test//(timeout = 4000)
  public void test62()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1));
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }
  
  @Test//(timeout = 4000)
  public void test10_0()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 1447);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      socket0.shutdownInput();
      socket0.shutdownOutput();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
//    	  e.printStackTrace();
         //
         // connect: timeout can't be negative
         //
      }
  }
  
  @Test//(timeout = 4000)
  public void test19_test_regression_fail()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("{CZe0m**DNWe", (-2213));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      mockEnvironment0.isBlackListed(mockInetSocketAddress0);
      socket0.getOutputStream();
      socket0.shutdownInput();
      socket0.shutdownOutput();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
        fail("Expecting exception: IllegalArgumentException");

      } catch(Exception e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  
}