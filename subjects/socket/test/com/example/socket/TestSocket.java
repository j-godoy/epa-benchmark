package com.example.socket;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.SocketException;

import org.junit.Test;

/**
 * This test suite achieves 100% epa transition coverage
 * 
 * @author jgaleotti
 */
public class TestSocket {

	@Test
	public void testStateS0_S1_S6_S6() throws IOException, Exception {
		Socket s = new Socket();
		s.close();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S4_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S5_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_close_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_close_S6_2() throws IOException, Exception {
		Socket s = new Socket();
		s.bind(null);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_connect_S6() throws IOException, Exception {
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
	public void testStateS0_S1_S2_S3_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S3_S3_S4_S4_S6() throws IOException, Exception {
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
	public void testStateS0_S1_S3_S5_S5_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.getInputStream();
		s.close();
	}

	@Test
	public void testStateS0_S1_connect_S6_S6() throws IOException, Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		env.pushIOException(true);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_connect_S1_S6() throws IOException, Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		env.pushIOException(true);
		env.pushIOException(true);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint0);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_connect_S2_S6() throws IOException, Exception {
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
	public void testStateS0_S1_S2_connect_S2_S6_2() throws IOException, Exception {
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
	public void testStateS0_S1_S3_S3_S4_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		s.shutdownOutput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S3_S5_shutdownInput_S6() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		s.shutdownInput();
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_S3_S6_2() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint, 10);
		s.close();
	}

	@Test
	public void testStateS0_S1_S2_S3_S6_3() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		try {
			s.connect(endpoint, -10);
			fail();
		} catch (IllegalArgumentException ex) {

		}
		s.close();
	}

	@Test
	public void testStateBindClosed_SDL23() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		s.close();
		try {
			s.bind(bindpoint);
			fail();
		} catch (SocketException e) {
		}
	}

	@Test
	public void testStateBindBind_SDL24() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		try {
			s.bind(bindpoint);
			fail();
		} catch (SocketException e) {
		}
	}

	@Test
	public void testStateBindBind_SDL25() throws IOException, Exception {
		Socket s = new Socket();
		try {
			s.bind(null);
			// No falla porque se arma uno en el bind
			// fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testStateBindBind_SDL46() throws IOException, Exception {
		Socket s = new Socket();
		s.close();
		try {
			s.getInputStream();
			fail();
		} catch (SocketException ex) {
		}
	}

	@Test
	public void testStateGetInputStream_SDL48() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownInput();
		try {
			s.getInputStream();
			fail();
		} catch (SocketException ex) {

		}
	}

	@Test
	public void testGetInputStream_SDL51() throws IOException, Exception {
		Socket s = new Socket();
		try {
			s.getInputStream();
			fail();
		} catch (SocketException ex) {

		}
	}

	@Test
	public void testStateGetOutputStream_1() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		s.shutdownOutput();
		try {
			s.getOutputStream();
			fail();
		} catch (SocketException ex) {

		}
	}

	@Test
	public void testGetOutputStream_2() throws IOException, Exception {
		Socket s = new Socket();
		try {
			s.getOutputStream();
			fail();
		} catch (SocketException ex) {

		}
	}

	@Test
	public void testConnectInet4() throws IOException, Exception {
		Socket s = new Socket();
		MockInet4Address inet4Address = new MockInet4Address("127.0.0.1");
		MockSocketAddress bindpoint = new MockInetSocketAddress(inet4Address, 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint, 10);
		s.close();
	}

	@Test
	public void testRefuseConnection() throws IOException, Exception {
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
	public void testConnectToBlackListedAddress() throws IOException, Exception {
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
	public void testBindToBlackListedAddress() throws IOException, Exception {
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
	public void testConnectTimeout() throws IOException, Exception {
		MockEnvironment env = new MockEnvironment();
		MockSocketAddress socketAddr = new MockInetSocketAddress("127.0.0.1", 0);
		env.setDelayToConnect(socketAddr, 10);
		Socket s = new Socket(env);
		try {
			s.connect(socketAddr, 5);
			fail();
		} catch (Exception ex) {

		}
		s.close();
	}

	@Test
	public void testBindPort1() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 1);
		s.bind(bindpoint);
		s.close();
	}

	@Test
	public void testStateBindBind_SDL66() throws IOException, Exception {
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
		} catch (SocketException ex) {
		}
	}

	@Test
	public void test_COD2() throws IOException, Exception {
		Socket s = new Socket();
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint);
		try {
			s.connect(endpoint);
			fail();
		} catch (SocketException ex) {
			s.close();
		}
	}

	@Test // (timeout = 4000)
	public void test29_2() throws Throwable {
		Socket socket0 = new Socket();
		MockInet4Address mockInet4Address0 = new MockInet4Address("97=AkhV$$?PKq");
		MockInetSocketAddress mockInetSocketAddress0 = new MockInetSocketAddress((MockInetAddress) mockInet4Address0,
				(-1347));
		try {
			socket0.bind(mockInetSocketAddress0);
			fail("Expecting exception: IOException");

		} catch (Exception e) {
			//
			// no message in exception (getMessage() returned null)
			//
//			e.printStackTrace();
		}
	}

	@Test
	public void testConnectCloseShutdownInput() throws IOException, Exception {
		MockEnvironment env = new MockEnvironment();
		Socket s = new Socket(env);
		MockSocketAddress bindpoint = new MockInetSocketAddress("127.0.0.2", 0);
		s.bind(bindpoint);
		MockSocketAddress endpoint0 = new MockInetSocketAddress("127.0.0.1", 0);
		s.connect(endpoint0, 15);
		s.close();
		try {
			s.shutdownInput();
			fail("Expecting exception: IOException");
		} catch (Exception ex) {

		}
	}

}
