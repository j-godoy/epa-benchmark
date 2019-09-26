package com.example.tohtmlstream;

import java.io.IOException;
import java.io.Reader;

public class MockInputStreamReader extends Reader {

	public MockInputStreamReader(MockInputStream is, String string) throws MockUnsupportedEncodingException {
	}

	public MockInputStreamReader(MockInputStream is) {
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return 0;
	}

	@Override
	public void close() throws IOException {
	}
}
