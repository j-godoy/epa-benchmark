package com.example.sftpconnection;

import java.io.FileOutputStream;

public class MockBufferedOutputStream {

	public MockBufferedOutputStream(MockOutputStream put) {
	}

	public MockBufferedOutputStream(FileOutputStream fileOutputStream) {
	}

	public MockBufferedOutputStream(MockFileOutputStream mockFileOutputStream) {
	}

	public void write(byte[] buf, int i, int len) {
	}

	public void flush() {
	}

	public void close() {
	}

}
