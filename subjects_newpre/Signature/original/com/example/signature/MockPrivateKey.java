package com.example.signature;

public class MockPrivateKey {

	private final MCaesarCipher cipher;
	
	public MockPrivateKey(MCaesarCipher cipher) {
		this.cipher = cipher;
	}
	
	public boolean isValid() {
		return cipher!=null;
	}

	public byte[] sign(byte[] bytes) throws MockSignatureException {
		if (!isValid()) {
			throw new MockSignatureException();
		}
		return cipher.sign(bytes);
	}

}
