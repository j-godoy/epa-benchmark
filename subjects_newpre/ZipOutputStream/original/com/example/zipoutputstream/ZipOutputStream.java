/*
 * @(#)ZipOutputStream.java	1.36 10/03/23
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.zipoutputstream;

import java.io.IOException;
import java.util.Vector;
import java.util.HashSet;
import java.util.zip.CRC32;
//import java.util.zip.Deflater;

/**
 * This class implements an output stream filter for writing files in the ZIP
 * file format. Includes support for both compressed and uncompressed entries.
 *
 * @author David Connelly
 * @version 1.36, 03/23/10
 */
public class ZipOutputStream extends MockDeflaterOutputStream implements MockZipConstants {

	private static class XEntry {
		public final MockZipEntry entry;
		public final long offset;
		public final int flag;

		public XEntry(MockZipEntry entry, long offset) {
			this.entry = entry;
			this.offset = offset;
			this.flag = (entry.method == DEFLATED && (entry.size == -1 || entry.csize == -1 || entry.crc == -1))
					// store size, compressed size, and crc-32 in data
					// descriptor
					// immediately following the compressed entry data
					? 8
					// store size, compressed size, and crc-32 in LOC header
					: 0;
		}
	}

	private XEntry current;
	private Vector<XEntry> xentries = new Vector<XEntry>();
	private HashSet<String> names = new HashSet<String>();
	private CRC32 crc = new CRC32();
	private long written = 0;
	private long locoff = 0;
//	private String comment;
	private int method = DEFLATED;
//	private boolean finished;

	private boolean closed = false;
	private MockByteArrayOutputStream byteArrayOutputStream;

	private static int version(MockZipEntry e) throws MockZipException {
		switch (e.method) {
		case DEFLATED:
			return 20;
		case STORED:
			return 10;
		default:
			throw new MockZipException("unsupported compression method");
		}
	}

	/**
	 * Checks to make sure that this stream has not been closed.
	 */
	private void ensureOpen() throws IOException {
		if (closed) {
			throw new IOException("Stream closed");
		}
	}

	/**
	 * Compression method for uncompressed (STORED) entries.
	 */
	public static final int STORED = MockZipEntry.STORED;

	/**
	 * Compression method for compressed (DEFLATED) entries.
	 */
	public static final int DEFLATED = MockZipEntry.DEFLATED;

	/**
	 * Creates a new ZIP output stream.
	 * 
	 * @param out
	 *            the actual output stream
	 */
	public ZipOutputStream() {
		super(new MockByteArrayOutputStream(), new MockDeflater(MockDeflater.DEFAULT_COMPRESSION, true));
		usesDefaultDeflater = true;
		this.byteArrayOutputStream = (MockByteArrayOutputStream) this.out;
	}

	/**
	 * Sets the ZIP file comment.
	 * 
	 * @param comment
	 *            the comment string
	 * @exception IllegalArgumentException
	 *                if the length of the specified ZIP file comment is greater
	 *                than 0xFFFF bytes
	 */
//	public void setComment(String comment) {
//		if (comment != null && comment.length() > 0xffff / 3 && getUTF8Length(comment) > 0xffff) {
//			throw new IllegalArgumentException("ZIP file comment too long.");
//		}
//		this.comment = comment;
//	}

	/**
	 * Sets the default compression method for subsequent entries. This default
	 * will be used whenever the compression method is not specified for an
	 * individual ZIP file entry, and is initially set to DEFLATED.
	 * 
	 * @param method
	 *            the default compression method
	 * @exception IllegalArgumentException
	 *                if the specified compression method is invalid
	 */
//	public void setMethod(int method) {
//		if (method != DEFLATED && method != STORED) {
//			throw new IllegalArgumentException("invalid compression method");
//		}
//		this.method = method;
//	}

