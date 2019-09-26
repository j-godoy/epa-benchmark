package com.example.signature;


/**
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>) for the
 * <code>Signature</code> class, which is used to provide the functionality of a
 * digital signature algorithm. Digital signatures are used for authentication
 * and integrity assurance of digital data. .
 * <p>
 * All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation of a
 * particular signature algorithm.
 *
 * @author Benjamin Renaud
 *
 *
 * @see Signature
 */

public class MockSignatureSpi {

	private MockPrivateKey privateKeyForSigning = null;

	private MockPublicKey publicKeyForVerifying = null;

	private byte[] bytes;

	/**
	 * Initializes this signature object with the specified public key for
	 * verification operations.
	 *
	 * @param publicKey
	 *            the public key of the identity whose signature is going to be
	 *            verified.
	 *
	 * @exception InvalidKeyException
	 *                if the key is improperly encoded, parameters are missing, and
	 *                so on.
	 */
	protected void engineInitVerify(MockPublicKey publicKey) throws MockInvalidKeyException {
		if (publicKey == null) {
			throw new NullPointerException("public key cannot be null");
		}
		if (!publicKey.isValid()) {
			throw new MockInvalidKeyException("key is improperly encoded");
		}

		this.publicKeyForVerifying = publicKey;
		this.privateKeyForSigning = null;
		this.bytes = new byte[] {};
	}

	/**
	 * Initializes this signature object with the specified private key for signing
	 * operations.
	 *
	 * @param privateKey
	 *            the private key of the identity whose signature will be generated.
	 *
	 * @exception InvalidKeyException
	 *                if the key is improperly encoded, parameters are missing, and
	 *                so on.
	 */
	protected void engineInitSign(MockPrivateKey privateKey) throws MockInvalidKeyException {
		if (privateKey == null) {
			throw new NullPointerException("private key cannot be null");
		}
		if (!privateKey.isValid()) {
			throw new MockInvalidKeyException("key is improperly encoded");
		}
		this.publicKeyForVerifying = null;
		this.privateKeyForSigning = privateKey;
		this.bytes = new byte[] {};
	}

	/**
	 * Updates the data to be signed or verified using the specified byte.
	 *
	 * @param b
	 *            the byte to use for the update.
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly.
	 */
	protected void engineUpdate(byte b) throws MockSignatureException {
		engineUpdate(new byte[] { b }, 0, 1);
	}

	/**
	 * Updates the data to be signed or verified, using the specified array of
	 * bytes, starting at the specified offset.
	 *
	 * @param b
	 *            the array of bytes
	 * @param off
	 *            the offset to start from in the array of bytes
	 * @param len
	 *            the number of bytes to use, starting at offset
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly
	 */
	protected void engineUpdate(byte[] b, int off, int len) throws MockSignatureException {
		if (b == null) {
			throw new NullPointerException();
		}

		if (off < 0 || off + len > b.length) {
			throw new IndexOutOfBoundsException();
		}

		if (privateKeyForSigning == null && publicKeyForVerifying == null) {
			throw new MockSignatureException("engine is not initialized properly");
		}

		appendBytes(b, len);
	}

	private void appendBytes(byte[] b, int len) {
		byte[] newBytes = new byte[this.bytes.length + len];
		System.arraycopy(this.bytes, 0, newBytes, 0, this.bytes.length);
		System.arraycopy(b, 0, newBytes, this.bytes.length, len);
	}

	/**
	 * Returns the signature bytes of all the data updated so far. The format of the
	 * signature depends on the underlying signature scheme.
	 *
	 * @return the signature bytes of the signing operation's result.
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly or if this signature
	 *                algorithm is unable to process the input data provided.
	 */
	protected byte[] engineSign() throws MockSignatureException {
		if (this.privateKeyForSigning == null) {
			throw new MockSignatureException("engine has no private keu");
		}
		byte[] sig = this.privateKeyForSigning.sign(this.bytes);
		clearBytes();
		return sig;
	}

	private void clearBytes() {
		this.bytes = new byte[0];
	}

	/**
	 * Finishes this signature operation and stores the resulting signature bytes in
	 * the provided buffer <code>outbuf</code>, starting at <code>offset</code>. The
	 * format of the signature depends on the underlying signature scheme.
	 *
	 * <p>
	 * The signature implementation is reset to its initial state (the state it was
	 * in after a call to one of the <code>engineInitSign</code> methods) and can be
	 * reused to generate further signatures with the same private key.
	 *
	 * This method should be abstract, but we leave it concrete for binary
	 * compatibility. Knowledgeable providers should override this method.
	 *
	 * @param outbuf
	 *            buffer for the signature result.
	 *
	 * @param offset
	 *            offset into <code>outbuf</code> where the signature is stored.
	 *
	 * @param len
	 *            number of bytes within <code>outbuf</code> allotted for the
	 *            signature. Both this default implementation and the SUN provider
	 *            do not return partial digests. If the value of this parameter is
	 *            less than the actual signature length, this method will throw a
	 *            SignatureException. This parameter is ignored if its value is
	 *            greater than or equal to the actual signature length.
	 *
	 * @return the number of bytes placed into <code>outbuf</code>
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly, if this signature
	 *                algorithm is unable to process the input data provided, or if
	 *                <code>len</code> is less than the actual signature length.
	 *
	 * @since 1.2
	 */
	protected int engineSign(byte[] outbuf, int offset, int len) throws MockSignatureException {
		byte[] sig = engineSign();
		if (len < sig.length) {
			throw new MockSignatureException("partial signatures not returned");
		}
		if (outbuf.length - offset < sig.length) {
			throw new MockSignatureException("insufficient space in the output buffer to store the " + "signature");
		}
		System.arraycopy(sig, 0, outbuf, offset, sig.length);
		return sig.length;
	}

	/**
	 * Verifies the passed-in signature.
	 *
	 * @param sigBytes
	 *            the signature bytes to be verified.
	 *
	 * @return true if the signature was verified, false if not.
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly, the passed-in
	 *                signature is improperly encoded or of the wrong type, if this
	 *                signature algorithm is unable to process the input data
	 *                provided, etc.
	 */
	protected boolean engineVerify(byte[] sigBytes) throws MockSignatureException {
		if (this.publicKeyForVerifying == null) {
			throw new MockSignatureException("the engine is not initialized properly");
		}
		boolean verify = this.publicKeyForVerifying.verify(this.bytes, sigBytes);
		clearBytes();
		return verify;
	}

	/**
	 * Verifies the passed-in signature in the specified array of bytes, starting at
	 * the specified offset.
	 *
	 * <p>
	 * Note: Subclasses should overwrite the default implementation.
	 *
	 *
	 * @param sigBytes
	 *            the signature bytes to be verified.
	 * @param offset
	 *            the offset to start from in the array of bytes.
	 * @param length
	 *            the number of bytes to use, starting at offset.
	 *
	 * @return true if the signature was verified, false if not.
	 *
	 * @exception SignatureException
	 *                if the engine is not initialized properly, the passed-in
	 *                signature is improperly encoded or of the wrong type, if this
	 *                signature algorithm is unable to process the input data
	 *                provided, etc.
	 * @since 1.4
	 */
	protected boolean engineVerify(byte[] sigBytes, int offset, int length) throws MockSignatureException {
		byte[] sigBytesCopy = new byte[length];
		System.arraycopy(sigBytes, offset, sigBytesCopy, 0, length);
		return engineVerify(sigBytesCopy);
	}
}
