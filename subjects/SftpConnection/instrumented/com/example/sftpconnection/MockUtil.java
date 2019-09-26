package com.example.sftpconnection;

public class MockUtil {

	public static MockSocket createSocket(String host, int port, int connectTimeout) {
		return new MockSocket();
	}

	static byte[] str2byte(String str, String encoding) {
		if (str == null)
			return null;
		try {
			return str.getBytes(encoding);
		} catch (java.io.UnsupportedEncodingException e) {
			return str.getBytes();
		}
	}

	static String byte2str(byte[] str, int s, int l) {
		return byte2str(str, s, l, "UTF-8");
	}

	static String byte2str(byte[] str) {
		return byte2str(str, 0, str.length, "UTF-8");
	}

	static String byte2str(byte[] str, String encoding) {
		return byte2str(str, 0, str.length, encoding);
	}

	static String byte2str(byte[] str, int s, int l, String encoding) {
		try {
			return new String(str, s, l, encoding);
		} catch (java.io.UnsupportedEncodingException e) {
			return new String(str, s, l);
		}
	}

	static byte[] str2byte(String str) {
		return str2byte(str, "UTF-8");
	}

	// public static String getFingerPrint(MockHASH hash, byte[] hostKey) {
	// return null;
	// }

	static void bzero(byte[] foo) {
		if (foo == null)
			return;
		for (int i = 0; i < foo.length; i++)
			foo[i] = 0;
	}

	public static String unquote(String newpath) {
		return newpath;
	}

	// public static boolean glob(byte[] pattern, byte[] str2byte) {
	// return false;
	// }

}