	/**
	 * Sets the compression level for subsequent entries which are DEFLATED. The
	 * default setting is DEFAULT_COMPRESSION.
	 * 
	 * @param level
	 *            the compression level (0-9)
	 * @exception IllegalArgumentException
	 *                if the compression level is invalid
	 */
//	public void setLevel(int level) {
//		deflater_setLevel(level);
//	}

	/**
	 * Begins writing a new ZIP file entry and positions the stream to the start
	 * of the entry data. Closes the current entry if still active. The default
	 * compression method will be used if no compression method was specified
	 * for the entry, and the current time will be used if the entry has no set
	 * modification time.
	 * 
	 * @param e
	 *            the ZIP entry to be written
	 * @exception MockZipException
	 *                if a ZIP format error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void putNextEntry(MockZipEntry e) throws IOException {
		ensureOpen();
		if (current != null) {
			closeEntry0(); // close previous entry
		}
		if (e.time == -1) {
			e.setTime(System.currentTimeMillis());
		}
		if (e.method == -1) {
			e.method = method; // use default method
		}
		switch (e.method) {
		case DEFLATED:
			break;
		case STORED:
			// compressed size, uncompressed size, and crc-32 must all be
			// set for entries using STORED compression method
			if (e.size == -1) {
				e.size = e.csize;
			} else if (e.csize == -1) {
				e.csize = e.size;
			} else if (e.size != e.csize) {
				throw new MockZipException("STORED entry where compressed != uncompressed size");
			}
			if (e.size == -1 || e.crc == -1) {
				throw new MockZipException("STORED entry missing size, compressed size, or crc-32");
			}
			break;
		default:
			throw new MockZipException("unsupported compression method");
		}
		if (!names.add(e.name)) {
			throw new MockZipException("duplicate entry: " + e.name);
		}
		current = new XEntry(e, written);
		xentries.add(current);
		writeLOC(current);
	}

	public void closeEntry() throws IOException {
		closeEntry0();
	}

	/**
	 * Closes the current ZIP entry and positions the stream for writing the
	 * next entry.
	 * 
	 * @exception MockZipException
	 *                if a ZIP format error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	private void closeEntry0() throws IOException {
		ensureOpen();
		if (current != null) {
			MockZipEntry e = current.entry;
			switch (e.method) {
			case DEFLATED:
				deflater_finish();
				while (!deflafter_finished()) {
					deflate();
				}
				if ((current.flag & 8) == 0) {
					// verify size, compressed size, and crc-32 settings
					if (e.size != deflater_getBytesRead()) {
						throw new MockZipException("invalid entry size (expected " + e.size + " but got "
								+ deflater_getBytesRead() + " bytes)");
					}
					if (e.csize != deflater_getBytesWritten()) {
						throw new MockZipException("invalid entry compressed size (expected " + e.csize + " but got "
								+ deflater_getBytesWritten() + " bytes)");
					}
					if (e.crc != crc.getValue()) {
						throw new MockZipException("invalid entry CRC-32 (expected 0x" + Long.toHexString(e.crc)
								+ " but got 0x" + Long.toHexString(crc.getValue()) + ")");
					}
				} else {
					e.size = deflater_getBytesRead();
					e.csize = deflater_getBytesWritten();
					e.crc = crc.getValue();
					writeEXT(e);
				}
				deflater_reset();
				written += e.csize;
				break;
			case STORED:
				// we already know that both e.size and e.csize are the same
				if (e.size != written - locoff) {
					throw new MockZipException(
							"invalid entry size (expected " + e.size + " but got " + (written - locoff) + " bytes)");
				}
				if (e.crc != crc.getValue()) {
					throw new MockZipException("invalid entry crc-32 (expected 0x" + Long.toHexString(e.crc) + " but got 0x"
							+ Long.toHexString(crc.getValue()) + ")");
				}
				break;
			default:
				throw new MockZipException("invalid compression method");
			}
			crc.reset();
			current = null;
		}
	}

	/**
	 * Writes an array of bytes to the current ZIP entry data. This method will
	 * block until all the bytes are written.
	 * 
	 * @param b
	 *            the data to be written
	 * @param off
	 *            the start offset in the data
	 * @param len
	 *            the number of bytes that are written
	 * @exception MockZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public synchronized void write(byte[] b, int off, int len) throws IOException {
		ensureOpen();
		if (off < 0 || len < 0 || off > b.length - len) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}

		if (current == null) {
			throw new MockZipException("no current ZIP entry");
		}
		MockZipEntry entry = current.entry;
		switch (entry.method) {
		case DEFLATED:
			super.write(b, off, len);
			break;
		case STORED:
			written += len;
			if (written - locoff > entry.size) {
				throw new MockZipException("attempt to write past end of STORED entry");
			}
			if(!(out instanceof ZipOutputStream))
				out.write(b, off, len);
			break;
		default:
			throw new MockZipException("invalid compression method");
		}
		crc.update(b, off, len);
	}

	/**
	 * Finishes writing the contents of the ZIP output stream without closing
	 * the underlying stream. Use this method when applying multiple filters in
	 * succession to the same output stream.
	 * 
	 * @exception ZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O exception has occurred
	 */
//	public void finish() throws IOException {
//		ensureOpen();
//		if (finished) {
//			return;
//		}
//		if (current != null) {
//			closeEntry0();
//		}
//		if (xentries.size() < 1) {
//			throw new ZipException("ZIP file must have at least one entry");
//		}
//		// write central directory
//		long off = written;
//		for (XEntry xentry : xentries)
//			writeCEN(xentry);
//		writeEND(off, written - off);
//		finished = true;
//	}

