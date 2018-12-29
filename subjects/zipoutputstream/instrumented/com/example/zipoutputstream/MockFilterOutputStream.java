package com.example.zipoutputstream;

import java.io.IOException;

public class MockFilterOutputStream extends MockOutputStream {
	/**
	 * The underlying output stream to be filtered.
	 */
	protected MockOutputStream out;

	/**
	 * Creates an output stream filter built on top of the specified underlying
	 * output stream.
	 *
	 * @param out
	 *            the underlying output stream to be assigned to the field
	 *            <tt>this.out</tt> for later use, or <code>null</code> if this
	 *            instance is to be created without an underlying stream.
	 */
	public MockFilterOutputStream(MockOutputStream out) {
		this.out = out;
	}

	/**
	 * Writes the specified <code>byte</code> to this output stream.
	 * <p>
	 * The <code>write</code> method of <code>FilterOutputStream</code> calls
	 * the <code>write</code> method of its underlying output stream, that is,
	 * it performs <tt>out.write(b)</tt>.
	 * <p>
	 * Implements the abstract <tt>write</tt> method of <tt>OutputStream</tt>.
	 *
	 * @param b
	 *            the <code>byte</code>.
	 * @exception IOException
	 *                if an I/O error occurs.
	 */
	public void write(int b) throws IOException {
		if(!(out instanceof ZipOutputStream))
			out.write(b);
	}

	/**
	 * Writes <code>b.length</code> bytes to this output stream.
	 * <p>
	 * The <code>write</code> method of <code>FilterOutputStream</code> calls
	 * its <code>write</code> method of three arguments with the arguments
	 * <code>b</code>, <code>0</code>, and <code>b.length</code>.
	 * <p>
	 * Note that this method does not call the one-argument <code>write</code>
	 * method of its underlying stream with the single argument <code>b</code>.
	 *
	 * @param b
	 *            the data to be written.
	 * @exception IOException
	 *                if an I/O error occurs.
	 * @see java.io.FilterOutputStream#write(byte[], int, int)
	 */
	public void write(byte b[]) throws IOException {
		write(b, 0, b.length);
	}

	/**
	 * Writes <code>len</code> bytes from the specified <code>byte</code> array
	 * starting at offset <code>off</code> to this output stream.
	 * <p>
	 * The <code>write</code> method of <code>FilterOutputStream</code> calls
	 * the <code>write</code> method of one argument on each <code>byte</code>
	 * to output.
	 * <p>
	 * Note that this method does not call the <code>write</code> method of its
	 * underlying input stream with the same arguments. Subclasses of
	 * <code>FilterOutputStream</code> should provide a more efficient
	 * implementation of this method.
	 *
	 * @param b
	 *            the data.
	 * @param off
	 *            the start offset in the data.
	 * @param len
	 *            the number of bytes to write.
	 * @exception IOException
	 *                if an I/O error occurs.
	 * @see java.io.FilterOutputStream#write(int)
	 */
	public void write(byte b[], int off, int len) throws IOException {
		if ((off | len | (b.length - (len + off)) | (off + len)) < 0)
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < len; i++) {
			write(b[off + i]);
		}
	}

	/**
	 * Flushes this output stream and forces any buffered output bytes to be
	 * written out to the stream.
	 * <p>
	 * The <code>flush</code> method of <code>FilterOutputStream</code> calls
	 * the <code>flush</code> method of its underlying output stream.
	 *
	 * @exception IOException
	 *                if an I/O error occurs.
	 * @see java.io.FilterOutputStream#out
	 */
	public void flush() throws IOException {
		if(!(out instanceof ZipOutputStream))
			out.flush();
	}

	/**
	 * Closes this output stream and releases any system resources associated
	 * with the stream.
	 * <p>
	 * The <code>close</code> method of <code>FilterOutputStream</code> calls
	 * its <code>flush</code> method, and then calls the <code>close</code>
	 * method of its underlying output stream.
	 *
	 * @exception IOException
	 *                if an I/O error occurs.
	 * @see java.io.FilterOutputStream#flush()
	 * @see java.io.FilterOutputStream#out
	 */
	public void close() throws IOException {
		flush();
		out.close();
	}
}
