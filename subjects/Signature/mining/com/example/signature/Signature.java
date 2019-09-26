package com.example.signature;

import org.evosuite.epa.EpaAction;
import org.evosuite.epa.EpaActionPrecondition;

/**
 * The Signature class is used to provide applications the functionality of a
 * digital signature algorithm. Digital signatures are used for authentication
 * and integrity assurance of digital data.
 *
 * <p>
 * The signature algorithm can be, among others, the NIST standard DSA, using
 * DSA and SHA-1. The DSA algorithm using the SHA-1 message digest algorithm can
 * be specified as <tt>SHA1withDSA</tt>. In the case of RSA, there are multiple
 * choices for the message digest algorithm, so the signing algorithm could be
 * specified as, for example, <tt>MD2withRSA</tt>, <tt>MD5withRSA</tt>, or
 * <tt>SHA1withRSA</tt>. The algorithm name must be specified, as there is no
 * default.
 *
 * <p>
 * A Signature object can be used to generate and verify digital signatures.
 *
 * <p>
 * There are three phases to the use of a Signature object for either signing
 * data or verifying a signature:
 * <ol>
 *
 * <li>Initialization, with either
 *
 * <ul>
 *
 * <li>a public key, which initializes the signature for verification (see
 * {@link #initVerify(PublicKey) initVerify}), or
 *
 * <li>a private key (and optionally a Secure Random Number Generator), which
 * initializes the signature for signing (see {@link #initSign(PrivateKey)} and
 * {@link #initSign(PrivateKey, SecureRandom)}).
 *
 * </ul>
 * <p>
 *
 * <li>Updating
 * <p>
 *
 * <p>
 * Depending on the type of initialization, this will update the bytes to be
 * signed or verified. See the {@link #update(byte) update} methods.
 * <p>
 *
 * <li>Signing or Verifying a signature on all updated bytes. See the
 * {@link #sign() sign} methods and the {@link #verify(byte[]) verify} method.
 *
 * </ol>
 *
 * <p>
 * Note that this class is abstract and extends from <code>SignatureSpi</code>
 * for historical reasons. Application developers should only take notice of the
 * methods defined in this <code>Signature</code> class; all the methods in the
 * superclass are intended for cryptographic service providers who wish to
 * supply their own implementations of digital signature algorithms.
 *
 * <p>
 * Every implementation of the Java platform is required to support the
 * following standard <code>Signature</code> algorithms:
 * <ul>
 * <li><tt>SHA1withDSA</tt></li>
 * <li><tt>SHA1withRSA</tt></li>
 * <li><tt>SHA256withRSA</tt></li>
 * </ul>
 * These algorithms are described in the <a href= "
 * {@docRoot}/../technotes/guides/security/StandardNames.html#Signature" >
 * Signature section</a> of the Java Cryptography Architecture Standard
 * Algorithm Name Documentation. Consult the release documentation for your
 * implementation to see if any other algorithms are supported.
 *
 * @author Benjamin Renaud
 *
 */

public class Signature {

	private final MockSignatureSpi engine = new MockSignatureSpi();

	/*
	 * The algorithm for this signature object. This value is used to map an OID to
	 * the particular algorithm. The mapping is done in
	 * AlgorithmObject.algOID(String algorithm)
	 */
	private String algorithm;

	/**
	 * Possible {@link #state} value, signifying that this signature object has not
	 * yet been initialized.
	 */
	private final static int UNINITIALIZED = 0;

	/**
	 * Possible {@link #state} value, signifying that this signature object has been
	 * initialized for signing.
	 */
	private final static int SIGN = 2;

	/**
	 * Possible {@link #state} value, signifying that this signature object has been
	 * initialized for verification.
	 */
	private final static int VERIFY = 3;

	/**
	 * Current state of this signature object.
	 */
	private int state = UNINITIALIZED;

