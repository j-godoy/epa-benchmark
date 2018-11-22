package com.example.zipoutputstream;

public class MockDeflater {

	public static final int DEFAULT_COMPRESSION = -1;
	public static final int NO_FLUSH = 0;
	private boolean finish;

	public MockDeflater(int defaultCompression, boolean b) {
	}

	public MockDeflater() {
		this(DEFAULT_COMPRESSION, false);
	}

	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setInput(byte[] b, int off, int len) {
		if (b== null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
	}

	public boolean needsInput() {
		return true;
	}

	public void finish() {
		finish = true;
	}

	public void end() {
	}

	public int deflate(byte[] b, int off, int len) {
		return deflate(b, off, len, NO_FLUSH);
	}
	
	public int deflate(byte[] b, int off, int len, int flush) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return 0;
    }

	public long getBytesRead() {
		return 0;
	}

	public long getBytesWritten() {
		return 0;
	}

	public void reset() {
		finish = false;
	}

}