	/**
	 * Closes the ZIP output stream as well as the stream being filtered.
	 * 
	 * @exception MockZipException
	 *                if a ZIP file error has occurred
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void close() throws IOException {
		if (!closed) {
			super.close();
			closed = true;
		}
	}

	/*
	 * Writes local file (LOC) header for specified entry.
	 */
	private void writeLOC(XEntry xentry) throws IOException {
		MockZipEntry e = xentry.entry;
		int flag = xentry.flag;
		writeInt(LOCSIG); // LOC header signature
		writeShort(version(e)); // version needed to extract
		writeShort(flag); // general purpose bit flag
		writeShort(e.method); // compression method
		writeInt(e.time); // last modification time
		if ((flag & 8) == 8) {
			// store size, uncompressed size, and crc-32 in data descriptor
			// immediately following compressed entry data
			writeInt(0);
			writeInt(0);
			writeInt(0);
		} else {
			writeInt(e.crc); // crc-32
			writeInt(e.csize); // compressed size
			writeInt(e.size); // uncompressed size
		}
		byte[] nameBytes = getUTF8Bytes(e.name);
		writeShort(nameBytes.length);
		writeShort(e.extra != null ? e.extra.length : 0);
		writeBytes(nameBytes, 0, nameBytes.length);
		if (e.extra != null) {
			writeBytes(e.extra, 0, e.extra.length);
		}
		locoff = written;
	}

	/*
	 * Writes extra data descriptor (EXT) for specified entry.
	 */
	private void writeEXT(MockZipEntry e) throws IOException {
		writeInt(EXTSIG); // EXT header signature
		writeInt(e.crc); // crc-32
		writeInt(e.csize); // compressed size
		writeInt(e.size); // uncompressed size
	}

