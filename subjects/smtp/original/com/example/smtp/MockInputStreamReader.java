package com.example.smtp;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class MockInputStreamReader extends Reader {

	public MockInputStreamReader(InputStream inputStream) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int read(char[] arg0, int arg1, int arg2) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
