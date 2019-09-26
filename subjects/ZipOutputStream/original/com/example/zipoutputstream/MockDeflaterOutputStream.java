/*
 * @(#)DeflaterOutputStream.java	1.37 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.zipoutputstream;

import java.io.IOException;
//import java.util.zip.Deflater;

/**
 * This class implements an output stream filter for compressing data in the
 * "deflate" compression format. It is also used as the basis for other types of
 * compression filters, such as GZIPOutputStream.
 *
 * @see MockDeflater
 * @version 1.37, 03/23/10
 * @author David Connelly
 */
public class MockDeflaterOutputStream extends MockFilterOutputStream {
	/**
	 * Compressor for this stream.
	 */
	private MockDeflater def;

	/**
	 * Output buffer for writing compressed data.
	 */
	private byte[] buf;

	/**
	 * Indicates that the stream has been closed.
	 */

	private boolean closed = false;

	/**
	 * Creates a new output stream with the specified compressor and buffer size.
	 * 
	 * @param out
	 *            the output stream
	 * @param def
	 *            the compressor ("deflater")
	 * @param size
	 *            the output buffer size
	 * @exception IllegalArgumentException
	 *                if size is <= 0
	 */
	public MockDeflaterOutputStream(MockOutputStream out, MockDeflater def, int size) {
		super(out);
		if (out == null || def == null) {
			throw new NullPointerException();
		} else if (size <= 0) {
			throw new IllegalArgumentException("buffer size <= 0");
		}
		this.def = def;
		buf = new byte[size];
	}

	/**
	 * Creates a new output stream with the specified compressor and a default
	 * buffer size.
	 * 
	 * @param out
	 *            the output stream
	 * @param def
	 *            the compressor ("deflater")
	 */
	public MockDeflaterOutputStream(MockOutputStream out, MockDeflater def) {
		this(out, def, 512);
	}

	boolean usesDefaultDeflater = false;

	/**
	 * Creates a new output stream with a default compressor and buffer size.
	 * 
	 * @param out
	 *            the output stream
	 */
	public MockDeflaterOutputStream(MockOutputStream out) {
		this(out, new MockDeflater());
		usesDefaultDeflater = true;
	}

	/**
	 * Writes a byte to the compressed output stream. This method will block until
	 * the byte can be written.
	 * 
	 * @param b
	 *            the byte to be written
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void write(int b) throws IOException {
		byte[] buf = new byte[1];
		buf[0] = (byte) (b & 0xff);
		write(buf, 0, 1);
	}

	/**
	 * Writes an array of bytes to the compressed output stream. This method will
	 * block until all the bytes are written.
	 * 
	 * @param b
	 *            the data to be written
	 * @param off
	 *            the start offset of the data
	 * @param len
	 *            the length of the data
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void write(byte[] b, int off, int len) throws IOException {
		if (def.finished()) {
			throw new IOException("write beyond end of stream");
		}
		if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		if (!def.finished()) {
			// Deflate no more than stride bytes at a time. This avoids
			// excess copying in deflateBytes (see Deflater.c)
			int stride = buf.length;
			for (int i = 0; i < len; i += stride) {
				def.setInput(b, off + i, Math.min(stride, len - i));
				while (!def.needsInput()) {
					deflate();
				}
			}
		}
	}

	/**
	 * Finishes writing compressed data to the output stream without closing the
	 * underlying stream. Use this method when applying multiple filters in
	 * succession to the same output stream.
	 * 
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void finish() throws IOException {
		if (!def.finished()) {
			def.finish();
			while (!def.finished()) {
				deflate();
			}
		}
	}

	/**
	 * Writes remaining compressed data to the output stream and closes the
	 * underlying stream.
	 * 
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void close() throws IOException {
		if (!closed) {
			finish();
			if (usesDefaultDeflater)
				def.end();
			if(!(out instanceof ZipOutputStream))
				out.close();
			closed = true;
		}
	}

	/**
	 * Writes next block of compressed data to the output stream.
	 * 
	 * @throws IOException
	 *             if an I/O error has occurred
	 */
	protected void deflate() throws IOException {
		int len = def.deflate(buf, 0, buf.length);
		if (len > 0) {
			out.write(buf, 0, len);
		}
	}

	public void deflater_finish() {
		this.def.finish();
	}

	// public void deflater_setLevel(int level) {
	// def.setLevel(level);
	//
	// }

	public boolean deflafter_finished() {
		return def.finished();
	}

	public long deflater_getBytesRead() {
		return def.getBytesRead();
	}

	public long deflater_getBytesWritten() {
		return def.getBytesWritten();
	}

	public void deflater_reset() {
		def.reset();
	}

}
