/*
 * This file was automatically generated by EvoSuite
 * Fri Aug 10 17:24:31 GMT 2018
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
import com.example.socket.Pair;
import com.example.socket.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

public class Test_Find_Mutants_Def {

  @Test(timeout = 1300)
  public void test00()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(": invalid address type", 2259);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2259);
      mockEnvironment0.pushIOException(true);
      int int1 = socket0.shutdownOutput();
      assertFalse(int1 == int0);
      assertEquals(1, int1);
  }

  @Test(timeout = 1300)
  public void test01()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", 0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 4993);
      assertEquals(1, int0);
  }

  @Test(timeout = 1300)
  public void test02()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-864));
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      assertEquals(1, int0);
  }

  @Test(timeout = 1300)
  public void test03()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3016);
      Socket socket0 = new Socket(mockEnvironment0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 3016);
      mockEnvironment0.pushIOException(true);
      int int1 = socket0.close();
      assertFalse(int1 == int0);
      assertEquals(1, int1);
  }

  @Test(timeout = 1300)
  public void test04()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.anyLocalAddress();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 31);
      int int0 = socket0.bind(mockInetSocketAddress0);
      assertEquals(1, int0);
  }

  @Test(timeout = 1300)
  public void test05()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      Socket socket0 = new Socket(mockEnvironment0);
      mockEnvironment0.pushIOException(false);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      try {
        socket0.getInputStream();
        fail("Expecting exception: ClassCastException");

      } catch(ClassCastException e) {
         //
         // com.example.socket.MockIOException cannot be cast to java.io.IOException
         //
      }
  }

  @Test(timeout = 1300)
  public void test06()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", (-82));
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 46);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test07()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-3068));
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 918);
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: IOException");

      } catch(IOException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test08()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(722);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 722);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // already connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test09()  throws Throwable  {
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-3068));
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 918);
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: IOException");

      } catch(IOException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test10()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.getLoopbackAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 0);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test11()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2240));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 369);
      int int0 = socket0.shutdownOutput();
      assertTrue(int0 == 0);
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket output is already shutdown
         //
      }
  }

  @Test(timeout = 1300)
  public void test12()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test13()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      socket0.shutdownInput();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket input is already shutdown
         //
      }
  }

  @Test(timeout = 1300)
  public void test14()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test15()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInet4Address mockInet4Address0 = new MockInet4Address("#(8n");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 65527);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownOutput();
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket output is shutdown
         //
      }
  }

  @Test(timeout = 1300)
  public void test16()  throws Throwable  {
      Socket socket0 = new Socket();
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test17()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.getOutputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test18()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(768);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      socket0.shutdownInput();
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket input is shutdown
         //
      }
  }

  @Test(timeout = 1300)
  public void test19()  throws Throwable  {
      Socket socket0 = new Socket();
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test20()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      try {
        socket0.getInputStream();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test21()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetAddress mockInetAddress0 = new MockInetAddress("rjA6SEmGcPNs*a^");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(mockInetAddress0, 742);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // bind: invalid address type
         //
      }
  }

  @Test(timeout = 1300)
  public void test22()  throws Throwable  {
      Socket socket0 = new Socket((MockEnvironment) null);
      MockInet4Address mockInet4Address0 = new MockInet4Address((String) null);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0, 1165);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: NullPointerException");

      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test23()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) null, (-1775));
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 1300)
  public void test24()  throws Throwable  {
      Socket socket0 = new Socket();
      int int0 = socket0.bind((MockSocketAddress) null);
      assertEquals(0, int0);
  }

  @Test(timeout = 1300)
  public void test25()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("k]\u0000(3l'dxom?lm", 2662);
      socket0.bind(mockInetSocketAddress0);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Already bound
         //
      }
  }

  @Test(timeout = 1300)
  public void test26()  throws Throwable  {
      Socket socket0 = new Socket();
      socket0.close();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test27()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(768);
      int int0 = socket0.bind(mockInetSocketAddress0);
      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      assertTrue(int1 == int0);
      assertEquals(0, int1);
  }

  @Test(timeout = 1300)
  public void test28()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("", (-1));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // already connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test29()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(": invalid address type", 2259);
      socket0.close();
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 742);
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is closed
         //
      }
  }

  @Test(timeout = 1300)
  public void test30()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-15));
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-15));
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // connect: timeout can't be negative
         //
      }
  }

  @Test(timeout = 1300)
  public void test31()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.connect((MockSocketAddress) null);
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // connect: The address can't be null
         //
      }
  }

  @Test(timeout = 1300)
  public void test32()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      try {
        socket0.shutdownOutput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test33()  throws Throwable  {
      Socket socket0 = new Socket();
      try {
        socket0.shutdownInput();
        fail("Expecting exception: SocketException");

      } catch(SocketException e) {
         //
         // Socket is not connected
         //
      }
  }

  @Test(timeout = 1300)
  public void test34()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-864));
      try {
        socket0.bind(mockInetSocketAddress0);
        fail("Expecting exception: IOException");

      } catch(IOException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }
  
  @Test(timeout = 1300)
  public void test22_1()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetAddress mockInetAddress0 = new MockInetAddress("6<PO~X;1%5d1|9@Bm");
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(mockInetAddress0, 1459);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
        fail("Expecting exception: IllegalArgumentException");

      } catch(IllegalArgumentException e) {
         //
         // connect: invalid address type
         //
      }
  }


  @Test(timeout = 1300)
  public void test35()  throws Throwable  {
      Socket socket0 = new Socket();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-3068));
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      Pair<Integer, OutputStream> pair0 = socket0.getOutputStream();
      assertNotNull(pair0);
  }

  @Test(timeout = 1300)
  public void test36()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      Pair<Integer, InputStream> pair0 = socket0.getInputStream();
      assertNotNull(pair0);
  }
}