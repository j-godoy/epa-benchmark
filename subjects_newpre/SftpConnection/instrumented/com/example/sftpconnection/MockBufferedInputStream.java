package com.example.sftpconnection;

import java.io.FileInputStream;

public class MockBufferedInputStream {

	public MockBufferedInputStream(FileInputStream fileInputStream) {
	}

	public MockBufferedInputStream(MockInputStream mockInputStream) {
	}

	public MockBufferedInputStream(MockFileInputStream mockFileInputStream) {
	}

	public int read(byte[] buf) {
		return 0;
	}

	public void close() {
	}

}
