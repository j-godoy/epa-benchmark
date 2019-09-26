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

public class Socket_ESTest_5
{
	  public void test011()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2561);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2561);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test11()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(275);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 275);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test21()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2537);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 2537);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test31()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2039);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1732));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test411()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2039);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test01()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(745);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownOutput();
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1_42()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(745);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test215dfds()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1223));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test3we34() throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1223));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4s()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(681);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5svx()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(681);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test6sdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(681);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test7add()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(990);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 990);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8safgsd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0csdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(293);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 293);
	     //assertTrue(int2 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertTrue(int3 == int1);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S2,close,S2]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test1ñs()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(293);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 293);
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownInput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S2,close,S2]
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test23423()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(261);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null, 0);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test3sfds()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(293);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 293);
	     //assertEquals(0, int2);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.close();
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S2,close,S2]
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test41()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(518);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 518);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test5sdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(518);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,bind,S1]
	}

	  public void test0sdqwwf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2520);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1sdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2520);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test2ssqdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(539);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3bc()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(539);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4cvb()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(539);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5cvbc()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1214);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-4183));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6cvb()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1214);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7cvbc()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-4125));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8vbc()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0wer()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(223);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 223);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);

	      socket0.shutdownOutput();
	      int int2 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test1fdg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(223);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 223);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	      int int2 = socket0.shutdownOutput();
	      int int3 = socket0.shutdownInput();
	     //assertFalse(int3 == int2);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.close();
	     //assertTrue(int4 == int1);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test2dfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2094);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3jk()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-4770));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test4basda()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(223);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,bind,S1]
	  }

	  public void test0hjg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1449);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test1ty()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1449);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test2try()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1449);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3erwe()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(97);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test4234()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(97);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test5345hgf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2451));
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test6fgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0ghj()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3194);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 3194);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1kg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3194);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 3194);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test2ghj()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4984);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test3jkl()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-5112));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4klñ()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1722);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5klñ()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1722);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test6nb()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2082));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test7hfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1857);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-803));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8vbn()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1857);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0tr()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-65));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1rty()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-65));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void lkfdl()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-37));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3234fd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-27));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4dfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-27));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5dfd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1718));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6wrwe()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7ghfd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null, 1);
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8dfgd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0234()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-802));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	      socket0.shutdownInput();
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownInput();
	     //assertFalse(int3 == int0);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1bdgf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1784));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test2gjhkg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-490));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test3ghj()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-490));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(true);
	      socket0.close();
	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4tyu()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null);
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test5fgdng()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0dfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-32));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 45);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1345()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test2456()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4176);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3fsdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4176);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4gfd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4176);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5dfw()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(707);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1301));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void te2st1261()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(707);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test723423()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-67));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8sdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(721);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0sdf()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1877));
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdf()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1877));
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test2sdf()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1877));
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3sdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(643);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test4fgd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(26);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-26));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test5fdgd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(26);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0werwerwer()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1752));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1werweerteghrghf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1788));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test2hfgfghfghf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1797));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	      int int2 = socket0.shutdownInput();
	     //assertTrue(int2 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertTrue(int3 == int1);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test3ghfgfjhvb()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1862));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testbvbvcvcbvc4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1862));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testdfgdfgdfgdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1797));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      socket0.close();
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testdfgdfgdhg6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(62);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-2521));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testhjyt7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(62);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void testyuaospdasoktesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testtyu1()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testutyut5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3wrgfhr()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(38);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 38);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testfghs4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testshs5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6sdfghhhhhhhhhhhhhhhhhhhhhh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void testssssssss7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1333);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1333);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testhsdfhs8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1333);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,bind,S1]
	  }

	  public void test0sdfhsd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void test_4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1841);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testfgsdfgdsfgs2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-4903));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 81);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3sdfgsdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1247);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4sdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1238);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5dfga()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      Integer integer0 = new Integer(0);
	      int int1 = socket0.connect((MockSocketAddress) null, (int) integer0);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void testdsfgs6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1835);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7sdgfs()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1212);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1912));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8twrthg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1212);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void test0fsfdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-879));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test1hgsfgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-879));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testhsdfh2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-773));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3sfgsdf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-773));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testhdsgs4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-773));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5sdfhs()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6qwerq()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(434);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testqwr7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3941);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdpasokctesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3451);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-559));
	     //assertEquals(1, int1);

	      int int2 = socket0.close();
	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertFalse(int3 == int2);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.shutdownOutput();
	     //assertTrue(int4 == int0);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdfñsdkjf1()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3451);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);

	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-559));
	      int int2 = socket0.close();
	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertFalse(int3 == int2);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.shutdownInput();
	     //assertTrue(int4 == int1);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test//(timeout = 4000)
	  public void test_5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3451);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-559));
	      int int2 = socket0.close();
	     //assertTrue(int2 == int0);

	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertFalse(int3 == int1);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.close();
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test31adas()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null, 0);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test4dasa11()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  public void tesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-554));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdsakdpotesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-554));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testssqawok2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-739));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test311()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(12);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test4wsqw()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(47);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1731));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test5qweqwe()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(47);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void testesasdasadasd23123()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(65492);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);

	      int int1 = socket0.close();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
	     //assertTrue(int2 == int1);

	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 65492);
	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.shutdownOutput();
	     //assertFalse(int4 == int3);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testdsfpotesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(65492);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
	     //assertEquals(1, int2);

	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 65492);
	     //assertFalse(int3 == int1);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.shutdownInput();
	     //assertTrue(int4 == int0);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test//(timeout = 4000)
	  public void test2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(65492);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1));
	      int int3 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 65492);
	     //assertFalse(int3 == int2);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.close();
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test
	  public void testesasdasadasdew()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1619);
	      int int1 = socket0.close();
	      System.out.println(int1);
	      mockEnvironment0.pushIOException(true);
	      try
	      {
	    	  int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, -1);
	    	  fail();
	      }
	      catch(Exception e)
	      {
	    	  
	      }