	/**
	 * Creates a Signature object for the specified algorithm.
	 *
	 * @param algorithm
	 *            the standard string name of the algorithm. See the Signature
	 *            section in the <a href=
	 *            "{@docRoot}/../technotes/guides/security/StandardNames.html#Signature"
	 *            > Java Cryptography Architecture Standard Algorithm Name
	 *            Documentation</a> for information about standard algorithm names.
	 */
	@EpaAction(name = "Signature()")
	public Signature(String algorithm) throws Exception {
		try {
			this.algorithm = algorithm;
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Initializes this object for verification. If this method is called again with
	 * a different argument, it negates the effect of this call.
	 *
	 * @param publicKey
	 *            the public key of the identity whose signature is going to be
	 *            verified.
	 *
	 * @exception InvalidKeyException
	 *                if the key is invalid.
	 */
	@EpaAction(name = "initVerify(PublicKey)")
	public final void initVerify(MockPublicKey publicKey) throws MockInvalidKeyException, Exception {
		try {
			initVerify0(publicKey);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void initVerify0(MockPublicKey publicKey) throws MockInvalidKeyException {
		engine.engineInitVerify(publicKey);
		state = VERIFY;
	}

	/**
	 * Initialize this object for signing. If this method is called again with a
	 * different argument, it negates the effect of this call.
	 *
	 * @param privateKey
	 *            the private key of the identity whose signature is going to be
	 *            generated.
	 *
	 * @exception InvalidKeyException
	 *                if the key is invalid.
	 */
	@EpaAction(name = "initSign(PrivateKey)")
	public final void initSign(MockPrivateKey privateKey) throws MockInvalidKeyException, Exception {
		try {
			initSign0(privateKey);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void initSign0(MockPrivateKey privateKey) throws MockInvalidKeyException {
		engine.engineInitSign(privateKey);
		state = SIGN;
	}

	/**
	 * Returns the signature bytes of all the data updated. The format of the
	 * signature depends on the underlying signature scheme.
	 *
	 * <p>
	 * A call to this method resets this signature object to the state it was in
	 * when previously initialized for signing via a call to
	 * <code>initSign(PrivateKey)</code>. That is, the object is reset and available
	 * to generate another signature from the same signer, if desired, via new calls
	 * to <code>update</code> and <code>sign</code>.
	 *
	 * @return the signature bytes of the signing operation's result.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly or if
	 *                this signature algorithm is unable to process the input data
	 *                provided.
	 */
	@EpaAction(name = "sign()")
	public final byte[] sign() throws MockSignatureException, Exception {
		try {
			return sign0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private byte[] sign0() throws MockSignatureException {
		if (state == SIGN) {
			return engine.engineSign();
		}
		throw new MockSignatureException("object not initialized for " + "signing");
	}

	/**
	 * Finishes the signature operation and stores the resulting signature bytes in
	 * the provided buffer <code>outbuf</code>, starting at <code>offset</code>. The
	 * format of the signature depends on the underlying signature scheme.
	 *
	 * <p>
	 * This signature object is reset to its initial state (the state it was in
	 * after a call to one of the <code>initSign</code> methods) and can be reused
	 * to generate further signatures with the same private key.
	 *
	 * @param outbuf
	 *            buffer for the signature result.
	 *
	 * @param offset
	 *            offset into <code>outbuf</code> where the signature is stored.
	 *
	 * @param len
	 *            number of bytes within <code>outbuf</code> allotted for the
	 *            signature.
	 *
	 * @return the number of bytes placed into <code>outbuf</code>.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly, if this
	 *                signature algorithm is unable to process the input data
	 *                provided, or if <code>len</code> is less than the actual
	 *                signature length.
	 *
	 * @since 1.2
	 */
	@EpaAction(name = "sign(byte[],int,int)")
	public final int sign(byte[] outbuf, int offset, int len) throws MockSignatureException, Exception {
		try {
			return sign0(outbuf, offset, len);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private int sign0(byte[] outbuf, int offset, int len) throws MockSignatureException {
		if (outbuf == null) {
			throw new IllegalArgumentException("No output buffer given");
		}
		if (outbuf.length - offset < len) {
			throw new IllegalArgumentException("Output buffer too small for specified offset and length");
		}
		if (state != SIGN) {
			throw new MockSignatureException("object not initialized for " + "signing");
		}
		return engine.engineSign(outbuf, offset, len);
	}

	/**
	 * Verifies the passed-in signature.
	 *
	 * <p>
	 * A call to this method resets this signature object to the state it was in
	 * when previously initialized for verification via a call to
	 * <code>initVerify(PublicKey)</code>. That is, the object is reset and
	 * available to verify another signature from the identity whose public key was
	 * specified in the call to <code>initVerify</code>.
	 *
	 * @param signature
	 *            the signature bytes to be verified.
	 *
	 * @return true if the signature was verified, false if not.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly, the
	 *                passed-in signature is improperly encoded or of the wrong
	 *                type, if this signature algorithm is unable to process the
	 *                input data provided, etc.
	 */
	@EpaAction(name = "verify(byte[])")
	public final boolean verify(byte[] signature) throws MockSignatureException, Exception {
		try {
			return verify0(signature);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private boolean verify0(byte[] signature) throws MockSignatureException {
		if (state == VERIFY) {
			return engine.engineVerify(signature);
		}
		throw new MockSignatureException("object not initialized for " + "verification");
	}

	/**
	 * Verifies the passed-in signature in the specified array of bytes, starting at
	 * the specified offset.
	 *
	 * <p>
	 * A call to this method resets this signature object to the state it was in
	 * when previously initialized for verification via a call to
	 * <code>initVerify(PublicKey)</code>. That is, the object is reset and
	 * available to verify another signature from the identity whose public key was
	 * specified in the call to <code>initVerify</code>.
	 *
	 *
	 * @param signature
	 *            the signature bytes to be verified.
	 * @param offset
	 *            the offset to start from in the array of bytes.
	 * @param length
	 *            the number of bytes to use, starting at offset.
	 *
	 * @return true if the signature was verified, false if not.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly, the
	 *                passed-in signature is improperly encoded or of the wrong
	 *                type, if this signature algorithm is unable to process the
	 *                input data provided, etc.
	 * @exception IllegalArgumentException
	 *                if the <code>signature</code> byte array is null, or the
	 *                <code>offset</code> or <code>length</code> is less than 0, or
	 *                the sum of the <code>offset</code> and <code>length</code> is
	 *                greater than the length of the <code>signature</code> byte
	 *                array.
	 * @since 1.4
	 */
	@EpaAction(name = "verify(byte[],int,int)")
	public final boolean verify(byte[] signature, int offset, int length) throws MockSignatureException, Exception {
		try {
			return verify0(signature, offset, length);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private boolean verify0(byte[] signature, int offset, int length) throws MockSignatureException {
		if (state == VERIFY) {
			if ((signature == null) || (offset < 0) || (length < 0) || (length > signature.length - offset)) {
				throw new IllegalArgumentException("Bad arguments");
			}

			return engine.engineVerify(signature, offset, length);
		}
		throw new MockSignatureException("object not initialized for " + "verification");
	}

	/**
	 * Updates the data to be signed or verified by a byte.
	 *
	 * @param b
	 *            the byte to use for the update.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly.
	 */
	@EpaAction(name = "update(byte)")
	public final void update(byte b) throws MockSignatureException, Exception {
		try {
			update0(b);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void update0(byte b) throws MockSignatureException {
		if (state == VERIFY || state == SIGN) {
			engine.engineUpdate(b);
		} else {
			throw new MockSignatureException("object not initialized for " + "signature or verification");
		}
	}

	/**
	 * Updates the data to be signed or verified, using the specified array of
	 * bytes.
	 *
	 * @param data
	 *            the byte array to use for the update.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly.
	 */
	@EpaAction(name = "update(byte[])")
	public final void update(byte[] data) throws MockSignatureException, Exception {
		try {
			update0(data, 0, data.length);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Updates the data to be signed or verified, using the specified array of
	 * bytes, starting at the specified offset.
	 *
	 * @param data
	 *            the array of bytes.
	 * @param off
	 *            the offset to start from in the array of bytes.
	 * @param len
	 *            the number of bytes to use, starting at offset.
	 *
	 * @exception SignatureException
	 *                if this signature object is not initialized properly.
	 */
	@EpaAction(name = "update(byte[],int,int)")
	public final void update(byte[] data, int off, int len) throws MockSignatureException, Exception {
		try {
			update0(data, off, len);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private void update0(byte[] data, int off, int len) throws MockSignatureException {
		if (state == SIGN || state == VERIFY) {
			engine.engineUpdate(data, off, len);
		} else {
			throw new MockSignatureException("object not initialized for " + "signature or verification");
		}
	}

	/**
	 * Returns the name of the algorithm for this signature object.
	 *
	 * @return the name of the algorithm for this signature object.
	 */
	@EpaAction(name = "getAlgorithm()")
	public final String getAlgorithm() throws Exception {
		try {
			return getAlgorithm0();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private String getAlgorithm0() {
		return this.algorithm;
	}
	
	
	
	/*
	 * -------------------------------------------------------------------------
	 * EPA Preconditions Methods
	 * -------------------------------------------------------------------------
	 */
	
	@EpaActionPrecondition(name = "initVerify(PublicKey)")
	private boolean isInitVerifyEnabled() {
		return state == UNINITIALIZED || state == SIGN || state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "initSign(PrivateKey)")
	private boolean isInitSignEnabled() {
		return state == UNINITIALIZED || state == SIGN || state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "sign()")
	private boolean isSingEnabled() {
		return state == SIGN;
	}
	
	@EpaActionPrecondition(name = "sign(byte[],int,int)")
	private boolean issignbyteintintEnabled() {
		return state == SIGN;
	}
	
	@EpaActionPrecondition(name = "verify(byte[])")
	private boolean isVerifybytearrayEnabled() {
		return state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "verify(byte[],int,int)")
	private boolean isVerifybytearrayintintEnabled() {
		return state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "update(byte)")
	private boolean isUpdatebyteEnabled() {
		return state == SIGN || state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "update(byte[])")
	private boolean isUpdatebytearrayEnabled() {
		return state == SIGN || state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "update(byte[],int,int)")
	private boolean isUpdatebytearrayintintEnabled() {
		return state == SIGN || state == VERIFY;
	}
	
	@EpaActionPrecondition(name = "getAlgorithm()")
	private boolean isGetAlgorithmEnabled() {
		return state == UNINITIALIZED || state == SIGN || state == VERIFY;
	}
}