	/*
	 * Write central directory (CEN) header for specified entry. REMIND: add
	 * support for file attributes
	 */
//	private void writeCEN(XEntry xentry) throws IOException {
//		ZipEntry e = xentry.entry;
//		int flag = xentry.flag;
//		int version = version(e);
//		writeInt(CENSIG); // CEN header signature
//		writeShort(version); // version made by
//		writeShort(version); // version needed to extract
//		writeShort(flag); // general purpose bit flag
//		writeShort(e.method); // compression method
//		writeInt(e.time); // last modification time
//		writeInt(e.crc); // crc-32
//		writeInt(e.csize); // compressed size
//		writeInt(e.size); // uncompressed size
//		byte[] nameBytes = getUTF8Bytes(e.name);
//		writeShort(nameBytes.length);
//		writeShort(e.extra != null ? e.extra.length : 0);
//		byte[] commentBytes;
//		if (e.comment != null) {
//			commentBytes = getUTF8Bytes(e.comment);
//			writeShort(commentBytes.length);
//		} else {
//			commentBytes = null;
//			writeShort(0);
//		}
//		writeShort(0); // starting disk number
//		writeShort(0); // internal file attributes (unused)
//		writeInt(0); // external file attributes (unused)
//		writeInt(xentry.offset); // relative offset of local header
//		writeBytes(nameBytes, 0, nameBytes.length);
//		if (e.extra != null) {
//			writeBytes(e.extra, 0, e.extra.length);
//		}
//		if (commentBytes != null) {
//			writeBytes(commentBytes, 0, commentBytes.length);
//		}
//	}

	/*
	 * Writes end of central directory (END) header.
	 */
//	private void writeEND(long off, long len) throws IOException {
//		int count = xentries.size();
//		writeInt(ENDSIG); // END record signature
//		writeShort(0); // number of this disk
//		writeShort(0); // central directory start disk
//		writeShort(count); // number of directory entries on disk
//		writeShort(count); // total number of directory entries
//		writeInt(len); // length of central directory
//		writeInt(off); // offset of central directory
//		if (comment != null) { // zip file comment
//			byte[] b = getUTF8Bytes(comment);
//			writeShort(b.length);
//			writeBytes(b, 0, b.length);
//		} else {
//			writeShort(0);
//		}
//	}

	/*
	 * Writes a 16-bit short to the output stream in little-endian byte order.
	 */
	private void writeShort(int v) throws IOException {
		MockOutputStream out = this.out;
		out.write((v >>> 0) & 0xff);
		out.write((v >>> 8) & 0xff);
		written += 2;
	}

	/*
	 * Writes a 32-bit int to the output stream in little-endian byte order.
	 */
	private void writeInt(long v) throws IOException {
		MockOutputStream out = this.out;
		out.write((int) ((v >>> 0) & 0xff));
		out.write((int) ((v >>> 8) & 0xff));
		out.write((int) ((v >>> 16) & 0xff));
		out.write((int) ((v >>> 24) & 0xff));
		written += 4;
	}

	/*
	 * Writes an array of bytes to the output stream.
	 */
	private void writeBytes(byte[] b, int off, int len) throws IOException {
		super.out.write(b, off, len);
		written += len;
	}

	/*
	 * Returns the length of String's UTF8 encoding.
	 */
//	static int getUTF8Length(String s) {
//		int count = 0;
//		for (int i = 0; i < s.length(); i++) {
//			char ch = s.charAt(i);
//			if (ch <= 0x7f) {
//				count++;
//			} else if (ch <= 0x7ff) {
//				count += 2;
//			} else {
//				count += 3;
//			}
//		}
//		return count;
//	}

	/*
	 * Returns an array of bytes representing the UTF8 encoding of the specified
	 * String.
	 */
	private static byte[] getUTF8Bytes(String s) {
		char[] c = s.toCharArray();
		int len = c.length;
		// Count the number of encoded bytes...
		int count = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				count++;
			} else if (ch <= 0x7ff) {
				count += 2;
			} else {
				count += 3;
			}
		}
		// Now return the encoded bytes...
		byte[] b = new byte[count];
		int off = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				b[off++] = (byte) ch;
			} else if (ch <= 0x7ff) {
				b[off++] = (byte) ((ch >> 6) | 0xc0);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			} else {
				b[off++] = (byte) ((ch >> 12) | 0xe0);
				b[off++] = (byte) (((ch >> 6) & 0x3f) | 0x80);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			}
		}
		return b;
	}

//	public byte[] toByteArray() {
//		return this.byteArrayOutputStream.toByteArray();
//	}

}