//	      mockEnvironment0.pushIOException(true);
//	      mockEnvironment0.pushIOException(true);
//	      mockEnvironment0.pushIOException(true);
//	      mockEnvironment0.pushIOException(true);
	     //assertEquals(0, int1);

	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdfptesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testqwkqowke2()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2290);
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertFalse(int2 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertTrue(int3 == int1);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3sda()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2290);
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertFalse(int2 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownInput();
	     //assertTrue(int3 == int1);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4sadsffg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(682);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test5werwe()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2036));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test6bfdfg()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2290);
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);

	      int int2 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int2);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.close();
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  public void tesaspdaoitesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1723));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdfpñ()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1723));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsoadkxoc2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(16);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3gfrer()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-11));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4erte()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(16);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5ytuiy()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(27);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1211));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6yui()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7yuiy()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(29);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1703));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8yuiyu()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(29);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void teszdftesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2035);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2035);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsadosdpaswk2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-182));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3yuiyu()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-182));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4hjd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-182));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5dhjg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null);
	     //assertEquals(1, int1);

	      int int2 = socket0.close();
	      int int3 = socket0.bind((MockSocketAddress) null);
	     //assertFalse(int3 == int2);

	      mockEnvironment0.pushIOException(true);
	      int int4 = socket0.connect((MockSocketAddress) null, 0);
	     //assertTrue(int4 == int0);
	     //assertEquals(1, int4);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void test6djd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null);
	      socket0.close();
	      int int2 = socket0.bind((MockSocketAddress) null);
	     //assertFalse(int2 == int1);

	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.close();
	     //assertTrue(int3 == int0);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  public void tessdfstesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(5823);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void testfsañlsktesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(5823);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testsodapwoq2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(5798);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testdjdjdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(860);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void tesdfhdf4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(5798);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void tffghsfgsfest5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null, 0);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void tfsfhgfffest6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void teghgfsfgfghfgt7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4858);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1445));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8ffsffsf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4858);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdaftesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1085);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdoaspoitesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1085);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdapok2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1085);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testfsgh3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(43);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1034));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void testjhddfgs4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(43);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void tessdgsdt5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(58);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1502));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void tesfghjt6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(58);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }
	  
	  public void tesftesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-213));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(1, int0);

	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testposapdoktesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-213));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	     //assertFalse(int1 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdasdasdpo2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-213));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	     //assertFalse(int1 == int0);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S1,connect,S1]
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testhddfgh3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessadasdtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(576);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void test_3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(576);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsasdñsadpa2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(576);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testfg3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(576);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void teritytrt4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(576);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testfghfgfghj5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1691);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-17));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void tedghjghjt6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1691);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void testghjh7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null);
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void tesdghjt8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessadghjgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1068);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void testapsodixctesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2943));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testsadñasdlsad2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1085);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void teshdjddt3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2982));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void tesjdfgdfg()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2977));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void tesdfdfgt5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(600);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-2293));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testjfghfg6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(600);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tesdsfslñtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2431);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdpañslkxcltesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2431);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdañsdñasdoap2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2423);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3gfdre()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-244));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4ryrty()  throws Throwable  {
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1309);
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1309);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(true);
	      socket0.close();
	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test5rtyrw()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(167);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null, 1);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void testwytr6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(167);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7rtyw()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-635));
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testrwrty8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,bind,S1]
	  }

	  public void tesdasrtyrt()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1612);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1612);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testkspdaomaqtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(198);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 198);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void testssdasadpqqcsx2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(198);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 198);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void tesqertqet3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(159);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 159);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test4qerqt()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1612);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1612);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testerte5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(159);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 159);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testwert()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null, 1);
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void testwer7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void testfhd8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 1);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,close,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test9()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	     //assertEquals(1, int0);
	    // Invalid EPA Transition: [S1,bind,S1]
	  }

	  public void testesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsadoitesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownOutput();
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdasñsad2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void testdfghfd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4dfgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testgjd5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test6dghfgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-2595));
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void test7fgf()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.connect((MockSocketAddress) null, 1);
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testfghd8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdasdtsadfesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-80));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 43);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testsdpasoktesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-80));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 43);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testasdkwqoksads2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1946);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3GD()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1946);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testASDFASD4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(31);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 31);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsddgSFfs5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(3052);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-640));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void testsdf6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2109);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7GDAG()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1122);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-686));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testfs8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1122);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessadaltesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(487);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testskadsptesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testasdakdsasod2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-17));
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testhsfgh3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1158);
	      Socket socket0 = new Socket(mockEnvironment0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.close();
	     //assertEquals(1, int1);

	      int int2 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownInput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testdhgj4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2752);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-516));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test5dhmhs()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2752);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdasdtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(42);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownInput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testspoacvktesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(42);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void testaskdfdfzs2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-11));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void teshsht3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-11));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4sfghgfs()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5fgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(42);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsfgh6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(42);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testfhgasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(703);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-344));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void sadfsakj2() throws Throwable {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(703);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdkzjcxtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4658);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownInput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	    // Invalid EPA Transition: [S5,shutdownInput,S5]
	  }

	  @Test(timeout = 4000)
	  public void testspaosdtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(47);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test1213vasdksad2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(47);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      int int1 = socket0.shutdownInput();
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testdgsdfg3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4658);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.shutdownOutput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.close();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	    // Invalid EPA Transition: [S5,close,S5]
	  }

	  @Test(timeout = 4000)
	  public void tesfgt4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(61);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test5dfghdfgh()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(61);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void tesdfghfgh6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.connect((MockSocketAddress) null, 1);
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void testgfjuyudfg7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      int int0 = socket0.bind((MockSocketAddress) null);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdasoaptesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1927));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.shutdownOutput();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void testzsodkpotesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1927));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	     //assertEquals(0, int0);

	      int int1 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int1);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void test12psdao2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(647);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdf3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(647);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void testhytjy4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1882));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,close,S3]
	  }

	  @Test(timeout = 4000)
	  public void testsdfg5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1964));
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress(250);
	      int int0 = socket0.bind(mockInetSocketAddress1);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1964));
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,connect,S2]
	  }

	  @Test(timeout = 4000)
	  public void teshgsfg6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(false);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(250);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test7()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(24);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-1004));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test8()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(24);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }

	  public void tessdaapsaoskxtesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-4466));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      int int1 = socket0.shutdownInput();
	     //assertEquals(0, int1);

	      mockEnvironment0.pushIOException(true);
	      int int2 = socket0.close();
	     //assertFalse(int2 == int0);
	     //assertEquals(1, int2);
	    // Invalid EPA Transition: [S4,close,S4]
	  }

	  @Test(timeout = 4000)
	  public void testsdkptesasdasadasd()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-10));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownOutput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownOutput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test234sa2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-10));
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.shutdownInput();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S3,shutdownInput,S3]
	  }

	  @Test(timeout = 4000)
	  public void test3()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(262);
	      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 262);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);

	      int int2 = socket0.shutdownInput();
	      mockEnvironment0.pushIOException(true);
	      int int3 = socket0.shutdownOutput();
	     //assertFalse(int3 == int2);
	     //assertEquals(1, int3);
	    // Invalid EPA Transition: [S3,close,S3]
	    // Invalid EPA Transition: [S4,shutdownOutput,S4]
	  }

	  @Test(timeout = 4000)
	  public void test4()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(2746);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      mockEnvironment0.pushIOException(true);
	      int int1 = socket0.close();
	     //assertFalse(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S2,close,S2]
	  }

	  @Test(timeout = 4000)
	  public void test5()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4945);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, (-406));
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,connect,S1]
	  }

	  @Test(timeout = 4000)
	  public void test6()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      mockEnvironment0.pushIOException(true);
	      mockEnvironment0.pushIOException(true);
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(4945);
	      int int0 = socket0.bind(mockInetSocketAddress0);
	      int int1 = socket0.close();
	     //assertTrue(int1 == int0);
	     //assertEquals(1, int1);
	    // Invalid EPA Transition: [S1,bind,S1]
	    // Invalid EPA Transition: [S1,close,S1]
	  }
	  
	  @Test//(timeout = 4000) // Detectado con bud= 600 | cri = line_branch_exception
	  public void test07()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1);
	      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 1);
	      try {
	        socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	        fail("Expecting exception: IOException");

	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }
	  
	  @Test//(timeout = 4000)
	  public void test5_1()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("_RLl.n[]62?_t1FT", (-1));
	      try {
	        socket0.bind(mockInetSocketAddress0);
	        fail("Expecting exception: IOException");

	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }
	  
	  @Test(timeout = 4000) //
	  public void test01_2()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(0);
	      socket0.connect((MockSocketAddress) mockInetSocketAddress0);
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
	  public void test26()  throws Throwable  {
	      Socket socket0 = new Socket();
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      new MockEnvironment();
	      mockEnvironment0.shouldThrowIOException();
	      new MockEnvironment();
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(319);
	      mockEnvironment0.addToBlackList(mockInetSocketAddress0);
	      mockEnvironment0.setDelayToConnect((MockSocketAddress) null, 319);
	      mockEnvironment0.pushIOException(false);
	      new MockEnvironment();
	      Socket socket1 = new Socket(mockEnvironment0);
	      socket1.close();
	      socket0.bind(mockInetSocketAddress0);
	      try {
	        socket1.shutdownInput();
	        fail("Expecting exception: SocketException");

	      } catch(Exception e) {
	         //
	         // Socket is closed
	         //
	      }
	  }

	  @Test(timeout = 4000)
	  public void test01_21()  throws Throwable  {
	      MockEnvironment mockEnvironment0 = new MockEnvironment();
	      Socket socket0 = new Socket(mockEnvironment0);
	      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1624));
	      try {
	        socket0.bind(mockInetSocketAddress0);
	        fail("Expecting exception: IOException");

	      } catch(Exception e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	}
