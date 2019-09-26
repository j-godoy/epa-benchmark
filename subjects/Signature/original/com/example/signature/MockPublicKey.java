package com.example.signature;

public class MockPublicKey {

	private final MCaesarCipher cipher;

	public MockPublicKey(MCaesarCipher cipher) {
		this.cipher = cipher;
	}

	public boolean isValid() {
		return cipher!=null;
	}

	public boolean verify(byte[] text, byte[] signature) throws MockSignatureException {
		if (!isValid()) {
			throw new MockSignatureException();
		}
		return cipher.verify(text,signature);
	}

}
