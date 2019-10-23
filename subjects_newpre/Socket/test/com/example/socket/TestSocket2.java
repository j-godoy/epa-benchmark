package com.example.socket;

import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This test suite achieves 100% epa transition coverage
 * 
 * @author jgaleotti
 */
public class TestSocket2 {

	@Test
	public void testStateS0_S1_S6_S6() throws Exception {
		Socket s = new Socket();
		s.close();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S4_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S5_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_close_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_close_S6_2() throws Exception {
		Socket s = new Socket();
		s.bind(null);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_connect_S6() throws Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.2", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		env.pushIOException(true);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_S3_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S3_S3_S4_S4_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.getInputStream();
		s.getOutputStream();
		s.shutdownInput();
		s.getOutputStream();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S5_S5_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.getInputStream();
		s.close();
	}

	@Test
	public void testStateS0_S1_connect_S6_S6() throws Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		env.pushIOException(true);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_connect_S1_S6() throws Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		env.pushIOException(true);
		env.pushIOException(true);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_connect_S2_S6() throws Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.2", 0);
		s.bind(bindpoint);
		env.pushIOException(true);
		env.pushIOException(true);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_connect_S2_S6_2() throws Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.2", 0);
		s.bind(bindpoint);
		env.pushIOException(true);
		env.pushIOException(true);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint0, 15);
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S3_S4_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		s.shutdownOutput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S5_shutdownInput_S6() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.shutdownInput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_S3_S6_2() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint, 10);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_S3_S6_3() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		try {
			s.connect(endpoint, -10);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testStateBindClosed_SDL23() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		s.close();
		try {
			s.bind(bindpoint);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testStateBindBind_SDL24() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		try {
			s.bind(bindpoint);
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testStateBindBind_SDL25() throws Exception {
		Socket s = new Socket();
		try {
			s.bind(null);
			// No falla porque se arma uno en el bind
			// fail();
		} catch (Exception e) 
		{
		}
	}
	
	

	@Test
	public void testStateBindBind_SDL46() throws Exception {
		Socket s = new Socket();
		s.close();
		try {
			s.getInputStream();
			fail();
		} catch (Exception ex) {
		}
	}

	@Test
	public void testStateGetInputStream_SDL48() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		try {
			s.getInputStream();
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testGetInputStream_SDL51() throws Exception {
		Socket s = new Socket();
		try {
			s.getInputStream();
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testStateGetOutputStream_1() throws Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		try {
			s.getOutputStream();
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testGetOutputStream_2() throws Exception {
		Socket s = new Socket();
		try {
			s.getOutputStream();
			fail();
		} catch (Exception ex) {

		}
	}

	@Test
	public void testConnectInet4() throws Exception {
		Socket s = new Socket();
		MockInet4Address inet4Address = new MockInet4Address("127.0.0.1");
		MockSocketAddress bindpoint = new MockInetSocketAddress(inet4Address, 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint, 10);
		s.close();
	}

	@Test
	public void testRefuseConnection() throws Exception {
		MockEnvironment env = new MockEnvironment();
		MockSocketAddress socketAddr = new MockInetSocketAddress("127.0.0.1", 0);
		env.addToBlackList(socketAddr);
		Socket s = new Socket(env);
		try {
			s.connect(socketAddr, 10);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testConnectToBlackListedAddress() throws Exception {
		MockEnvironment env = new MockEnvironment();
		MockSocketAddress socketAddr = new MockInetSocketAddress("127.0.0.1", 0);
		env.addToBlackList(socketAddr);
		Socket s = new Socket(env);
		try {
			s.connect(socketAddr, 10);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testBindToBlackListedAddress() throws Exception {
		MockEnvironment env = new MockEnvironment();
		MockSocketAddress socketAddr = new MockInetSocketAddress("127.0.0.1", 0);
		env.addToBlackList(socketAddr);
		Socket s = new Socket(env);
		try {
			s.bind(socketAddr);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testConnectTimeout() throws Exception {
		MockEnvironment env = new MockEnvironment();
		MockSocketAddress socketAddr = new MockInetSocketAddress("127.0.0.1", 0);
		env.setDelayToConnect(socketAddr,10);
		Socket s = new Socket(env);
		try {
			s.connect(socketAddr, 5);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testBindPort1() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 1);
		s.bind(bindpoint);
		s.close();
	}
	
	@Test
	public void testStateBindBind_SDL66() throws Exception {
		Socket s = new Socket();
		MockInet4Address inet4Address = new MockInet4Address("127.0.0.1");
		MockSocketAddress bindpoint = new MockInetSocketAddress(inet4Address, 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint, 10);
		s.close();
		try {
			s.getInputStream();
			fail();
		} catch (Exception ex) {
		}
	}
	
	@Test
	public void test_COD2() throws Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		try {
			s.connect(endpoint);
			fail();
		} catch (Exception ex) {
		s.close();
		}
	}
	
	@Test(timeout = 4000)
	public void test11_2() throws Throwable {
	Socket socket0 = new Socket();
	MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((-1261));
	socket0.connect((MockSocketAddress) mockInetSocketAddress0);
	socket0.close();
	try {
	socket0.shutdownOutput();
	fail("Expecting exception: Exception");

	} catch(Exception e) {
	}
	}
	
	@Test(timeout = 4000)
  public void test29()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInet6Address mockInet6Address0 = (MockInet6Address)MockInetAddress.anyLocalAddress();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet6Address0, 0);
      mockEnvironment0.addToBlackList(mockInetSocketAddress0);
      MockInetSocketAddress mockInetSocketAddress1 = new MockInetSocketAddress(45);
      socket0.connect((MockSocketAddress) mockInetSocketAddress1, 45);
      socket0.getInputStream();
      socket0.shutdownOutput();
      mockEnvironment0.pushIOException(true);
      socket0.close();
      socket0.shutdownInput();
      Socket socket1 = new Socket(mockEnvironment0);
      socket0.close();
      Socket socket2 = new Socket(mockEnvironment0);
      Socket socket3 = new Socket(mockEnvironment0);
      Socket socket4 = new Socket(mockEnvironment0);
      Socket socket5 = new Socket(mockEnvironment0);
     //assertFalse(socket5.equals((Object)socket2));
  }
	
  @Test//(timeout = 4000)
  public void test12()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      Socket socket0 = new Socket(mockEnvironment0);
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress("lg", 0);
      mockEnvironment0.setDelayToConnect(mockInetSocketAddress0, 1);
      socket0.bind(mockInetSocketAddress0);
      try {
        socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
        fail("Expecting exception: IOException");

      } catch(Exception e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }
  
  @Test(timeout = 1300)
  public void test103()  throws Throwable  {
      MockEnvironment mockEnvironment0 = new MockEnvironment();
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.pushIOException(true);
      mockEnvironment0.pushIOException(true);
      MockInetAddress.getLocalHost();
      MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress(1384);
      Socket socket0 = new Socket(mockEnvironment0);
      int int0 = socket0.connect((MockSocketAddress) mockInetSocketAddress0, 0);
      assertEquals(1, int0);

      int int1 = socket0.connect((MockSocketAddress) mockInetSocketAddress0);
      int int2 = socket0.close();
      assertFalse(int2 == int1);
      assertEquals(0, int2);
  }




}
